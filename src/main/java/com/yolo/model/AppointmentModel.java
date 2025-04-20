package com.yolo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentModel {
	
	private int appointmentID;
	private String appointmentStatus;
	private LocalDate appointmentDate;
	private LocalTime appointmentStartTime;
	private LocalTime appointmentEndTime;
	private int appointmentFee;
	private DoctorModel doctor;
	private RoomModel room;
	private PaymentModel payment;
	private TreatmentModel treatment;
	
	public AppointmentModel() {
		
	}

	public AppointmentModel(int appointmentID, String appointmentStatus, LocalDate appointmentDate,
			LocalTime appointmentStartTime, LocalTime appointmentEndTime, int appointmentFee, DoctorModel doctor,
			RoomModel room, PaymentModel payment, TreatmentModel treatment) {
		super();
		this.appointmentID = appointmentID;
		this.appointmentStatus = appointmentStatus;
		this.appointmentDate = appointmentDate;
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.appointmentFee = appointmentFee;
		this.doctor = doctor;
		this.room = room;
		this.payment = payment;
		this.treatment = treatment;
	}

	public AppointmentModel( String appointmentStatus, LocalDate appointmentDate,
			LocalTime appointmentStartTime, LocalTime appointmentEndTime, int appointmentFee, DoctorModel doctor,
			RoomModel room, PaymentModel payment, TreatmentModel treatment) {
		super();
		this.appointmentStatus = appointmentStatus;
		this.appointmentDate = appointmentDate;
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.appointmentFee = appointmentFee;
		this.doctor = doctor;
		this.room = room;
		this.payment = payment;
		this.treatment = treatment;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(LocalTime appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public LocalTime getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(LocalTime appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public int getAppointmentFee() {
		return appointmentFee;
	}

	public void setAppointmentFee(int appointmentFee) {
		this.appointmentFee = appointmentFee;
	}

	public DoctorModel getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorModel doctor) {
		this.doctor = doctor;
	}

	public RoomModel getRoom() {
		return room;
	}

	public void setRoom(RoomModel room) {
		this.room = room;
	}

	public PaymentModel getPayment() {
		return payment;
	}

	public void setPayment(PaymentModel payment) {
		this.payment = payment;
	}

	public TreatmentModel getTreatment() {
		return treatment;
	}

	public void setTreatment(TreatmentModel treatment) {
		this.treatment = treatment;
	}

	

	
}
