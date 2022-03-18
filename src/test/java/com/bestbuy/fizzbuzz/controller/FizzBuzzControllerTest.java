package com.bestbuy.fizzbuzz.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.stream.IntStream;

import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bestbuy.fizzbuzz.domain.FizzBuzzRequest;

import com.bestbuy.fizzbuzz.service.FizzBuzzService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@Import(Validator.class)
public class FizzBuzzControllerTest {

	
	  @InjectMocks
	  private FizzBuzzController fizzBuzzController;
	  
	 
	  @Mock
		FizzBuzzService service;
	
	  private MockMvc mvc;
	  
		 ArrayList<String> inputList;
		
		@BeforeAll
		public  void init() {
			 MockitoAnnotations.initMocks(this);
			inputList=new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(i -> inputList.add(Integer.toString(i)));
			mvc= MockMvcBuilders.standaloneSetup(fizzBuzzController).build();
		}
		
		
		public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}
		
	  
	  @Test
	  public void runfizzBuzzSuccess() throws Exception {
		  mvc.perform( MockMvcRequestBuilders
			      .post("/v1/fizzBuzz")
			      .content(asJsonString(FizzBuzzRequest.builder().buzzNumber(2).fizzNumber(4).input(inputList).build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().is2xxSuccessful());
	  }
	  
	
}
