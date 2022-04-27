package com.learnjava.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.learnjava.entity.Student;

@Component
@Transactional
public class StudentService {
	//Its Persistence Context not autowired
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MajorService majorService;
	
	public Student getStudentById(int id) {
		Student student = em.find(Student.class, id);
		if (student == null) {
			throw new RuntimeException("Student not found by " + id);
		}
		return student;
	}
	
	public Student getStudentByEmail(String email) {
		Query q = em.createQuery("SELECT s FROM Student s WHERE s.email = :email");//Student should be same with class name Student not student
		q.setParameter("email", email.toLowerCase());
		Student student = (Student)q.getSingleResult();
		if (student == null) {
			throw new RuntimeException("Student not found by " + email);
		}
		return student;
		
	}
	
	public Student login(String email, String password) {
		Student student = getStudentByEmail(email);
		if (student == null) {
			throw new RuntimeException("Student not found by " + email);
		}
		
		if (!student.getPassword().equals(password)) {
			throw new RuntimeException(password + " is wrong");
		}	
		return student;
	}
	
	public Student register(String email, String password, String firstName, String lastName,
							String address, String phone, String majorName) {
		Student student = new Student();
		student.setEmail(email);
		student.setPassword(password);
		student.setAddress(address);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		try {
			int majorId = majorService.getMajorByName(majorName);
			student.setMajorId(majorId);
			
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		student.setPhone(phone);
		em.persist(student);// add using persist
		return student;
	}
	/*TODO 
	 * email can't be updated
	 */
	public void updateStudent(String email, String password, String firstName, String lastName,
			String address, String phone, String majorName) {
		Student student = getStudentByEmail(email);// email wont change. nee
		student.setEmail(email);
		student.setPassword(password);
		student.setAddress(address);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		try {
			int majorId = majorService.getMajorByName(majorName);
			student.setMajorId(majorId);
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		student.setPhone(phone);
		em.flush();//update using flush, persist is to create a new row
	}
	
	public void deleteStudentById(int id) {
		Student student = getStudentById(id);
		em.remove(student);
	}
	
	public void deleteStudentByEmail(String email) {
		Student student = getStudentByEmail(email);
		em.remove(student);
	}

}
