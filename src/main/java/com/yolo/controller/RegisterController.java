package com.yolo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.yolo.model.UserModel;
import com.yolo.service.RegisterService;
import com.yolo.util.ImageUtil;
import com.yolo.util.PasswordUtil;
import com.yolo.util.ValidationUtil;

/**
 * RegisterController handles user registration requests and processes form submissions,
 * It performs input validation, manages file uploads, and creates user accounts.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
				maxFileSize = 1024 * 1024 * 10, // 10MB	
				maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ImageUtil imageUtil = new ImageUtil();
	private final RegisterService registerService = new RegisterService();
     
	
    /**
     * default constructor
     */
    public RegisterController() {
        super();
    }

    /**
	 * Handles HTTP GET requests by forwarding to the registration form view.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
	}

    /** 
     * Handles HTTP POST requests for user registration. It validates form input, extracts user data, registers the user, and manages image uploads.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try {
			// Validate and extract user model
			String validationMessage = validateRegistrationForm(request);
			if (validationMessage != null) {
				handleError(request, response, validationMessage);
				return;
			}

			// Extract user model from request
			UserModel userModel = extractUserModel(request);
			
			// Attempt to register user
			Boolean isAdded = registerService.registerPatient(userModel);

			if (isAdded == null) {
				handleError(request, response, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {
				try {
					if (uploadImage(request)) {
						handleSuccess(request, response, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
					} else {
						handleError(request, response, "Could not upload the image. Please try again later!");
					}
				} catch (IOException | ServletException e) {
					handleError(request, response, "An error occurred while uploading the image. Please try again later!");
					e.printStackTrace(); // Log the exception
				}
			} else {
				handleError(request, response, "Could not register your account. Please try again later!");
			}
		} catch (Exception e) {
			handleError(request, response, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}
    
    /**
     * Validates the registration form input. Checks for empty fields, validates data formats, ensures password match and age restrictions, and verifies the uploaded image file.
     *
     * @param request the HttpServletRequest object containing form data
     * @return null if all inputs are valid; otherwise, returns the validation error message
     */
    private String validateRegistrationForm(HttpServletRequest request) {
    	
    	// Extract form data
    	String firstName = request.getParameter("firstName");
    	String lastName = request.getParameter("lastName");
    	String dobStr = request.getParameter("dateOfBirth");
    	String gender = request.getParameter("gender");
    	String number = request.getParameter("phoneNumber");
    	String email = request.getParameter("email");
    	String city = request.getParameter("city");
    	String district = request.getParameter("district");
    	String municipality = request.getParameter("municipality");
    	String wardNo = request.getParameter("wardNo");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String retypePassword = request.getParameter("retypePassword");
    	
    	// Check for null or empty fields first
    	if (ValidationUtil.isNullOrEmpty(firstName))
    		return "First name is required.";
    	if (ValidationUtil.isNullOrEmpty(lastName))
    		return "Last name is required.";
    	if (ValidationUtil.isNullOrEmpty(username))
    		return "Username is required.";
    	if (ValidationUtil.isNullOrEmpty(dobStr))
    		return "Date of birth is required.";
    	if (ValidationUtil.isNullOrEmpty(gender))
    		return "Gender is required.";
    	if (ValidationUtil.isNullOrEmpty(email))
    		return "Email is required.";
    	if (ValidationUtil.isNullOrEmpty(number))
    		return "Phone number is required.";
    	if (ValidationUtil.isNullOrEmpty(city))
    		return "City is required.";
    	if (ValidationUtil.isNullOrEmpty(district))
    		return "District is required.";
    	if (ValidationUtil.isNullOrEmpty(municipality))
    		return "Municipality is required.";
    	if (ValidationUtil.isNullOrEmpty(wardNo))
    		return "Ward number is required.";
    	if (ValidationUtil.isNullOrEmpty(password))
    		return "Password is required.";
    	if (ValidationUtil.isNullOrEmpty(retypePassword))
    		return "Please retype the password.";

    	// Convert and validate date of birth
    	LocalDate dob;
    	try {
    		dob = LocalDate.parse(dobStr);
    	} catch (Exception e) {
    		return "Invalid date format. Please use YYYY-MM-DD.";
    	}
    	
    	// Validate field format
    	if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
    		return "Username must start with a letter and contain only letters and numbers.";
    	if (!ValidationUtil.isValidGender(gender))
    		return "Gender must be 'male' or 'female'.";
    	if (!ValidationUtil.isValidEmail(email))
    		return "Invalid email format.";
    	if (!ValidationUtil.isValidPhoneNumber(number))
    		return "Phone number must be 10 digits and start with 98.";
    	if (!ValidationUtil.isValidPassword(password))
    		return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
    	if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
    		return "Passwords do not match.";
    	
    	// Check if the date of birth is at least 16 years before today
    	if (!ValidationUtil.isValidDoB(dob))
    		return "You must be at least 16 years old to register.";
    	
    	// Validate image file
    	try {
			Part image = request.getPart("image");
			if (!ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			return "Error handling image file. Please ensure the file is valid.";
		}
    	
		return null; // All validations are passed
	}
    
    /**
	 * Extracts user registration details from the HttpServletRequest and creates
	 * a UserModel object. This method handles parameter extraction, password encryption,
	 * and image file processing.
	 *
	 * @param request HttpServletRequest containing the registration form data
	 * @return UserModel object populated with user input data
	 * @throws Exception if an error occurs while processing the request data
	 */
	private UserModel extractUserModel(HttpServletRequest request) throws Exception {
		
		// Extract form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String municipality = request.getParameter("municipality");
		String wardNo = request.getParameter("wardNo");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Encrypt the password using username as salt after form validation in validateRegistrationForm
		password = PasswordUtil.encrypt(username, password);
		
		// Handle image upload
		Part image = request.getPart("image");
		String imagePath = imageUtil.getImageNameFromPart(image);
		
		// Set fixed role as "Patient" and default userId as 0
		String userRole = "Patient";
		
		// Create and return the UserModel object
		return new UserModel(firstName, lastName, dateOfBirth, gender, phoneNumber, email,
				city, district, municipality, wardNo, username, password, userRole, imagePath);
	}
	
	/**
	 * Handles the image upload for the user registration process.
	 * 
	 * This method retrieves the image part from the HTTP request and passes it to the `ImageUtil` class 
	 * for uploading. The image is stored in a specific directory on the server based on the context 
	 * path, and the directory is specified as "user" for user profile images.
	 *
	 * @param request The HttpServletRequest object containing the form data and file upload part.
	 * @return A boolean indicating whether the image upload was successful or not.
	 * @throws IOException If an I/O error occurs during the file upload process.
	 * @throws ServletException If an error occurs when retrieving the file part from the request.
	 */
	private boolean uploadImage(HttpServletRequest request) throws IOException, ServletException {
		
		// Retrieve the uploaded image file from the request
		Part image = request.getPart("image");
		
		// Call the ImageUtil's uploadImage method to store the image in the specified directory
		return imageUtil.uploadImage(image, request.getServletContext().getRealPath("/"), "patient");
	}

	/**
	 * Handles the success scenario after a successful user registration or action.
	 * 
	 * This method sets a success message as a request attribute and forwards the request 
	 * to the specified redirect page. The message is typically displayed to the user 
	 * to inform them of a successful action, such as successful registration or form submission.
	 *
	 * @param request The HttpServletRequest object containing the request data.
	 * @param response The HttpServletResponse object used to send the response to the client.
	 * @param message The success message to be displayed to the user.
	 * @param redirectPage The path of the page to which the request is forwarded after the success message is set.
	 * @throws ServletException If an error occurs during the request forwarding.
	 * @throws IOException If an I/O error occurs during the forwarding process.
	 */
	private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message,
			String redirectPage) throws ServletException, IOException{
		
		request.setAttribute("success", message);
		request.getRequestDispatcher(redirectPage).forward(request, response);	
	}

	/**
	 * Handles error scenarios during user registration.
	 * 
	 * This method sets an error message as a request attribute and retains the input values 
	 * submitted by the user in the form fields. This is done to ensure that when the user is 
	 * redirected back to the registration page, their previous inputs are preserved.
	 * 
	 * The method forwards the request to the registration page, where the error message 
	 * and previously entered data are displayed to the user.
	 *
	 * @param request The HttpServletRequest object containing the request data.
	 * @param response The HttpServletResponse object used to send the response to the client.
	 * @param message The error message to be displayed to the user.
	 * @throws ServletException If an error occurs during the request forwarding.
	 * @throws IOException If an I/O error occurs during the forwarding process.
	 */
	private void handleError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{

		// Set the error message to be displayed on the registration page
		request.setAttribute("error", message);
		
		request.setAttribute("firstName", request.getParameter("firstName"));
		request.setAttribute("lastName", request.getParameter("lastName"));
		request.setAttribute("dateOfBirth", request.getParameter("dob"));
		request.setAttribute("gender", request.getParameter("gender"));
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("phoneNumber", request.getParameter("phoneNumber"));
		request.setAttribute("city", request.getParameter("city"));
		request.setAttribute("district", request.getParameter("district"));
		request.setAttribute("municipality", request.getParameter("municipality"));
		request.setAttribute("wardNo", request.getParameter("wardNo"));
		request.setAttribute("username", request.getParameter("username"));
		request.setAttribute("password", request.getParameter("password"));
		
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}

}
