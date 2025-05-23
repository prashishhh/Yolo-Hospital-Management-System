package com.yolo.model;

import java.time.LocalDate;

public class DoctorModel {
	private int doctorID;
	private String doctorFirstName;
	private String doctorLastName;
	private LocalDate doctorDateOfBirth;
	private String doctorGender;
	private String doctorPhoneNumber;
	private String doctorEmail;
	private String doctorCity;
	private String doctorDistrict;
	private String doctorMunicipality;
	private String doctorWardNo;
	private String doctorQualification;
	private String doctorExperience;
	private String doctorSpecialization;
	private String imagePath;
	
	public DoctorModel() {
		
	}

	public DoctorModel(int doctorID, String doctorFirstName, String doctorLastName, LocalDate doctorDateOfBirth,
			String doctorGender, String doctorPhoneNumber, String doctorEmail, String doctorCity, String doctorDistrict,
			String doctorMunicipality, String doctorWardNo, String doctorQualification,
			String doctorExperience, String doctorSpecialization, String imagePath) {
		super();
		this.doctorID = doctorID;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorDateOfBirth = doctorDateOfBirth;
		this.doctorGender = doctorGender;
		this.doctorPhoneNumber = doctorPhoneNumber;
		this.doctorEmail = doctorEmail;
		this.doctorCity = doctorCity;
		this.doctorDistrict = doctorDistrict;
		this.doctorMunicipality = doctorMunicipality;
		this.doctorWardNo = doctorWardNo;
		this.doctorQualification = doctorQualification;
		this.doctorExperience = doctorExperience;
		this.doctorSpecialization = doctorSpecialization;
		this.imagePath = imagePath;
	}

	public DoctorModel(String doctorFirstName, String doctorLastName, LocalDate doctorDateOfBirth, String doctorGender,
			String doctorPhoneNumber, String doctorEmail, String doctorCity, String doctorDistrict,
			String doctorMunicipality, String doctorWardNo,String doctorQualification,
			String doctorExperience, String doctorSpecialization, String imagePath) {
		super();
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorDateOfBirth = doctorDateOfBirth;
		this.doctorGender = doctorGender;
		this.doctorPhoneNumber = doctorPhoneNumber;
		this.doctorEmail = doctorEmail;
		this.doctorCity = doctorCity;
		this.doctorDistrict = doctorDistrict;
		this.doctorMunicipality = doctorMunicipality;
		this.doctorWardNo = doctorWardNo;
		this.doctorQualification = doctorQualification;
		this.doctorExperience = doctorExperience;
		this.doctorSpecialization = doctorSpecialization;
		this.imagePath = imagePath;
	}
	
	

	public DoctorModel(String doctorFirstName, String doctorLastName, String doctorSpecialization,	String imagePath) {
		super();
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorSpecialization = doctorSpecialization;
		this.imagePath = imagePath;
	}
	
	public DoctorModel(int doctorID, String doctorFirstName, String doctorLastName, String doctorSpecialization,	String imagePath) {
		this.doctorID = doctorID;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorSpecialization = doctorSpecialization;
		this.imagePath = imagePath;
	}
	
	
	
	public DoctorModel (String doctorFirstName, String doctorLastName, String doctorQualification,
			String doctorSpecialization, String imagePath, int doctorID) {
		super();
		this.doctorID = doctorID;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorQualification = doctorQualification;
		this.doctorSpecialization = doctorSpecialization;
		this.imagePath = imagePath;
	}
	

	public DoctorModel (String doctorFirstName,  int doctorID, String doctorLastName, String doctorQualification,
			String doctorSpecialization, String doctorExperience, String imagePath) {
		super();
		this.doctorID = doctorID;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorQualification = doctorQualification;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
		this.imagePath = imagePath;
	}

	public DoctorModel(int doctorID, String doctorFirstName, String doctorLastName,
			String doctorExperience,  String doctorPhoneNumber, String doctorSpecialization) {
		super();
		this.doctorID = doctorID;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorPhoneNumber = doctorPhoneNumber;
		this.doctorExperience = doctorExperience;
		this.doctorSpecialization = doctorSpecialization;
	}
	
	

	public DoctorModel(int doctorID, String doctorFirstName, String doctorLastName) {
		super();
		this.doctorID = doctorID;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}

	public String getDoctorLastName() {
		return doctorLastName;
	}

	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}

	public LocalDate getDoctorDateOfBirth() {
		return doctorDateOfBirth;
	}

	public void setDoctorDateOfBirth(LocalDate doctorDateOfBirth) {
		this.doctorDateOfBirth = doctorDateOfBirth;
	}

	public String getDoctorGender() {
		return doctorGender;
	}

	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}

	public String getDoctorPhoneNumber() {
		return doctorPhoneNumber;
	}

	public void setDoctorPhoneNumber(String doctorPhoneNumber) {
		this.doctorPhoneNumber = doctorPhoneNumber;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getDoctorCity() {
		return doctorCity;
	}

	public void setDoctorCity(String doctorCity) {
		this.doctorCity = doctorCity;
	}

	public String getDoctorDistrict() {
		return doctorDistrict;
	}

	public void setDoctorDistrict(String doctorDistrict) {
		this.doctorDistrict = doctorDistrict;
	}

	public String getDoctorMunicipality() {
		return doctorMunicipality;
	}

	public void setDoctorMunicipality(String doctorMunicipality) {
		this.doctorMunicipality = doctorMunicipality;
	}

	public String getDoctorWardNo() {
		return doctorWardNo;
	}

	public void setDoctorWardNo(String doctorWardNo) {
		this.doctorWardNo = doctorWardNo;
	}

	public String getDoctorQualification() {
		return doctorQualification;
	}

	public void setDoctorQualification(String doctorQualification) {
		this.doctorQualification = doctorQualification;
	}

	public String getDoctorExperience() {
		return doctorExperience;
	}

	public void setDoctorExperience(String doctorExperience) {
		this.doctorExperience = doctorExperience;
	}

	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}

	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
