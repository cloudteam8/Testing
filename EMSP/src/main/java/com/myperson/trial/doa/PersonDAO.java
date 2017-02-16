/**
 * 
 */
package com.myperson.trial.doa;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myperson.trial.exception.AdException;
import com.myperson.trial.pojo.Employee;
import com.myperson.trial.pojo.Manager;
import com.myperson.trial.pojo.Person;

/**
 * @author Christopher Dsouza
 *
 */
public class PersonDAO extends DAO{
	
	public Employee createUser(String firstname, String lastname, int empID, String email, String password, long phnNo, String designation, String role, String street, String city, String state, int zipcode, Manager mngr){
		Employee emp = new Employee();
		try{
			begin();
			emp.setFirstName(firstname);
			emp.setLastName(lastname);
			emp.setEmpID(empID);
			emp.setEmailAddress(email);
			emp.setPassword(password);
			emp.setPhoneNumber(phnNo);
			emp.setDesignation(designation);
			emp.setUserRole(role);
			emp.setStreetName(street);
			emp.setCity(city);
			emp.setState(state);
			emp.setZipCode(zipcode);
			emp.setPasswordStatus('F');
			emp.setManager(mngr);
			
			getSession().save(emp);
			commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return emp;
	}
	
	
	public int getManagerEmployeeID(String email){
		Person person = new Person();
		int mngrEmployeeID=0;
		try{
			begin();
			Query q = getSession().createQuery("from Person where EmailID = :email");
            q.setString("email", email);
            person = (Person) q.uniqueResult();
            if(person!=null)
            	mngrEmployeeID = person.getEmpID();
            commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return mngrEmployeeID;
	}
	
	
	
	public Person getUserRole(String email, String password)throws AdException{
		Person person = new Person();
		try{
			begin();
			Query q = getSession().createQuery("from Person where EmailID = :email and Password = :password");
            q.setString("email", email);
            q.setString("password", password);
            person = (Person) q.uniqueResult();
            commit();			
		}catch(HibernateException e){
			rollback();
			throw new AdException("Exception while fetching user role: " + e.getMessage());
		}
		return person;
	}
	
	
	public Person searchUser(String emailID)throws AdException{
		
		Person person = new Person();
		try{
			begin();
			Query q = getSession().createQuery("from Person where EmailID = :email");
            q.setString("email", emailID);
            person = (Person) q.uniqueResult();
            commit();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Exception while searching user details: " + e.getMessage());
		}
		return person;
	}
	
	
public Person getUser(int empID)throws AdException{
		
		Person person = new Person();
		try{
			begin();
			Query q = getSession().createQuery("from Person where empID = :empID");
            q.setInteger("empID", empID);
            person = (Person) q.uniqueResult();
            commit();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Exception while fetching user details: " + e.getMessage());
		}
		return person;
	}
	
	
	public void deleteUser(int empID) throws AdException{
		Person person = new Person();
		try{
			begin();
			person.setEmpID(empID);
			getSession().delete(person);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Exception while deleting user: " + e.getMessage());
		}
	}
	
	
	public int updateUserRole(int empID, String role){
		int result = 0;
		try{
			begin();
			String hql = "UPDATE Person set userRole = :role where empID = :empID";
			Query q = getSession().createQuery(hql);
			q.setString("role", role);
			q.setInteger("empID", empID);
			
			result = q.executeUpdate();
			commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int updateUser(int empID, String firstName, String lastName, String password, long phnNo, String street, String city, String state, int zipcode)throws AdException{
		int result = 0;
		try{
			begin();
			String hql = "UPDATE Person set firstName = :firstName, lastName= :lastName, password= :password, phoneNumber= :phoneNumber, "
					+ "streetName= :streetName, city= :city, state= :state, zipCode= :zipCode where empID = :empID";
			Query q = getSession().createQuery(hql);
			q.setInteger("empID", empID);
			q.setString("firstName", firstName);
			q.setString("lastName", lastName);
			q.setString("password", password);
			q.setLong("phoneNumber", phnNo);
			q.setString("streetName", street);
			q.setString("city", city);
			q.setString("state", state);
			q.setInteger("zipCode", zipcode);
			
			result = q.executeUpdate();
			commit();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Exception while updating user details: " + e.getMessage());
		}
		return result;
	}
}