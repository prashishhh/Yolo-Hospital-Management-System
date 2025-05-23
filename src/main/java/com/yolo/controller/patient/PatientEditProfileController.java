package com.yolo.controller.patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.yolo.model.UserModel;
import com.yolo.service.patient.PatientEditProfileService;
import com.yolo.service.patient.PatientProfileService;
import com.yolo.util.ImageUtil;
import com.yolo.util.SessionUtil;
import com.yolo.util.ValidationUtil;

/**
 * PatientEditProfileController is responsible for handling patient profile editing operations.
 * It manages the display and processing of the patient profile edit page, allowing patients
 * to view and update their personal information, contact details, and preferences.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-edit-profile"})
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 5,   // 5 MB
	    maxRequestSize = 1024 * 1024 * 10 // 10 MB
	)
public class PatientEditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private PatientProfileService patientProfileService;
	 private PatientEditProfileService patientEditProfileService;
	 private ImageUtil imageUtil;
       
    /**
     * Constructor for PatientEditProfileController. 
     * Initializes the servlet by calling the parent constructor.
     */
    public PatientEditProfileController() {
    	
    	this.patientProfileService = new PatientProfileService();
    	this.patientEditProfileService = new PatientEditProfileService();
    	this.imageUtil = new ImageUtil();
    }

	/**
	 * Handles GET requests to the patient profile edit page.
	 * Forwards the request to the patient_edit_profile.jsp view which displays
	 * the form for editing patient profile information.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get patient from session (set by PatientProfileController)
        UserModel patient = (UserModel) request.getSession().getAttribute("patient");
        
        if (patient == null) {
            // If no patient in session, redirect back to profile
            response.sendRedirect(request.getContextPath() + "/patient-profile");
            return;
        }
        
        // Remove from session and set as request attribute
        request.getSession().removeAttribute("patient");
        request.setAttribute("patient", patient);
        
        
		request.getRequestDispatcher("/WEB-INF/pages/patient/patient_edit_profile.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for patient profile updates.
	 * Currently delegates to doGet method, but should be extended to process 
	 * profile form submissions, validate input data, and update the patient record.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Get user ID from session
        Integer userId = (Integer) SessionUtil.getAttribute(request, "userID");

        if (userId == null) {
            // If user ID is not available in session, redirect to login
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        doPut(request, response, userId);
	}
	
	/**
     * Processes the profile update form submission.
     * Validates input, handles image upload, and updates patient information.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @param userId   The ID of the user being updated
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void doPut(HttpServletRequest request, HttpServletResponse response, Integer userId) throws ServletException, IOException {
        try {
            // Validate form fields
            String validationError = validateUpdateForm(request);
            if (validationError != null) {
                request.setAttribute("error", validationError);
                doGet(request, response);
                return;
            }

            // Extract form data
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dateOfBirthStr = request.getParameter("dateOfBirth");
            String gender = request.getParameter("gender");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String city = request.getParameter("city");
            String district = request.getParameter("district");
            String municipality = request.getParameter("municipality");
            String wardNo = request.getParameter("ward");

            // Parse date of birth
            LocalDate dateOfBirth;
            try {
                dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ISO_DATE);
            } catch (DateTimeParseException e) {
                request.setAttribute("error", "Invalid date format. Please use YYYY-MM-DD format.");
                doGet(request, response);
                return;
            }

            // Get the current patient data to preserve values that aren't being changed
            UserModel currentPatient = patientProfileService.getPatientDetails(userId);
            if (currentPatient == null) {
                request.setAttribute("error", "Unable to retrieve current patient data.");
                doGet(request, response);
                return;
            }

            // Handle profile picture upload
            String imagePath = currentPatient.getImagePath(); // Default to current image path
            Part filePart = request.getPart("profilePicture");

            if (filePart != null && filePart.getSize() > 0) {
                // Validate image extension
                if (!ValidationUtil.isValidImageExtension(filePart)) {
                    request.setAttribute("error", "Invalid image format. Please upload JPG, JPEG, PNG, or GIF.");
                    doGet(request, response);
                    return;
                }

                // Upload new profile picture
                boolean uploadSuccess = imageUtil.uploadImage(filePart, request.getServletContext().getRealPath("/"), "patient");

                if (uploadSuccess) {
                    // Update image path
                    imagePath = imageUtil.getImageNameFromPart(filePart);
                } else {
                    request.setAttribute("error", "Failed to upload profile picture. Other information was not updated.");
                    doGet(request, response);
                    return;
                }
            }

            // Create UserModel with updated data
            UserModel updatedPatient = new UserModel(
                userId, firstName, lastName, dateOfBirth, gender,
                phoneNumber, email, city, district, municipality, wardNo,
                currentPatient.getUserName(), currentPatient.getPassword(),
                currentPatient.getUserRole(), imagePath
            );

            // Attempt to update patient information
            Boolean result = patientEditProfileService.updatePatientInfo(updatedPatient);

            if (result != null && result) {
                // Update successful, redirect to profile page
                response.sendRedirect(request.getContextPath() + "/patient-profile");
            } else {
                // Update failed
                handleUpdateFailure(request, result);
                doGet(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred. Please try again later.");
            doGet(request, response);
        }
    }
    
    
    /**
     * Handles update failures by setting an appropriate error message.
     *
     * @param request The HttpServletRequest object
     * @param updateStatus Result of the update operation
     */
    private void handleUpdateFailure(HttpServletRequest request, Boolean updateStatus) {
        String errorMessage;

        if (updateStatus == null) {
            errorMessage = "The server is currently unavailable. Please try again later.";
        } else {
            errorMessage = "Failed to update profile. Please check your information and try again.";
        }

        request.setAttribute("error", errorMessage);
    }
    
    
    /**
     * Validates the form fields for the update operation.
     */
    private String validateUpdateForm(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dobStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String municipality = request.getParameter("municipality");
        String wardNo = request.getParameter("ward");

        if (ValidationUtil.isNullOrEmpty(firstName)) return "First name is required.";
        if (ValidationUtil.isNullOrEmpty(lastName)) return "Last name is required.";
        if (ValidationUtil.isNullOrEmpty(dobStr)) return "Date of birth is required.";
        if (ValidationUtil.isNullOrEmpty(gender)) return "Gender is required.";
        if (ValidationUtil.isNullOrEmpty(phoneNumber)) return "Phone number is required.";
        if (ValidationUtil.isNullOrEmpty(email)) return "Email is required.";
        if (ValidationUtil.isNullOrEmpty(city)) return "City is required.";
        if (ValidationUtil.isNullOrEmpty(district)) return "District is required.";
        if (ValidationUtil.isNullOrEmpty(municipality)) return "Municipality is required.";
        if (ValidationUtil.isNullOrEmpty(wardNo)) return "Ward number is required.";

        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        if (!ValidationUtil.isValidGender(gender)) return "Gender must be 'Male', 'Female', or 'Other'.";
        if (!ValidationUtil.isValidEmail(email)) return "Invalid email format.";
        if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) return "Phone number must be 10 digits and start with 98.";
        if (!ValidationUtil.isValidDoB(dob)) return "Date of birth cannot be in the future.";

        return null;
    }
}