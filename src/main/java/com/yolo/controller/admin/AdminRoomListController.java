package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.service.admin.AdminRoomListService;

/**
 * Servlet implementation class AdminRoomListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-room-list" })
public class AdminRoomListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminRoomListService adminRoomListService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRoomListController() {
    	this.adminRoomListService = new AdminRoomListService();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		Get the search query from the request
		
		String searchQuery = request.getParameter("search");
		
		// set roomList as set attribute
		request.setAttribute("roomList", adminRoomListService.getRoomListDetails(searchQuery));
		request.getRequestDispatcher("/WEB-INF/pages/admin/admin_room_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
