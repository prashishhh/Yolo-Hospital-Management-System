package com.yolo.model;

public class UserAppointmentModel {
	
	private UserModel user;
	private AppointmentModel appointment;
	
	public UserAppointmentModel() {
		super();
	}

	public UserAppointmentModel(UserModel user, AppointmentModel appointment) {
		super();
		this.user = user;
		this.appointment = appointment;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public AppointmentModel getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentModel appointment) {
		this.appointment = appointment;
	}
	
}
