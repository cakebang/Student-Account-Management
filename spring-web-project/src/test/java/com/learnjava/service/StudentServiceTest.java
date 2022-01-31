package com.learnjava.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.learnjava.AppConfig;
import com.learnjava.entity.Student;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {AppConfig.class})
@WebAppConfiguration
public class StudentServiceTest {
	
	@Autowired
	StudentService service;
	
	@Before
	public void initi() {
		
	}

	@Test
	public void testRegister() {
		Student student = service.register("test@email.com", "password", "testFN", "testLN", "testAddress", "testPhone", "testMajor");
		Student studentToTest = service.getStudentByEmail("test@email.com");
	 	assertThat(student.getFirstName(), equalTo(studentToTest.getFirstName()));
	 	service.deleteStudentByEmail("test@email.com");
	
	}
	
	@Test
	public void testGetStudentByEmail() {
		Student student = service.register("test@email.com", "password", "testFN", "testLN", "testAddress", "testPhone", "testMajor");
		student = service.getStudentByEmail("test@email.com");
		assertThat(student, hasProperty("email", equalTo("test@email.com")));
		service.deleteStudentByEmail("test@email.com");
	}
	
	@Test
	public void testLogin() {
		Student student = service.register("test@email.com", "password", "testFN", "testLN", "testAddress", "testPhone", "testMajor");
		student = service.login("test@email.com", "password");
		assertThat(student, hasProperty("email", equalTo("test@email.com")));
		service.deleteStudentByEmail("test@email.com");
	}
	
	@Test
	public void testUpdate() {
		Student student = service.register("test@email.com", "password", "testFN", "testLN", "testAddress", "testPhone", "testMajor");
		service.updateStudent("test@email.com", "password", "updateFN", "testLN", "testAddress", "testPhone", "testMajor");
		student = service.getStudentByEmail("test@email.com");
		assertThat(student.getFirstName(), equalTo("updateFN"));
		service.deleteStudentByEmail("test@email.com");
	}
	
	@Test(expected = RuntimeException.class)
	public void testDeleteStudentByEmail() {
		Student student = service.register("test@email.com", "password", "testFN", "testLN", "testAddress", "testPhone", "testMajor");
		service.deleteStudentByEmail("test@email.com");
		service.getStudentByEmail("test@email.com");
	}
	
	

}
