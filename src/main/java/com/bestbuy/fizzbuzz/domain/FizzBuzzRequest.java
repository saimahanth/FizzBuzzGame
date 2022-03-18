package com.bestbuy.fizzbuzz.domain;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FizzBuzzRequest {
	
	@ApiModelProperty(required = true, name="Input Numbers", value = "Input List for FizzBuzz")
	@NotNull(message="Input cannot be null") 
	private List<String> input;
	
	@NotNull 
	@ApiModelProperty(required = true,name="fizzNumber", value = "Number to be printed as Fizz")
	@Digits(message="fizzNumber can only be Integer", fraction = 0, integer = 10)
	@Range(min=1,max=9,message="fizzNumber can only be between 1 to 9")
	private Integer fizzNumber;
	
	@ApiModelProperty(required = true, name="buzzNumber", value = "Number to be printed as Buzz")
	@Range(min=1,max=9,message="BuzzNumber can only be between 1 to 9")
	private Integer buzzNumber;
	
	
}
