/**
 * 
 */
package com.myperson.trial.doa;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myperson.trial.exception.AdException;
import com.myperson.trial.pojo.Employee;

/**
 * @author Christopher Dsouza
 *
 */
public class EmployeeDAO extends DAO{
	public Employee getEmployee(int empID){
		Employee emp = new Employee();
		try{
			begin();
			Query q = getSession().createQuery("from Employee where empID = :empID");
			q.setInteger("empID", empID);
            emp = (Employee) q.uniqueResult();
            commit();			
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return emp;
	}
}