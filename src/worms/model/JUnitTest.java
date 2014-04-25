package worms.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTest {

	
	
	
	
	Worm worm ;
	World world;
	@Test
	public void ValidWidth_legalCase(){
		world.setWidth(20);
		assertEquals(20,world.getWidth(),0);
	}
	
	@Test
	public void ValidWidth_trueCase(){
		assertTrue(world.isValidWidth(15));
	}

}
