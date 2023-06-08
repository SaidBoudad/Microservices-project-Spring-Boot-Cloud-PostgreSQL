package com.saidboudad.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class StudentApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		// Perform assertions to check the application context
		assertNotNull(applicationContext, "Application context should not be null");
		assertTrue(((SmartLifecycle) applicationContext).isRunning(), "Application context should be running");
		// Add more assertions as per your specific requirements
	}

}
