/**
 * 
 */
package com.myperson.trial.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Christopher Dsouza
 *
 */

@Entity
@Table(name="manager")
public class Manager extends Person {
	@Column(name="MID", nullable=false, unique=true)
	private int managerID;

	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="manager")
	private List <Employee> employees = new ArrayList<Employee>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="manager")
	private List <WorkRequests> workRequests = new ArrayList<WorkRequests>();

	/**
	 * @return the managerID
	 */
	public int getManagerID() {
		return managerID;
	}

	/**
	 * @param managerID the managerID to set
	 */
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * @return the workRequests
	 */
	public List<WorkRequests> getWorkRequests() {
		return workRequests;
	}

	/**
	 * @param workRequests the workRequests to set
	 */
	public void setWorkRequests(List<WorkRequests> workRequests) {
		this.workRequests = workRequests;
	}
}
