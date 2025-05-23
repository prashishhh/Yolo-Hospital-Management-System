package com.yolo.model;


public class PaymentModel {
	private int paymentID;
	private float paymentAmount;
	private String paymentStatus;
	
	
	public PaymentModel() {
	}


	public PaymentModel(int paymentID, float paymentAmount, String paymentStatus) {
		super();
		this.paymentID = paymentID;
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
	}


	public PaymentModel(float paymentAmount, String paymentStatus) {
		super();
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
	}


	public int getPaymentID() {
		return paymentID;
	}


	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}


	public float getPaymentAmount() {
		return paymentAmount;
	}


	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	public String isPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
}
