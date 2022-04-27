package com.learnjava.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.learnjava.entity.Student;
import com.learnjava.service.StudentService;
//import com.learnjava.service.UserService;

@Controller
public class WebController {
	
	public static final String KEY_USER = "__user__";
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	StudentService studentService;
//	
//	@PostMapping(value="/rest", consumes = "applications/json; charset=utf-8", produces= "applications/json; charset=utf-8" )
//	@ResponseBody
//	public String rest(@RequestBody User user) {
//		return "{\"rest support\" : true}";
//	}
	
	
	@GetMapping("/")
	public String index() {
//		Student user = (Student)session.getAttribute(KEY_USER);
//		Map<String, Object> model = new HashMap<>();
//		
//		if (user != null) {
//			model.put("student", user);
//		}
		
		
		return "index2.html";
	}
	
	@GetMapping("/signin")
	public String signin(HttpSession session) {
 		Student student = (Student)session.getAttribute(KEY_USER);
		if (student == null) {
			return "signin.html";
		}
		return "profile.html";
	}
	
	
	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("register.html");
	}
	

	@GetMapping("/profile")
	public String profile(HttpSession session) {
 		Student student = (Student)session.getAttribute(KEY_USER);
		if (student == null) {
			return "redirect:signin.html";
		}
		
		return "profile.html";
		
	}
	
	@GetMapping("/update")
	public String update() {
		return "update.html";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "delete.html";
	}
	
	@GetMapping("/signout")
	public String signout( HttpSession session) {
		session.removeAttribute(KEY_USER);
		return "redirect:signin.html";
	}

	
	

}
