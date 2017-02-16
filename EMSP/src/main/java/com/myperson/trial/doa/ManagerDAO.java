/**
 * 
 */
package com.myperson.trial.doa;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myperson.trial.exception.AdException;
import com.myperson.trial.pojo.Manager;

/**
 * @author Christopher Dsouza
 *
 */
public class ManagerDAO extends DAO {
	public Manager createManagerUser(String firstname, String lastname, int empID, String email, String password, long phnNo, String designation, String role, String street, String city, String state, int zipcode, int mngrID){
		Manager mngr = new Manager();
		try{
			begin();
				mngr.setFirstName(firstname);
				mngr.setLastName(lastname);
				mngr.setEmpID(empID);
				mngr.setEmailAddress(email);
				mngr.setPassword(password);
				mngr.setPhoneNumber(phnNo);
				mngr.setDesignation(designation);
				mngr.setUserRole(role);
				mngr.setStreetName(street);
				mngr.setCity(city);
				mngr.setState(state);
				mngr.setZipCode(zipcode);
				mngr.setPasswordStatus('F');
				mngr.setManagerID(mngrID);
			
				getSession().save(mngr);
				commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return mngr;
	}
	
	
	public Manager searchManager(int managerEmployeeID){
		
		Manager manager = new Manager();
		try{
			begin();
			Query q = getSession().createQuery("from Manager where empID = :mngrempid");
            q.setInteger("mngrempid", managerEmployeeID);
            manager = (Manager) q.uniqueResult();
            commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return manager;
	}
	
	
}
