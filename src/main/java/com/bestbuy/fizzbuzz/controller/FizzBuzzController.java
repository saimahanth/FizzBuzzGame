package com.bestbuy.fizzbuzz.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import com.bestbuy.fizzbuzz.domain.FizzBuzzRequest;
import com.bestbuy.fizzbuzz.exception.ApiError;
import com.bestbuy.fizzbuzz.service.FizzBuzzService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
public class FizzBuzzController {
	
	private static final Long DEFERRED_TIMEOUT = 100L;
	

	@Autowired
	FizzBuzzService service;
	
	 @ApiOperation(value = "API to Play Fizz Buzz game")
	 @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully completed the game",response=List.class),
	@ApiResponse(code = 400, message = "bad input parameters")})
	@PostMapping(value="/fizzBuzz", consumes = "application/json")
	public @ResponseBody DeferredResult<ResponseEntity<List<String>>> runFizzBuzz(@Valid @RequestBody FizzBuzzRequest request,BindingResult bindingResult) {
	
		DeferredResult<ResponseEntity<List<String>>> dr = new DeferredResult<>(DEFERRED_TIMEOUT);
	
		if(bindingResult.hasErrors()) {
			ResponseEntity.badRequest().body(createApiError(bindingResult));
			dr.setErrorResult(ResponseEntity.badRequest().body(createApiError(bindingResult)));
			return dr;
		}
		log.info("Request received {}",request);
		
		List<String> response=service.generateFizzBuzz(request.getInput(), request.getFizzNumber(), request.getBuzzNumber());
		if(response==null) {
			dr.setErrorResult(ResponseEntity.internalServerError().body("Something went wrong...please try again"));
		}
		else {
			log.info("Response is {}",response);
			dr.setResult(new ResponseEntity<>(response,HttpStatus.OK));
		}
		return dr;
		
	}

	
	protected ApiError createApiError(BindingResult bindingResult) {
		  List<String> errors = new ArrayList<>();
		for (FieldError error : bindingResult.getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error :bindingResult.getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	    
	    return new ApiError(HttpStatus.BAD_REQUEST, "FizzBuzz Exception", errors);
	   
	}
	
	


}
