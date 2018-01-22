package com.example.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class StudentServiceDelegate {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getStudentServiceData_Fallback")
	public String getStudentServiceData(String firstname) {
		
		System.out.println("Getting Student Service Data "+ firstname);
		
		String response = restTemplate
                .exchange("http://student-service:8080/getStudentByFirstname/{firstname}"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {
            }, firstname).getBody();
		
		System.out.println("Response Received as " + response + " -  " + new Date());
		
		return "NORMAL FLOW !!! - First Name -  " + firstname + " :::  " +
        " Student Details " + response + " -  " + new Date();
	}
	
	@SuppressWarnings("unused")
	private String getStudentServiceData_Fallback(String firstname) {
		 
        System.out.println("Student Service is down!!! fallback route enabled...");
 
        return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
                    " Service will be back shortly - " + new Date();
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
