package com.github.parallel;

import org.testng.annotations.Test;

/**
 * Add Library TestNG Test - http://toolsqa.com/selenium-webdriver/testng-introduction/
 * - http://www.seleniumeasy.com/selenium-tutorials/testing-in-multiple-browsers
 * 
 * <p>make sure to add TestNG plug-in https://marketplace.eclipse.org/content/testng-eclipse
 * and its description usage http://testng.org/doc/eclipse.html
 * 
 * @author yashwanth.m
 *
 */
public class DependsTest {
	@Test(priority = 10)
	public void test10(){
		System.out.println("10");
	}

	@Test(priority = 20, dependsOnMethods = "test10")
	public void test20() throws Exception {
		System.out.println("20, depends on 10");
		throw new Exception("My Exception");
	}

	@Test(priority = 30, dependsOnMethods = "test20")
	public void test30() {
		System.out.println("30, depends on 20");
	}

	@Test(priority = 40, dependsOnMethods = "test10")
	public void test40() {
		System.out.println("40, depends on 10");
	}
}
