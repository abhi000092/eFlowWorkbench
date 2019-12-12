package com.workbench.client.config;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.workbench.client.core.DriverManager;
import com.workbench.client.core.RandomGeneratorUtils;

public class CredentialsProvider {

	@DataProvider(name = "RegressionCredentials")
	public static Object[] getUserCredentials(Method m) {
		if (m.getName().equalsIgnoreCase("loginwithInvalidCredentialsTest")) {

			return new Object[] { RandomGeneratorUtils.generateRandomUserId() };

		} else {

			return new Object[] { DriverManager.getloginid() };
		}

	}
}