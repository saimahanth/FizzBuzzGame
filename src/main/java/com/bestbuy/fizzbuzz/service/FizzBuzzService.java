package com.bestbuy.fizzbuzz.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.bestbuy.fizzbuzz.domain.Constants;

@Service
public class FizzBuzzService {
	
	
	 
	 

	public List<String> generateFizzBuzz(List<String> input,Integer fizzNumber,Integer buzzNummber) {
		List<String> response=new ArrayList<>();
		if(input.isEmpty() || (fizzNumber==null || buzzNummber==null) || 
				(fizzNumber==0  && buzzNummber==0) ) {
			return response;
		}
		
		input.forEach(entry ->{
			if(entry!=null &&  NumberUtils.isDigits(entry)) {
				validateEntryForFizzBuzz(fizzNumber,buzzNummber,entry,response);
			}
			else {
				response.add(Constants.INVALID_INPUT);
			}
		});
		return response;
	}
	
	

	private String getDividedByString(String entry,int number) {
		return new StringBuilder().append(Constants.DIVIDED).append(entry).append(Constants.BY).append(Integer.toString(number)).toString();
	}
	
	
	
	private void validateEntryForFizzBuzz(Integer fizzNumber,Integer buzzNummber,String entry,List<String> response) {
		if(isFizzOrBuzz(fizzNumber,entry) && isFizzOrBuzz(buzzNummber,entry)) {
			response.add(Constants.FIZZBUZZ);
		}
		else if(isFizzOrBuzz(fizzNumber,entry)) {
			response.add(Constants.FIZZ);
			response.add(getDividedByString(entry,buzzNummber));
		}
		else if(isFizzOrBuzz(buzzNummber,entry)){
			response.add(Constants.BUZZ);
			response.add(getDividedByString(entry,fizzNumber));
		}
		else {
			response.add(getDividedByString(entry,buzzNummber));
			response.add(getDividedByString(entry,fizzNumber));
		}
	}

 
	
	private boolean isFizzOrBuzz(Integer fizBuzNum, String entry) {
		return (Float.parseFloat(entry) % fizBuzNum ==0);
	}
	
	
}
