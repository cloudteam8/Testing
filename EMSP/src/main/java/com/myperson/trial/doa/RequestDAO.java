/**
 * 
 */
package com.myperson.trial.doa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.myperson.trial.exception.AdException;
import com.myperson.trial.pojo.Manager;
import com.myperson.trial.pojo.WorkRequests;

/**
 * @author Christopher Dsouza
 *
 */
public class RequestDAO extends DAO {
	
	public List<String> listOptions(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("Open");
		list.add("In Progress");
		list.add("Closed");
		return list;
	}
	
	
	public List<String> getRequests(){
		List<String> results = new ArrayList<String>();
		try{
			begin();
			String hql = "FROM WorkRequests WHERE requestStatus= :openStatus OR requestStatus= :progressStatus";
			Query q = getSession().createQuery(hql);
			q.setString("openStatus", "Open");
			q.setString("progressStatus", "In Progress");
			results = q.list();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}

		return results;
	}
	
	public WorkRequests createWorkRequests(String designation, String emailAddress, String firstName, String lastName, String userRole, String managerComments, String requestStatus, int employeeID, Manager mgr){
		WorkRequests workReq = new WorkRequests();
		try{
			begin();
			workReq.setDesignation(designation);
			workReq.setEmailAddress(emailAddress);
			workReq.setFirstName(firstName);
			workReq.setLastName(lastName);
			workReq.setUserRole(userRole);
			workReq.setManagerComments(managerComments);
			workReq.setRequestStatus(requestStatus);
			workReq.setEmployeeID(employeeID);
			workReq.setManager(mgr);
			getSession().save(workReq);
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		return workReq;
	}
	
	public int mgrUpdateTasks(int workRequestID, String managerComments, String status){
		int result = 0;
		try{
			begin();
			String hql = "UPDATE WorkRequests set managerComments = :managerComments, requestStatus= :requestStatus where workRequestID = :workRequestID";
			Query q = getSession().createQuery(hql);
			q.setString("managerComments", managerComments);
			q.setInteger("workRequestID", workRequestID);
			q.setString("requestStatus", status);
			
			result = q.executeUpdate();
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		return result;
	}
	

	public int adminUpdateTasks(int workRequestID, String adminComments, String status){
		int result = 0;
		try{
			begin();
			String hql = "UPDATE WorkRequests set adminComments = :adminComments, requestStatus= :requestStatus where workRequestID = :workRequestID";
			Query q = getSession().createQuery(hql);
			q.setString("adminComments", adminComments);
			q.setInteger("workRequestID", workRequestID);
			q.setString("requestStatus", status);
			
			result = q.executeUpdate();
			commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		return result;
	}
}