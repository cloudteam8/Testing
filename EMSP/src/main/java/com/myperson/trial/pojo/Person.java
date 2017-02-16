/**
 * 
 */
package com.myperson.trial.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Christopher Dsouza
 *
 */

@Entity
@Table(name = "Person")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person {
	@Id
	@Column(name="EmployeeID", nullable=false, unique=true)
	private int empID;
	
	@Column(name="FirstName", nullable=false)
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="EmailID", nullable=false, unique=true)
	private String emailAddress;
	
	@Column(name="ContactNo")
	private long phoneNumber;
	
	@Column(name="Designation", nullable=false)
	private String designation;
	
	@Column(name="Street")
	private String streetName;
	
	@Column(name="City")
	private String city;
	
	@Column(name="State")
	private String state;
	
	@Column(name="ZipCode")
	private int zipCode;
	
	@Column(name="Password", nullable=false)
	private String password;
	
	@Column(name="Role", nullable=false)
	private String userRole;
	
	@Column(name="PasswordStatus")
	private char passwordStatus;

	/**
	 * @return the empID
	 */
	public int getEmpID() {
		return empID;
	}



	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(int empID) {
		this.empID = empID;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the phoneNumber
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the passwordStatus
	 */
	public char getPasswordStatus() {
		return passwordStatus;
	}

	/**
	 * @param passwordStatus the passwordStatus to set
	 */
	public void setPasswordStatus(char passwordStatus) {
		this.passwordStatus = passwordStatus;
	}
	
	
	
}
