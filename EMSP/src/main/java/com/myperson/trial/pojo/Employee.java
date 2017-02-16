/**
 * 
 */
package com.myperson.trial.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author Christopher Dsouza
 *
 */
@Entity
@Table(name="employee")
@PrimaryKeyJoinColumn(name="empID")
public class Employee extends Person {
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="MID")
	private Manager manager;
	
	@Transient
	private String managerEmail;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="employee")
	private List <Leaves> leav = new ArrayList<Leaves>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="employee")
	private List <Tasks> tasks = new ArrayList<Tasks>();
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="employee", cascade=CascadeType.ALL)
    private PerformanceFeedback performanceFeedback;
	
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
	 * @return the managerEmail
	 */
	public String getManagerEmail() {
		return managerEmail;
	}

	/**
	 * @param managerEmail the managerEmail to set
	 */
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	/**
	 * @return the leav
	 */
	public List<Leaves> getLeav() {
		return leav;
	}

	/**
	 * @param leav the leav to set
	 */
	public void setLeav(List<Leaves> leav) {
		this.leav = leav;
	}

	/**
	 * @return the tasks
	 */
	public List<Tasks> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return the performanceFeedback
	 */
	public PerformanceFeedback getPerformanceFeedback() {
		return performanceFeedback;
	}

	/**
	 * @param performanceFeedback the performanceFeedback to set
	 */
	public void setPerformanceFeedback(PerformanceFeedback performanceFeedback) {
		this.performanceFeedback = performanceFeedback;
	}
}
