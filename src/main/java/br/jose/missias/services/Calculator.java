package br.jose.missias.services;

import br.jose.missias.exceptions.ShouldNotDivideByZeroException;

public class Calculator {

	public int sum(int a, int b) {
 
		return a+b;
	}

	public int subtractionm(int a, int b) {
		 
		return a-b;
	}

	public int divide(int a, int b) throws ShouldNotDivideByZeroException {
		if (b == 0) {
			throw new ShouldNotDivideByZeroException();
		}
		return a/b;
	}
	
 
}
