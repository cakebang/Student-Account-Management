package com.learnjava.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.learnjava.entity.Major;
import com.learnjava.entity.Student;

@Component
@Transactional
public class MajorService {
	
	@PersistenceContext
	EntityManager em;
	
//	public Map<String, Integer> getMajorList() {
//		Map<String, Integer> map = new HashMap<>();
//		em.cre
//	}
	
	public Major getMajorById(int id) {
		Major major = em.find(Major.class, id);
		if (major == null)
			throw new RuntimeException("major not found by "+ id);
		return major;
	}
	
	public int getMajorByName(String name) {
		Query query = em.createQuery("SELECT m FROM Major m WHERE m.name = :name");
		query.setParameter("name", name);
		Major major = (Major)query.getSingleResult();
		if (major == null) {
			throw new RuntimeException("Major not found by " + name);
		}
		return major.getId();
	}
	
	public Major getMajorByStudent(Student student) {
		Major major = this.getMajorById(student.getId());
		return major;
	}
	
	

}
