package com.github.parallel;

import org.testng.annotations.Test;

public class Parallel2 {
	@Test
	public void test1() throws InterruptedException {
		for (int i = 0; i < 50; i++) {
			System.out.println("Parallel2 : "+i);
			Thread.sleep( 500 );
		}
	}
}
