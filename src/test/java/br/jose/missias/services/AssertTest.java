package br.jose.missias.services;

import org.junit.Assert;
import org.junit.Test;

import br.jose.missias.entities.User;

public class AssertTest {
	
	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Error was...", 1, 1);
		Assert.assertEquals(0.51234, 0.512, 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		Assert.assertEquals("ball", "ball");
		Assert.assertNotEquals("ball", "hose");
		
		Assert.assertTrue("ball".equalsIgnoreCase("Ball"));
		Assert.assertTrue("ball".startsWith("ba"));
		
		User u1 = new User("User 1");
		User u2 = new User("User 1");
		User u3 = null;
		
		Assert.assertEquals(u1, u2);
		Assert.assertSame(u2, u2);
		Assert.assertNotSame(u1, u2);
		Assert.assertNull(u3);
		
	}

}
