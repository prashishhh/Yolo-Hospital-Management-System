package com.yolo.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents a user in the system with relevant personal and account details.
 */
public class UserModel {
	
	private int userId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String phoneNumber;
	private String email;
	private String city;
	private String district;
	private String municipality;
	private String wardNo;
	private String userName;
	private String password;
	private String userRole;
	private String imagePath;
	
	/**
     * Default constructor.
     */
	public UserModel() {
		
	}

	/**
     * Full parameterized constructor including userId.
     *
     * @param userId        Unique user identifier
     * @param firstName     User's first name
     * @param lastName      User's last name
     * @param dateOfBirth   User's date of birth
     * @param gender        Gender
     * @param phoneNumber   Phone number
     * @param email         Email address
     * @param city          City
     * @param district      District
     * @param municipality  Municipality
     * @param wardNo        Ward number
     * @param userName      Username for login
     * @param password      Password for login
     * @param userRole      User role (e.g., Admin, Patient)
     * @param imagePath     Path to profile image
     */
	public UserModel(int userId, String firstName, String lastName, LocalDate dateOfBirth, String gender,
			String phoneNumber, String email, String city, String district, String municipality, String wardNo,
			String userName, String password, String userRole, String imagePath) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.city = city;
		this.district = district;
		this.municipality = municipality;
		this.wardNo = wardNo;
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
		this.imagePath = imagePath;
	}
	
	
	
	/**
     * Constructor without userId (used for user registration query in sql).
     */
	public UserModel(String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber,
			String email, String city, String district, String municipality, String wardNo, String userName,
			String password, String userRole, String imagePath) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.city = city;
		this.district = district;
		this.municipality = municipality;
		this.wardNo = wardNo;
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
		this.imagePath = imagePath;
	}
	
	
	/**
     * Partial constructor for retrieving information for admin dashboard 
     */
	public UserModel(String firstName, String lastName, String gender, LocalDate dateOfBirth, String municipality,
			String wardNo, String imagePath) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.municipality = municipality;
		this.wardNo = wardNo;
		this.imagePath = imagePath;
	}

	public UserModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getWardNo() {
		return wardNo;
	}

	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public int getAge() {
	    if (this.dateOfBirth == null) return 0;
	    return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
	}

	
	
}
