package com.bestbuy.fizzbuzz.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bestbuy.fizzbuzz.domain.Constants;





@ExtendWith(MockitoExtension.class)
class FizzBuzzServiceTest {

	@InjectMocks
	private FizzBuzzService fizzBuzzService;
	
	static List<String> inputList=new ArrayList<>();
	
	@BeforeAll
	public static void init() {
		IntStream.rangeClosed(1, 5).forEach(i -> inputList.add(Integer.toString(i)));
	}
	
	@Test
	void testEmptyInputList() {
		
		Assert.assertEquals(true, fizzBuzzService.generateFizzBuzz(new ArrayList<String>(), 1, 2).isEmpty());
	}
	
	@Test
	void testNullFizzNumber() {
	
		Assert.assertEquals(true, fizzBuzzService.generateFizzBuzz(inputList, null, 2).isEmpty());
	}
	
	@Test
	void testNullBuzzNumber() {
	
		Assert.assertEquals(true, fizzBuzzService.generateFizzBuzz(inputList, 1, null).isEmpty());
	}
	
	@Test
	void testNoBizzNumber() {
	
		Assert.assertTrue(!fizzBuzzService.generateFizzBuzz(inputList, 2, 6).contains(Constants.BUZZ));
	}
	
	@Test
	void testNoFizzNumber() {
	
		Assert.assertTrue(!fizzBuzzService.generateFizzBuzz(inputList, 6, 2).contains(Constants.FIZZ));
	}
	
	@Test
	void testFuzzNumber() {
		System.out.println(fizzBuzzService.generateFizzBuzz(inputList, 3, 6));
		Assert.assertTrue(fizzBuzzService.generateFizzBuzz(inputList, 3, 6).contains(Constants.FIZZ));
	}
	
	@Test
	void testBuzzNumber() {
		System.out.println(fizzBuzzService.generateFizzBuzz(inputList, 6, 3));
		Assert.assertTrue(fizzBuzzService.generateFizzBuzz(inputList, 6, 3).contains(Constants.BUZZ));
	}
	
	@Test
	void testInvalidInput() {
		inputList.add(null);
		Assert.assertTrue(fizzBuzzService.generateFizzBuzz(inputList, 2, 4).contains(Constants.INVALID_INPUT));
	}
	
	@Test
	void testInputWithLetters() {
		inputList.add("A");
		Assert.assertTrue(fizzBuzzService.generateFizzBuzz(inputList, 2, 4).contains(Constants.INVALID_INPUT));
	}
	
	@Test
	void testFizzBuzzNumber() {
		System.out.println(fizzBuzzService.generateFizzBuzz(inputList, 2, 4));
		Assert.assertTrue(fizzBuzzService.generateFizzBuzz(inputList, 2, 4).contains(Constants.FIZZBUZZ));
	}
	
	@Test
	void testNoFizzBuzzNumber() {
		System.out.println(fizzBuzzService.generateFizzBuzz(inputList, 7, 10));
		Assert.assertTrue(!fizzBuzzService.generateFizzBuzz(inputList, 7, 10).contains(Constants.FIZZBUZZ));
		Assert.assertTrue(!fizzBuzzService.generateFizzBuzz(inputList, 7, 10).contains(Constants.FIZZ));
		Assert.assertTrue(!fizzBuzzService.generateFizzBuzz(inputList, 7, 10).contains(Constants.BUZZ));
	}
}
