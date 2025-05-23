package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.yolo.model.TreatmentModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.service.admin.AddTreatmentService;
import com.yolo.util.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/add-treatment" })
public class AddTreatmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AddTreatmentService addTreatmentService = new AddTreatmentService();

    // Note: This controller is intended for use by the admin with userID = 12 in this system prototype.
    public AddTreatmentController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserAppointmentModel> bookedAppointments = addTreatmentService.getBookedAppointments();
        request.setAttribute("bookedAppointments", bookedAppointments);
        request.getRequestDispatcher("/WEB-INF/pages/admin/add_treatment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String validationMessage = validateTreatmentForm(request);
            if (validationMessage != null) {
                handleError(request, response, validationMessage);
                return;
            }

            int appointmentId = Integer.parseInt(request.getParameter("hiddenAppointmentId"));
            String treatmentName = request.getParameter("treatmentName");
            String treatmentPlan = request.getParameter("treatmentPlan");
            String remarks = request.getParameter("remarks");

            TreatmentModel treatment = new TreatmentModel(treatmentName, treatmentPlan, remarks);
            Integer treatmentId = addTreatmentService.addTreatment(treatment);

            if (treatmentId == null) {
                handleError(request, response, "Failed to add treatment. Please try again later!");
                return;
            }

            boolean isUpdated = addTreatmentService.updateAppointmentWithTreatment(appointmentId, treatmentId);
            if (isUpdated) {
                handleSuccess(request, response, "Treatment successfully added and appointment marked as completed!");
            } else {
                handleError(request, response, "Failed to update appointment. It may not be in 'Booked' status!");
            }
        } catch (Exception e) {
            handleError(request, response, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }

    private String validateTreatmentForm(HttpServletRequest request) {
        String appointmentId = request.getParameter("hiddenAppointmentId");
        String treatmentName = request.getParameter("treatmentName");
        String treatmentPlan = request.getParameter("treatmentPlan");

        if (ValidationUtil.isNullOrEmpty(appointmentId)) {
            return "Appointment selection is required.";
        }
        if (!appointmentId.matches("\\d+")) {
            return "Invalid appointment ID.";
        }
        if (ValidationUtil.isNullOrEmpty(treatmentName)) {
            return "Treatment name is required.";
        }
        if (ValidationUtil.isNullOrEmpty(treatmentPlan)) {
            return "Treatment plan is required.";
        }
        return null;
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        List<UserAppointmentModel> bookedAppointments = addTreatmentService.getBookedAppointments();
        request.setAttribute("bookedAppointments", bookedAppointments);
        request.setAttribute("error", message);
        request.setAttribute("appointmentId", request.getParameter("hiddenAppointmentId"));
        request.setAttribute("treatmentName", request.getParameter("treatmentName"));
        request.setAttribute("treatmentPlan", request.getParameter("treatmentPlan"));
        request.setAttribute("remarks", request.getParameter("remarks"));
        request.getRequestDispatcher("/WEB-INF/pages/admin/add_treatment.jsp").forward(request, response);
    }

    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        List<UserAppointmentModel> bookedAppointments = addTreatmentService.getBookedAppointments();
        request.setAttribute("bookedAppointments", bookedAppointments);
        request.setAttribute("success", message);
        request.getRequestDispatcher("/WEB-INF/pages/admin/add_treatment.jsp").forward(request, response);
    }
}