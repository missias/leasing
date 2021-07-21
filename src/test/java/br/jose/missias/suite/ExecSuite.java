package br.jose.missias.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.jose.missias.services.CalculatorPriceRentTest;
import br.jose.missias.services.CalculatorTest;
import br.jose.missias.services.LeasingServiceTest;

//@RunWith(Suite.class)
@SuiteClasses({
	CalculatorTest.class,
	CalculatorPriceRentTest.class,
	LeasingServiceTest.class
})
public class ExecSuite {
	
	@BeforeClass
	public static void before() {
		System.out.println("before");
	}
	
	@AfterClass
	public static void after() {
		System.out.println("after");
	}

}
