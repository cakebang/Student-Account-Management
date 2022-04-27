package com.learnjava.web;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnjava.entity.Major;
import com.learnjava.entity.Student;
import com.learnjava.service.MajorService;
import com.learnjava.service.StudentService;
//
//import com.learnjava.entity.Student;
//
@RestController
@RequestMapping("/api")
public class StudentController {
	
	 public static final String KEY_USER = "__user__";
	 
	 final Logger log = LoggerFactory.getLogger(getClass());
	 
	 @Autowired
	 StudentService studentService;
	 
	 @Autowired
	 MajorService majorService;
	 
	 
	 @RequestMapping(value = "/signin", method = RequestMethod.POST,  produces = "application/json")//consumes = "application/json",
	 public Student signin(@RequestBody SignInRequest signInRequest, HttpSession session) {//@RequestBody SignInRequest signInRequest @RequestParam("email") String email, @RequestParam("password") String password
		 String email = signInRequest.email;
		 String password = signInRequest.password;
		 Student student = studentService.login(email, password);

//		 ResponseCookie cookie = ResponseCookie.from(KEY_USER, String.valueOf(id))
//				 .httpOnly(true)
//				 .path("/")
//				 .maxAge(86400)
//				 .build();
//		 ResponseEntity
//		 	.ok()
//		 	.header(HttpHeaders.SET_COOKIE,cookie.toString())
//		 	.build();
		session.setAttribute(KEY_USER, student);
		 
		 return student;
	 }
	 
	 @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json")
	 public Student profile(HttpSession session) {
		 Student student = (Student) session.getAttribute(KEY_USER);
		 return student;
	 }
	 
	 @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	 public Student register(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName,
			 				@RequestParam String password, @RequestParam String address, @RequestParam String phone,
			 				@RequestParam String major) {
		 Student student = studentService.register(email, password, firstName, lastName, address, phone, major);
		 log.info("student registered: {}", student.getEmail());
		 return student;
	 }
	 
	 @RequestMapping(value = "/update", method = RequestMethod.GET, produces = "application/json")
	 public Student update(HttpSession session) {
		 Student student = (Student) session.getAttribute(KEY_USER);
		 return student;
	 }
	 
	 @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	 public Map<String, Object> update(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName,
			 				@RequestParam String password, @RequestParam String address, @RequestParam String phone,
			 				@RequestParam String major) {
		 Map<String, Object> map = new HashMap<>();
		 try {
			 Student student = studentService.getStudentByEmail(email);
			 studentService.updateStudent(email, password, firstName, lastName, address, phone, major);
			 log.info("student update: {}", email);
			 map.put("success", student);
			 
		 } catch (RuntimeException e) {
			 map.put("error", e.getMessage());
		 }
		 return map;
	
	 }
	 
	 @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "application/json")
	 public Map<String, Object> delete(HttpSession session) {
		 Map<String, Object> map = new HashMap<>();
		 try {
			 Student student = (Student)session.getAttribute(KEY_USER);
			 studentService.deleteStudentById(student.getId());
			 map.put("success", student);
		 } catch (RuntimeException e) {
			 map.put("error", e.getMessage());
		 }
		 return map;
	 }
	 
	 public static class SignInRequest {
		 public String email;
		 public String password;
	 }

	 

}
