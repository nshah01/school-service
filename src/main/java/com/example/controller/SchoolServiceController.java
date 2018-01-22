package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.StudentServiceDelegate;

@RestController
public class SchoolServiceController {
	
	@Autowired
	StudentServiceDelegate studentServiceDelegate;
	
	@RequestMapping(value = "/getDetails/{firstname}")
	public String getStudents(@PathVariable String firstname) {
	 System.out.println("Going to call student service to get data!");
	 return studentServiceDelegate.getStudentServiceData(firstname);
	}

}
