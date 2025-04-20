package com.yolo.model;

public class TreatmentModel {
	
	private int treatmentID;
	private String treatmentName;
	private String treatmentPlan;
	private String treatmentRemark;
	public TreatmentModel() {
		
	}
	public TreatmentModel(int treatmentID, String treatmentName, String treatmentPlan, String treatmentRemark) {
		super();
		this.treatmentID = treatmentID;
		this.treatmentName = treatmentName;
		this.treatmentPlan = treatmentPlan;
		this.treatmentRemark = treatmentRemark;
	}
	public TreatmentModel(String treatmentName, String treatmentPlan, String treatmentRemark) {
		super();
		this.treatmentName = treatmentName;
		this.treatmentPlan = treatmentPlan;
		this.treatmentRemark = treatmentRemark;
	}
	public int getTreatmentID() {
		return treatmentID;
	}
	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	public String getTreatmentPlan() {
		return treatmentPlan;
	}
	public void setTreatmentPlan(String treatmentPlan) {
		this.treatmentPlan = treatmentPlan;
	}
	public String getTreatmentRemark() {
		return treatmentRemark;
	}
	public void setTreatmentRemark(String treatmentRemark) {
		this.treatmentRemark = treatmentRemark;
	}
	
	
	
}
