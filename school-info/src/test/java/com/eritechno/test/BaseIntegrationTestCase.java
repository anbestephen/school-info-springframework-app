package com.eritechno.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eritechno.test.config.SchoolTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SchoolTestConfig.class)
public class BaseIntegrationTestCase {

	@Test
	public void dumyTest() {

	}

}
