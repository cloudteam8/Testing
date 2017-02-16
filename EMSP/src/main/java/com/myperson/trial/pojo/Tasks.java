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
@Table(name="tasks")
public class Tasks {
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="empID")
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="taskID", nullable=false, unique=true)
	private int taskID;
	
	
	
	@Column(name="taskName", nullable=false) // remove constraint unique true
	private String taskName;
	
	@Column(name="currentStatus", nullable = false) // add constraint nullable false
	private String currentStatus;
	
	@Column(name="employeeComments", nullable = false) // add constraint nullable false
	private String employeeComments;
	
	@Column(name="supervisorComment")
	private String supervisorComment;

	/**
	 * @return the taskID
	 */
	public int getTaskID() {
		return taskID;
	}

	/**
	 * @param taskID the taskID to set
	 */
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus the currentStatus to set
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * @return the supervisorComment
	 */
	public String getSupervisorComment() {
		return supervisorComment;
	}

	/**
	 * @param supervisorComment the supervisorComment to set
	 */
	public void setSupervisorComment(String supervisorComment) {
		this.supervisorComment = supervisorComment;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the employeeComments
	 */
	public String getEmployeeComments() {
		return employeeComments;
	}

	/**
	 * @param employeeComments the employeeComments to set
	 */
	public void setEmployeeComments(String employeeComments) {
		this.employeeComments = employeeComments;
	}
}
