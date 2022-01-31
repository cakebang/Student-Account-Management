package com.learnjava.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String index(HttpSession session) {
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
		if (student != null) {
			return "profile.html";
		}
		return "signin.html";
	}
	
	
	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("register.html");
	}
	
//	@PostMapping("/register")
//	public ModelAndView register(@RequestParam("email") String email, @RequestParam("password") String password,
//			@RequestParam("name") String name) {
//		try {
//			User user = userService.register(email, password, name);
//			logger.info("user registered: {}", user.getEmail());
//
//		} catch (RuntimeException ex) {
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("email", email);
//			map.put("error", "Register fail");
//			return new ModelAndView("register.html", map);
//		}
//		//redirect to signin mapping
//		return new ModelAndView("redirect:/signin");
//	}
//	
//	@GetMapping("/signin")
//	public ModelAndView signin(HttpSession session) {
//		User user = (User)session.getAttribute(KEY_USER);
//		if (user != null) {
//			return new ModelAndView("redirect:/profile");
//		}
//		return new ModelAndView("signin.html");
//		
//	}
//	
//	@PostMapping("/signin")
//	public String doSignin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
//		try {
//			User user = userService.signin(email, password);
//			session.setAttribute(KEY_USER, user);
//		} catch (RuntimeException ex) {
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("email", email);
//			map.put("error", "Login fail");
//			return new ModelAndView("signin.html", map);
//		}
//		return new ModelAndView("redirect:/profile");
//		
//	}
//	
	@GetMapping("/profile")
	public String profile(HttpSession session) {
		Student user = (Student)session.getAttribute(KEY_USER);
		if (user == null) {
			return "redirect:signin.html";
		}
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("user", user);
		return "profile.html";
		
	}
	
	@GetMapping("/update")
	public String update(HttpSession session) {
		return "update.html";
	}
	
	@GetMapping("/delete")
	public String delete(HttpSession session) {
		return "delete.html";
	}
	
	@GetMapping("/signout")
	public String signout(HttpSession session) {
		session.removeAttribute(KEY_USER);
		return "redirect:signin.html";
	}
//	
//	@GetMapping("/signout")
//	public ModelAndView signout(HttpSession session) {
//		session.removeAttribute(KEY_USER);
//		return new ModelAndView("redirect:/signin");
//	}
	
	

}
