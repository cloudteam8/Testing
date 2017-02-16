/**
 * 
 */
package com.myperson.trial.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * @author Christopher Dsouza
 *
 */
@Entity
@Table(name="PerformanceFeedback")

public class PerformanceFeedback implements Serializable{	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feedbackID", nullable=false, unique=true)
	private int feedbackID;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="empID")
	private Employee employee;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="MID")
	private Manager manager;
	
	@Column(name="valueCreator")
	private String valueCreator;
	
	@Column(name="peopleDeveloper")
	private String peopleDeveloper;
	
	@Column(name="businessOperator")
	private String businessOperator;
	
	@Column(name="communicationSkills")
	private String communicationSkills;
	
	@Column(name="technicalSkills")
	private String technicalSkills;
	
	@Column(name="taskCompletionSkills")
	private String taskCompletionSkills;
	
	@Column(name="uninformedLeavesTaken")
	private String uninformedLeavesTaken;
	
	@Column(name="improvementAreas")
	private String improvementAreas;
	
	@Column(name="efficientAreas")
	private String efficientAreas;

	/**
	 * @return the feedbackID
	 */
	public int getFeedbackID() {
		return feedbackID;
	}

	/**
	 * @param feedbackID the feedbackID to set
	 */
	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

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
	 * @return the valueCreator
	 */
	public String getValueCreator() {
		return valueCreator;
	}

	/**
	 * @param valueCreator the valueCreator to set
	 */
	public void setValueCreator(String valueCreator) {
		this.valueCreator = valueCreator;
	}

	/**
	 * @return the peopleDeveloper
	 */
	public String getPeopleDeveloper() {
		return peopleDeveloper;
	}

	/**
	 * @param peopleDeveloper the peopleDeveloper to set
	 */
	public void setPeopleDeveloper(String peopleDeveloper) {
		this.peopleDeveloper = peopleDeveloper;
	}

	/**
	 * @return the businessOperator
	 */
	public String getBusinessOperator() {
		return businessOperator;
	}

	/**
	 * @param businessOperator the businessOperator to set
	 */
	public void setBusinessOperator(String businessOperator) {
		this.businessOperator = businessOperator;
	}

	/**
	 * @return the communicationSkills
	 */
	public String getCommunicationSkills() {
		return communicationSkills;
	}

	/**
	 * @param communicationSkills the communicationSkills to set
	 */
	public void setCommunicationSkills(String communicationSkills) {
		this.communicationSkills = communicationSkills;
	}

	/**
	 * @return the technicalSkills
	 */
	public String getTechnicalSkills() {
		return technicalSkills;
	}

	/**
	 * @param technicalSkills the technicalSkills to set
	 */
	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	/**
	 * @return the taskCompletionSkills
	 */
	public String getTaskCompletionSkills() {
		return taskCompletionSkills;
	}

	/**
	 * @param taskCompletionSkills the taskCompletionSkills to set
	 */
	public void setTaskCompletionSkills(String taskCompletionSkills) {
		this.taskCompletionSkills = taskCompletionSkills;
	}

	/**
	 * @return the uninformedLeavesTaken
	 */
	public String getUninformedLeavesTaken() {
		return uninformedLeavesTaken;
	}

	/**
	 * @param uninformedLeavesTaken the uninformedLeavesTaken to set
	 */
	public void setUninformedLeavesTaken(String uninformedLeavesTaken) {
		this.uninformedLeavesTaken = uninformedLeavesTaken;
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
	 * @return the improvementAreas
	 */
	public String getImprovementAreas() {
		return improvementAreas;
	}

	/**
	 * @param improvementAreas the improvementAreas to set
	 */
	public void setImprovementAreas(String improvementAreas) {
		this.improvementAreas = improvementAreas;
	}

	/**
	 * @return the efficientAreas
	 */
	public String getEfficientAreas() {
		return efficientAreas;
	}

	/**
	 * @param efficientAreas the efficientAreas to set
	 */
	public void setEfficientAreas(String efficientAreas) {
		this.efficientAreas = efficientAreas;
	}
}
