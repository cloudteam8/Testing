/**
 * 
 */
package com.myperson.trial.doa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myperson.trial.exception.AdException;
import com.myperson.trial.pojo.Employee;
import com.myperson.trial.pojo.Manager;
import com.myperson.trial.pojo.PerformanceFeedback;

/**
 * @author Christopher Dsouza
 *
 */
public class FeedbackDAO extends DAO {
	
	public PerformanceFeedback checkperfGiven(int empID){
		PerformanceFeedback perfFeedback = new PerformanceFeedback();
		try{
			begin();
			Query q = getSession().createQuery("from PerformanceFeedback where empID = :empID");
            q.setInteger("empID", empID);
            perfFeedback = (PerformanceFeedback)q.uniqueResult();
            commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return perfFeedback;
	}
	
	public PerformanceFeedback giveFeedback(String businessOperator, String peopleDeveloper, String valueCreator, String communicationSkills, String technicalSkills,
			String taskCompletionSkills, String uninformeLeaves, String improvementAreas, String efficientAreas, Employee employee, Manager manager){
		
		PerformanceFeedback perfFeedback = new PerformanceFeedback();
		try{
			begin();
			perfFeedback.setBusinessOperator(businessOperator);
			perfFeedback.setValueCreator(valueCreator);
			perfFeedback.setCommunicationSkills(communicationSkills);
			perfFeedback.setPeopleDeveloper(peopleDeveloper);
			perfFeedback.setTaskCompletionSkills(taskCompletionSkills);
			perfFeedback.setTechnicalSkills(technicalSkills);
			perfFeedback.setUninformedLeavesTaken(uninformeLeaves);
			perfFeedback.setImprovementAreas(improvementAreas);
			perfFeedback.setEfficientAreas(efficientAreas);
			perfFeedback.setEmployee(employee);
			perfFeedback.setManager(manager);
			
			getSession().save(perfFeedback);
			commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		
		return perfFeedback; 
	}
	
	
	public PerformanceFeedback getFeedback(int empID){
		PerformanceFeedback perfFeedback = new PerformanceFeedback();
		try{
			begin();
			String hql = "FROM PerformanceFeedback WHERE empID= :empID";
			Query q = getSession().createQuery(hql);
			q.setInteger("empID", empID);
			perfFeedback = (PerformanceFeedback)q.uniqueResult();
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		
		return perfFeedback;
	}
	
	
	public List<String> listOptions(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("Below Expectation");
		list.add("Met Expectation");
		list.add("Exceeded Expectation");
		return list;
	}
	
}
