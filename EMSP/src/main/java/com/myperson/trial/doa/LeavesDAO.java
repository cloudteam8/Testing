/**
 * 
 */
package com.myperson.trial.doa;

import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;

import org.hibernate.Query;

import com.myperson.trial.exception.AdException;
import com.myperson.trial.pojo.Employee;
import com.myperson.trial.pojo.Leaves;

/**
 * @author Christopher Dsouza
 *
 */
public class LeavesDAO extends DAO{
	public Leaves addLeaves(Date leaveStartDate, Date leaveEndDate, Employee emp){
		Leaves lea = new Leaves();
		try{
		begin();
		lea.setEmployee(emp);
		lea.setLeaveStartDate(leaveStartDate);
		lea.setLeaveEndDate(leaveEndDate);
		lea.setApprovalStatus('R');
		lea.setRejectReason("");
		getSession().save(lea);
		commit();
		}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		return lea;
	}
	

	//public Leaves updateLeaveStatus(Date leaveStartDate, Date leaveEndDate, Employee emp, int leaveID, char approvalStatus, String comments){
	public int updateLeaveStatus(int leaveID, char approvalStatus, String comments){

		int result = 0;
		try{
		begin();
		
		
		String hql = "UPDATE Leaves set approvalStatus = :approvalStatus, rejectReason = :comments where leaveID = :leaveID";
		Query q = getSession().createQuery(hql);
		q.setCharacter("approvalStatus", approvalStatus);
		q.setString("comments", comments);
		q.setInteger("leaveID", leaveID);
		
		result = q.executeUpdate();
		

		commit();}catch(Exception e){
			rollback();
			e.printStackTrace();
		}
		
		return result;
	}
	

}