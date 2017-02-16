/**
 * 
 */
package com.myperson.trial.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Christopher Dsouza
 *
 */

@Entity
@Table(name="WorkRequests")
public class WorkRequests {
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="MID")
	private Manager manager;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="workRequestID", nullable=false, unique=true)
	private int workRequestID;
	
	@Column(name="employeeID", nullable=false, unique=true)
	private int employeeID;
	
	@Column(name="designation", nullable=false)
	private String designation;
	
	@Column(name="emailAddress", nullable=false)
	private String emailAddress;
	
	@Column(name="firstName", nullable=false)
	private String firstName;
	
	@Column(name="lastName", nullable=false)
	private String lastName;
	
	@Column(name="userRole", nullable=false)
	private String userRole;
	
	@Column(name="managerComments")
	private String managerComments;
	
	@Column(name="adminComments")
	private String adminComments;
	
	@Column(name="requestStatus")
	private String requestStatus;

	/**
	 * @return the manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * @return the workRequestID
	 */
	public int getWorkRequestID() {
		return workRequestID;
	}

	/**
	 * @param workRequestID the workRequestID to set
	 */
	public void setWorkRequestID(int workRequestID) {
		this.workRequestID = workRequestID;
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
	 * @return the managerComments
	 */
	public String getManagerComments() {
		return managerComments;
	}

	/**
	 * @param managerComments the managerComments to set
	 */
	public void setManagerComments(String managerComments) {
		this.managerComments = managerComments;
	}

	/**
	 * @return the adminComments
	 */
	public String getAdminComments() {
		return adminComments;
	}

	/**
	 * @param adminComments the adminComments to set
	 */
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}

	/**
	 * @return the requestStatus
	 */
	public String getRequestStatus() {
		return requestStatus;
	}

	/**
	 * @param requestStatus the requestStatus to set
	 */
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
}
