package com.learnjava.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learnjava.entity.Major;
import com.learnjava.entity.Student;
import com.learnjava.service.MajorService;

@RestController
@RequestMapping("/api/major")
public class MajorController {
	
	public static final String KEY_USER = "__user__";
	
	@Autowired
	MajorService majorService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json")
	public Major profile(HttpSession session) {
		Student student = (Student)session.getAttribute(KEY_USER);
		Major major = majorService.getMajorByStudent(student);
		return major;
	}
	
	

}
