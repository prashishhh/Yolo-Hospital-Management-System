package com.yolo.model;


public class PaymentModel {
	private int paymentID;
	private float paymentAmount;
	private boolean paymentStatus;
	
	
	public PaymentModel() {
	}


	public PaymentModel(int paymentID, float paymentAmount, boolean paymentStatus) {
		super();
		this.paymentID = paymentID;
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
	}


	public PaymentModel(float paymentAmount, boolean paymentStatus) {
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


	public boolean isPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
}
