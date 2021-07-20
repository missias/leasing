package br.jose.missias.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * TDD CICLE
 * bay steps
 * RED -> GREEN -> REFACTOR
 */

import br.jose.missias.exceptions.ShouldNotDivideByZeroException;

public class CalculatorTest {
	
	private Calculator calc;
	
	@Before
	public void setup( ) {
		calc = new Calculator();
	}
	
	
	@Test
	public void shouldSumTwoNumbers() {
		
		// scenario
		int a = 5;
		int b = 3;
		 
		
		// action
		
		int result = calc.sum(a,b);
		
		// validation
		
		Assert.assertEquals(8,result);
		
	}
	
	@Test
	public void shouldSubtractionTwoNumbers() {
		
				// scenario
				int a = 5;
				int b = 3;
			 
				
				// action
				
				int result = calc.subtractionm(a,b);
				
				// validation
				
				Assert.assertEquals(2,result);
		
	}
	
	@Test
	public void shouldDivideTwoNumbers() throws ShouldNotDivideByZeroException {
		
		// scenario
		int a = 4;
		int b = 2;
		
		// action
		
		int result = calc.divide(a,b);
		
		// validation
		
		Assert.assertEquals(2,result);
		
	}
	
	@Test(expected=ShouldNotDivideByZeroException.class)
	public void shouldThrowAnExceptionIfDivideByZero() throws ShouldNotDivideByZeroException {
		// scenario
		int a = 10;
		int b = 0;
		
		// action
		calc.divide(a, b);
		
		// validation
		
	}

}
