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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.yolo.model.DoctorModel;
import com.yolo.service.admin.AdminDoctorListService;
import com.yolo.service.admin.EditDoctorService;
import com.yolo.util.ImageUtil;
import com.yolo.util.SessionUtil;
import com.yolo.util.ValidationUtil;

/**
 * EditDoctorController handles the editing of doctor profiles by admins.
 * It manages displaying the edit form and processing updates, including validation and image uploads.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/edit-doctor" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 5,   // 5 MB
    maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class EditDoctorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminDoctorListService adminDoctorListService;
    private EditDoctorService editDoctorService;
    private ImageUtil imageUtil;

    public EditDoctorController() {
        this.adminDoctorListService = new AdminDoctorListService();
        this.editDoctorService = new EditDoctorService();
        this.imageUtil = new ImageUtil();
    }

    /**
     * Handles GET requests to display the doctor edit form.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve doctor from session (set by AdminDoctorListController)
        DoctorModel doctor = (DoctorModel) SessionUtil.getAttribute(request, "doctor");

        if (doctor == null) {
            // Redirect to doctor list if no doctor is found in session
            response.sendRedirect(request.getContextPath() + "/admin-doctor-list");
            return;
        }

        // Remove from session and set as request attribute for JSP
        SessionUtil.removeAttribute(request, "doctor");
        request.setAttribute("doctor", doctor);

        request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to update doctor details.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get doctorID from the form (hidden field)
        String doctorIDStr = request.getParameter("doctorID");
        Integer doctorID;

        try {
            doctorID = Integer.parseInt(doctorIDStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Doctor ID");
            response.sendRedirect(request.getContextPath() + "/admin-doctor-list");
            return;
        }

        doPut(request, response, doctorID);
    }

    /**
     * Processes the update form submission, including validation and database update.
     */
    private void doPut(HttpServletRequest request, HttpServletResponse response, Integer doctorID) 
            throws ServletException, IOException {
        try {
            // Validate form fields
            String validationError = validateUpdateForm(request);
            if (validationError != null) {
                request.setAttribute("error", validationError);
                DoctorModel doctor = adminDoctorListService.getDoctorDetails(doctorID);
                request.setAttribute("doctor", doctor);
                request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
                return;
            }

            // Extract form data
            String firstName = request.getParameter("doctorFirstName");
            String lastName = request.getParameter("doctorLastName");
            String dateOfBirthStr = request.getParameter("dateOfBirth");
            String gender = request.getParameter("doctorGender");
            String phoneNumber = request.getParameter("doctorPhoneNumber");
            String email = request.getParameter("doctorEmail");
            String city = request.getParameter("doctorCity");
            String district = request.getParameter("doctorDistrict");
            String municipality = request.getParameter("doctorMunicipality");
            String ward = request.getParameter("doctorWard");
            String qualification = request.getParameter("doctorQualification");
            String experience = request.getParameter("doctorExperience");
            String specialization = request.getParameter("doctorSpecialization");

            // Parse date of birth
            LocalDate dateOfBirth;
            try {
                dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ISO_DATE);
            } catch (DateTimeParseException e) {
                request.setAttribute("error", "Invalid date format. Please use YYYY-MM-DD format.");
                DoctorModel doctor = adminDoctorListService.getDoctorDetails(doctorID);
                request.setAttribute("doctor", doctor);
                request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
                return;
            }

            // Get current doctor data to preserve existing values
            DoctorModel currentDoctor = adminDoctorListService.getDoctorDetails(doctorID);
            if (currentDoctor == null) {
                request.setAttribute("error", "Unable to retrieve current doctor data.");
                request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
                return;
            }

            // Handle profile picture upload
            String imagePath = currentDoctor.getImagePath(); // Default to current image
            Part filePart = request.getPart("doctorProfile");

            if (filePart != null && filePart.getSize() > 0) {
                if (!ValidationUtil.isValidImageExtension(filePart)) {
                    request.setAttribute("error", "Invalid image format. Please upload JPG, JPEG, PNG, or GIF.");
                    request.setAttribute("doctor", currentDoctor);
                    request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
                    return;
                }

                boolean uploadSuccess = imageUtil.uploadImage(filePart, request.getServletContext().getRealPath("/"), "doctors");
                if (uploadSuccess) {
                    imagePath = imageUtil.getImageNameFromPart(filePart);
                } else {
                    request.setAttribute("error", "Failed to upload profile picture. Other information was not updated.");
                    request.setAttribute("doctor", currentDoctor);
                    request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
                    return;
                }
            }

            // Create updated DoctorModel
            DoctorModel updatedDoctor = new DoctorModel(
                doctorID, firstName, lastName, dateOfBirth, gender, phoneNumber, email, city, district,
                municipality, ward, qualification, experience, specialization, imagePath
            );

            // Update doctor info in database
            Boolean result = editDoctorService.updateDoctorInfo(updatedDoctor);

            if (result != null && result) {
                response.sendRedirect(request.getContextPath() + "/admin-doctor-list?success=Doctor+updated+successfully");
            } else {
                handleUpdateFailure(request, result);
                request.setAttribute("doctor", currentDoctor);
                request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred. Please try again later.");
            DoctorModel doctor = adminDoctorListService.getDoctorDetails(doctorID);
            request.setAttribute("doctor", doctor);
            request.getRequestDispatcher("/WEB-INF/pages/admin/edit_doctor.jsp").forward(request, response);
        }
    }

    /**
     * Sets an error message based on update failure reason.
     */
    private void handleUpdateFailure(HttpServletRequest request, Boolean updateStatus) {
        String errorMessage = (updateStatus == null) 
            ? "The server is currently unavailable. Please try again later."
            : "Failed to update doctor profile. Please check your information and try again.";
        request.setAttribute("error", errorMessage);
    }

    /**
     * Validates form fields for the update operation.
     */
    private String validateUpdateForm(HttpServletRequest request) {
        String firstName = request.getParameter("doctorFirstName");
        String lastName = request.getParameter("doctorLastName");
        String dobStr = request.getParameter("dateOfBirth");
        String gender = request.getParameter("doctorGender");
        String phoneNumber = request.getParameter("doctorPhoneNumber");
        String email = request.getParameter("doctorEmail");
        String city = request.getParameter("doctorCity");
        String district = request.getParameter("doctorDistrict");
        String municipality = request.getParameter("doctorMunicipality");
        String ward = request.getParameter("doctorWard");
        String qualification = request.getParameter("doctorQualification");
        String experienceStr = request.getParameter("doctorExperience");
        String specialization = request.getParameter("doctorSpecialization");

        if (ValidationUtil.isNullOrEmpty(firstName)) return "First name is required.";
        if (ValidationUtil.isNullOrEmpty(lastName)) return "Last name is required.";
        if (ValidationUtil.isNullOrEmpty(dobStr)) return "Date of birth is required.";
        if (ValidationUtil.isNullOrEmpty(gender)) return "Gender is required.";
        if (ValidationUtil.isNullOrEmpty(phoneNumber)) return "Phone number is required.";
        if (ValidationUtil.isNullOrEmpty(email)) return "Email is required.";
        if (ValidationUtil.isNullOrEmpty(city)) return "City is required.";
        if (ValidationUtil.isNullOrEmpty(district)) return "District is required.";
        if (ValidationUtil.isNullOrEmpty(municipality)) return "Municipality is required.";
        if (ValidationUtil.isNullOrEmpty(ward)) return "Ward number is required.";
        if (ValidationUtil.isNullOrEmpty(qualification)) return "Qualification is required.";
        if (ValidationUtil.isNullOrEmpty(experienceStr)) return "Experience is required.";
        if (ValidationUtil.isNullOrEmpty(specialization)) return "Specialization is required.";

        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        try {
            Integer.parseInt(experienceStr);
        } catch (NumberFormatException e) {
            return "Experience must be a valid number.";
        }

        if (!ValidationUtil.isValidGender(gender) && !"Prefer not to say".equalsIgnoreCase(gender)) 
            return "Gender must be 'Male', 'Female', 'Other', or 'Prefer not to say'.";
        if (!ValidationUtil.isValidEmail(email)) return "Invalid email format.";
        if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) return "Phone number must be 10 digits and start with 98.";
        if (!ValidationUtil.isValidDoB(dob)) return "Date of birth cannot be in the future.";

        String[] validSpecializations = {"Cardiology", "Dermatology", "Neurology", "Orthopedics", "Pediatrics",
                                        "Psychiatry", "General Medicine", "General Surgery", "Gynecology", "Other"};
        boolean isValidSpecialization = false;
        for (String validSpec : validSpecializations) {
            if (validSpec.equals(specialization)) {
                isValidSpecialization = true;
                break;
            }
        }
        if (!isValidSpecialization) return "Invalid specialization selected.";

        return null;
    }
}