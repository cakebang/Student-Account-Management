package com.learnjava.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learnjava.AppConfig;
import com.learnjava.entity.Student;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class StudentServiceTest {
	
	@Autowired
	StudentService service;

	@Test
	public void testRegister() {
		Student student = service.register("test@email.com", "password", "testFN", "testLN", "testAddress", "testPhone", "testMajor");
		Student studentToTest = service.getStudentByEmail("test@email.com");
	 	assertThat(student.getFirstName(), equalTo(studentToTest.getFirstName()));
	 	service.deleteStudentByEmail("test@email.com");
	
	}

}
