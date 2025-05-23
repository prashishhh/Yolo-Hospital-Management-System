package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.model.RoomModel;
import com.yolo.service.admin.AddRoomService;
import com.yolo.util.ValidationUtil;

/**
 * Servlet implementation class AddRoomController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/add-room" })
public class AddRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final AddRoomService addRoomService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoomController() {
    	this.addRoomService = new AddRoomService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/admin/add_room.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
     * Handles POST requests for adding a room. Validates input and saves the room details.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate form input
            String validationMessage = validateRoomForm(request);
            if (validationMessage != null) {
                handleError(request, response, validationMessage);
                return;
            }

            // Extract RoomModel from request
            RoomModel roomModel = extractRoomModel(request);

            // Attempt to add room
            Boolean isAdded = addRoomService.addRoom(roomModel);

            if (isAdded == null) {
                handleError(request, response, "Server is under maintenance. Please try again later!");
            } else if (isAdded) {
                handleSuccess(request, response, "Room successfully added!", "/WEB-INF/pages/admin/admin_room_list.jsp");
            } else {
                handleError(request, response, "Could not add room. Please try again later!");
            }
        } catch (Exception e) {
            handleError(request, response, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }

    /**
     * Validates the room form input.
     */
    private String validateRoomForm(HttpServletRequest request) {
        String roomName = request.getParameter("roomName");
        String roomType = request.getParameter("roomType");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(roomName))
            return "Room name is required.";
        if (ValidationUtil.isNullOrEmpty(roomType))
            return "Room type is required.";

        return null; // All validations passed
    }

    /**
     * Extracts room details from the request and creates a RoomModel.
     */
    private RoomModel extractRoomModel(HttpServletRequest request) {
        String roomName = request.getParameter("roomName");
        String roomType = request.getParameter("roomType");

        // Create and return RoomModel
        return new RoomModel(roomName, roomType);
    }

    /**
     * Handles successful room addition.
     */
    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message, String redirectPage)
            throws ServletException, IOException {
        request.setAttribute("success", message);
        request.getRequestDispatcher(redirectPage).forward(request, response);
    }

    /**
     * Handles errors during room addition.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.setAttribute("roomName", request.getParameter("roomName"));
        request.setAttribute("roomType", request.getParameter("roomType"));
        request.getRequestDispatcher("/WEB-INF/pages/admin/add_room.jsp").forward(request, response);
    }

}
