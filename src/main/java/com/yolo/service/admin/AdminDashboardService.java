package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;
import com.yolo.model.RoomModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

/**
 * Service class for interacting with the database to retrieve dashboard-related data for admin.
 * This class handles database connections and performs queries to fetch hospital system related information.
 */
public class AdminDashboardService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminDashboardService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	/**
     * Retrieves recent patient information from the database.
     * 
     * @return A list of UserModel objects containing patient data.
     */
	public List<UserModel> getRecentPatients() {
		
		if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT firstName, lastName, gender, dateOfBirth, municipality, wardNo, imagePath " 
        				+ "FROM User WHERE userRole = 'Patient' ORDER BY userID DESC LIMIT 5";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<UserModel> patientList = new ArrayList<>();

            while (result.next()) {
                
            	// Create and Add UserModel with userRole as Patient in the list
            	patientList.add(new UserModel(result.getString("firstName"), // First Name
            					result.getString("lastName"), // Last Name
            					result.getString("gender"),
            					result.getDate("dateOfBirth").toLocalDate(), // Date of Birth
            					result.getString("municipality"), // Municipality
            					result.getString("wardNo"), //Ward No
            					result.getString("imagePath")
            			));
            	
            }
            return patientList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
		
	}
	
	public List<DoctorModel> getRecentDoctors() {
		
		if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }
		
		String query ="Select doctorFirstName, doctorLastName, doctorSpecialization, imagePath FROM Doctor ORDER BY doctorID DESC LIMIT 5";
		
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<DoctorModel> doctorList = new ArrayList<>();

            while (result.next()) {
                
            	// Create and Add UserModel with userRole as Patient in the list
            	doctorList.add(new DoctorModel(result.getString("doctorFirstName"), // First Name
            					result.getString("doctorLastName"), // Last Name
            					result.getString("doctorSpecialization"),
            					result.getString("imagePath")
            			));
            	
            }
            return doctorList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	

	}
	
	public List<UserAppointmentModel> getRecentAppointment() {
	    if (isConnectionError) {
	        System.out.println("Connection Error!");
	        return new ArrayList<>();
	    }

	    String query = "SELECT " +
	            "u.firstName AS patientFirstName, u.lastName AS patientLastName, " +
	            "d.doctorFirstName, d.doctorLastName, " +
	            "a.appointmentDate, a.appointmentStartTime, a.appointmentEndTime," +
	            "r.roomName, r.roomType, a.appointmentStatus " +
	            "FROM Appointment a " +
	            "LEFT JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
	            "LEFT JOIN User u ON ua.userID = u.userID " +
	            "JOIN Doctor d ON a.doctorID = d.doctorID " +
	            "JOIN Room r ON a.roomID = r.roomID " +
	            "ORDER BY a.appointmentDate DESC, a.appointmentStartTime DESC " +
	            "LIMIT 5";

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet result = stmt.executeQuery();
	        List<UserAppointmentModel> appointmentList = new ArrayList<>();

	        while (result.next()) {
	            // Create UserModel for Patient
	            UserModel user = new UserModel();
	            String firstName = result.getString("patientFirstName");
	            String lastName = result.getString("patientLastName");

	            if (firstName != null && lastName != null) {
	                user.setFirstName(firstName);
	                user.setLastName(lastName);
	            } else {
	                user.setFirstName("------");
	                user.setLastName("--");
	            }

	            // Create DoctorModel
	            DoctorModel doctor = new DoctorModel();
	            doctor.setDoctorFirstName(result.getString("doctorFirstName"));
	            doctor.setDoctorLastName(result.getString("doctorLastName"));

	            // Create RoomModel
	            RoomModel room = new RoomModel();
	            room.setRoomName(result.getString("roomName"));
	            room.setRoomType(result.getString("roomType"));
	            
	            // Create AppointmentModel
	            AppointmentModel appointment = new AppointmentModel();
	            appointment.setAppointmentDate(result.getDate("appointmentDate").toLocalDate());
	            appointment.setAppointmentStartTime(result.getTime("appointmentStartTime").toLocalTime());
	            appointment.setAppointmentEndTime(result.getTime("a.appointmentEndTime").toLocalTime());
	            appointment.setAppointmentStatus(result.getString("appointmentStatus"));
	            appointment.setDoctor(doctor);
	            appointment.setRoom(room);

	            // Create UserAppointmentModel and add to list
	            UserAppointmentModel userAppointment = new UserAppointmentModel(user, appointment);
	            appointmentList.add(userAppointment);
	        }

	        return appointmentList;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	
	public String getTotalAppointments() {
		if (isConnectionError) {
			return null;
		}

		String countQuery = "SELECT COUNT(*) AS totalAppointment FROM Appointment;";
		
		try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {

			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("totalAppointment");
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String getTotalDoctors() {
		if (isConnectionError) {
			return null;
		}

		String countQuery = "SELECT COUNT(*) AS totalDoctor FROM Doctor;";
		
		try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {

			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("totalDoctor");
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getTotalPatients() {
		if (isConnectionError) {
			return null;
		}

		String countQuery = "SELECT COUNT(*) AS totalPatient FROM User WHERE userRole='Patient';";
		
		try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {

			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("totalPatient");
			} else {
				return "";
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			return null;
		}
	}
	
	public String getTotalIncome() {
	    if (isConnectionError) {
	        return null;
	    }

	    String sumQuery = "SELECT SUM(paymentAmount) AS totalIncome FROM Payment;";

	    try (PreparedStatement stmt = dbConn.prepareStatement(sumQuery)) {
	        ResultSet result = stmt.executeQuery();
	        if (result.next()) {
	            String totalIncome = result.getString("totalIncome");
	            return (totalIncome != null) ? totalIncome : "0";
	        } else {
	            return "0";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	

}
