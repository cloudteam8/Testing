/**
 * 
 */
package com.myperson.trial.doa;

import org.hibernate.Query;

import com.myperson.trial.exception.AdException;
import com.myperson.trial.pojo.Employee;
import com.myperson.trial.pojo.Tasks;

/**
 * @author Christopher Dsouza
 *
 */
public class TasksDAO extends DAO {
	
	public Tasks createTasks(String taskName,String status, String employeeComments, String managerComments, Employee emp){
		Tasks tasks = new Tasks();
		try{
			begin();
			tasks.setEmployee(emp);
			tasks.setTaskName(taskName);
			tasks.setCurrentStatus(status);
			tasks.setSupervisorComment(managerComments);
			tasks.setEmployeeComments(employeeComments);
			getSession().save(tasks);
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		return tasks;
	}
	

	public int updateTasks(int taskID, String managerComments) {
		int result = 0;
		try{
			begin();
			String hql = "UPDATE Tasks set supervisorComment = :managerComments where taskID = :taskID";
			Query q = getSession().createQuery(hql);
			q.setString("managerComments", managerComments);
			q.setInteger("taskID", taskID);
			
			result = q.executeUpdate();
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public int updateEmployeeTasks(int taskID, String currentStatus, String employeeComments){
		int result = 0;
		
		try{
			begin();
			String hql = "UPDATE Tasks set employeeComments = :employeeComments, currentStatus = :currentStatus where taskID = :taskID";
			Query q = getSession().createQuery(hql);
			q.setString("employeeComments", employeeComments);
			q.setString("currentStatus", currentStatus);
			q.setInteger("taskID", taskID);
			
			result = q.executeUpdate();
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int deleteTasks(int taskID){
		int result =0;
		try{
			begin();
			String hql = "DELETE from Tasks where taskID = :taskID";
			Query q = getSession().createQuery(hql);
			q.setInteger("taskID", taskID);
			result = q.executeUpdate();
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		return result;
	}

}