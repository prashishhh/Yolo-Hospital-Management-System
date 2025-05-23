package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.yolo.model.DoctorModel;
import com.yolo.service.admin.AddDoctorService;
import com.yolo.util.ImageUtil;
import com.yolo.util.ValidationUtil;

/**
 * Servlet implementation class AddDoctorController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/add-doctor" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,    // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddDoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ImageUtil imageUtil = new ImageUtil();
    private final AddDoctorService addDoctorService = new AddDoctorService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDoctorController() {
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/admin/add_doctor.jsp").forward(request, response);
	}

	/**
     * Handles POST requests for adding a doctor. Validates input, processes image upload,
     * and saves the doctor details.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate form input
            String validationMessage = validateDoctorForm(request);
            if (validationMessage != null) {
                handleError(request, response, validationMessage);
                return;
            }

            // Extract DoctorModel from request
            DoctorModel doctorModel = extractDoctorModel(request);

            // Attempt to add doctor
            Boolean isAdded = addDoctorService.addDoctor(doctorModel);

            if (isAdded == null) {
                handleError(request, response, "Server is under maintenance. Please try again later!");
            } else if (isAdded) {
                try {
                    if (uploadImage(request)) {
                        handleSuccess(request, response, "Doctor successfully added!", "/WEB-INF/pages/admin/admin_doctor_list.jsp");
                    } else {
                        handleError(request, response, "Could not upload the image. Please try again later!");
                    }
                } catch (IOException | ServletException e) {
                    handleError(request, response, "Error uploading image. Please try again later!");
                    e.printStackTrace();
                }
            } else {
                handleError(request, response, "Could not add doctor. Please try again later!");
            }
        } catch (Exception e) {
            handleError(request, response, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }
    
    /**
     * Validates the doctor form input.
     */
    private String validateDoctorForm(HttpServletRequest request) {
        // Extract form data
        String firstName = request.getParameter("doctorFirstName");
        String lastName = request.getParameter("doctorLastName");
        String dobStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("doctorGender");
        String phoneNumber = request.getParameter("doctorPhoneNumber");
        String email = request.getParameter("doctorEmail");
        String city = request.getParameter("doctorCity");
        String district = request.getParameter("doctorDistrict");
        String municipality = request.getParameter("doctorMunicipality");
        String wardNo = request.getParameter("doctorWard");
        String qualification = request.getParameter("doctorQualification");
        String experience = request.getParameter("doctorExperience");
        String specialization = request.getParameter("doctorSpecialization");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(firstName))
            return "First name is required.";
        if (ValidationUtil.isNullOrEmpty(lastName))
            return "Last name is required.";
        if (ValidationUtil.isNullOrEmpty(dobStr))
            return "Date of birth is required.";
        if (ValidationUtil.isNullOrEmpty(gender))
            return "Gender is required.";
        if (ValidationUtil.isNullOrEmpty(phoneNumber))
            return "Phone number is required.";
        if (ValidationUtil.isNullOrEmpty(email))
            return "Email is required.";
        if (ValidationUtil.isNullOrEmpty(city))
            return "City is required.";
        if (ValidationUtil.isNullOrEmpty(district))
            return "District is required.";
        if (ValidationUtil.isNullOrEmpty(municipality))
            return "Municipality is required.";
        if (ValidationUtil.isNullOrEmpty(wardNo))
            return "Ward number is required.";
        if (ValidationUtil.isNullOrEmpty(qualification))
            return "Qualification is required.";
        if (ValidationUtil.isNullOrEmpty(experience))
            return "Experience is required.";
        if (ValidationUtil.isNullOrEmpty(specialization))
            return "Specialization is required.";

        // Validate date of birth
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
            if (!ValidationUtil.isValidDoB(dob))
                return "Invalid date of birth.";
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        // Validate field formats
        if (!ValidationUtil.isAlphabetic(firstName) || !ValidationUtil.isAlphabetic(lastName))
            return "Names must contain only letters.";
        if (!ValidationUtil.isValidGender(gender))
            return "Gender must be 'Male', 'Female', 'Other', or 'Prefer not to say'.";
        if (!ValidationUtil.isValidEmail(email))
            return "Invalid email format.";
        if (!ValidationUtil.isValidPhoneNumber(phoneNumber))
            return "Phone number must be 10 digits and start with 98.";
        if (!experience.matches("\\d+"))
            return "Experience must be a valid number.";

        // Validate image file
        try {
            Part image = request.getPart("doctorProfile");
            if (image != null && image.getSize() > 0 && !ValidationUtil.isValidImageExtension(image))
                return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
        } catch (IOException | ServletException e) {
            return "Error handling image file. Please ensure the file is valid.";
        }

        return null; // All validations passed
    }
    
    
    /**
     * Extracts doctor details from the request and creates a DoctorModel.
     */
    private DoctorModel extractDoctorModel(HttpServletRequest request) throws Exception {
        String firstName = request.getParameter("doctorFirstName");
        String lastName = request.getParameter("doctorLastName");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String gender = request.getParameter("doctorGender");
        String phoneNumber = request.getParameter("doctorPhoneNumber");
        String email = request.getParameter("doctorEmail");
        String city = request.getParameter("doctorCity");
        String district = request.getParameter("doctorDistrict");
        String municipality = request.getParameter("doctorMunicipality");
        String wardNo = request.getParameter("doctorWard");
        String qualification = request.getParameter("doctorQualification");
        String experience = request.getParameter("doctorExperience");
        String specialization = request.getParameter("doctorSpecialization");

        // Handle image upload
        Part image = request.getPart("doctorProfile");
        String imagePath = (image != null && image.getSize() > 0) ? imageUtil.getImageNameFromPart(image) : null;

        // Create and return DoctorModel
        return new DoctorModel(firstName, lastName, dateOfBirth, gender, phoneNumber, email,
                city, district, municipality, wardNo, qualification, experience, specialization, imagePath);
    }
    
    /**
     * Handles image upload for the doctor profile.
     */
    private boolean uploadImage(HttpServletRequest request) throws IOException, ServletException {
        Part image = request.getPart("doctorProfile");
        if (image == null || image.getSize() == 0) {
            return true; // Image is optional
        }
        return imageUtil.uploadImage(image, request.getServletContext().getRealPath("/"), "doctors");
    }

    /**
     * Handles successful doctor addition.
     */
    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message, String redirectPage)
            throws ServletException, IOException {
        request.setAttribute("success", message);
        request.getRequestDispatcher(redirectPage).forward(request, response);
    }
    
    /**
     * Handles errors during doctor addition.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.setAttribute("doctorFirstName", request.getParameter("doctorFirstName"));
        request.setAttribute("doctorLastName", request.getParameter("doctorLastName"));
        request.setAttribute("dateOfBirth", request.getParameter("dateOfBirth"));
        request.setAttribute("doctorGender", request.getParameter("doctorGender"));
        request.setAttribute("doctorPhoneNumber", request.getParameter("doctorPhoneNumber"));
        request.setAttribute("doctorEmail", request.getParameter("doctorEmail"));
        request.setAttribute("doctorCity", request.getParameter("doctorCity"));
        request.setAttribute("doctorDistrict", request.getParameter("doctorDistrict"));
        request.setAttribute("doctorMunicipality", request.getParameter("doctorMunicipality"));
        request.setAttribute("doctorWard", request.getParameter("doctorWard"));
        request.setAttribute("doctorQualification", request.getParameter("doctorQualification"));
        request.setAttribute("doctorExperience", request.getParameter("doctorExperience"));
        request.setAttribute("doctorSpecialization", request.getParameter("doctorSpecialization"));
        request.getRequestDispatcher("/WEB-INF/pages/admin/add_doctor.jsp").forward(request, response);
    }

}
