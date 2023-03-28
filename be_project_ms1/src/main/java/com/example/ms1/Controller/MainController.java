package com.example.ms1.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.example.ms1.Service.ListEmployeeApi;
import com.example.ms1.Service.RandomEmpApiCall;
import com.example.ms1.dto.*;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {
	
	@Autowired
	private RandomEmpApiCall randomEmpApiCall;
	
	Logger logger= Logger.getLogger(MainController.class.getName());
	
	@RequestMapping(value = "/randomemp", method = RequestMethod.POST)
	@ResponseBody
	public void randomcallempployee()
	{ 
		while(true) 
	    {	
			logger.info("entering randomcallempployee method");
	    	randomEmpApiCall.randomempapicall(); 
	    	logger.info("entering randomcallempployee method");
	    }
	}
	
}
