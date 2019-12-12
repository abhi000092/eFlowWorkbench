package com.workbench.client.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryAnalyzer implements IRetryAnalyzer, IAnnotationTransformer {
	int reTrycount = 0;
	int reTryLimit;

	@Override
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
		// TODO Auto-generated method stub
		IRetryAnalyzer retry = arg0.getRetryAnalyzer();

		if (retry == null) {
			arg0.setRetryAnalyzer(RetryAnalyzer.class);
		}
	}

	@Override
	public boolean retry(ITestResult result) {

		if (DriverManager.getReTryFlag()) {
			reTryLimit = 3;
		} else {
			reTryLimit = 0;
		}

		if (reTrycount < reTryLimit) {
			System.out.println("Retrying " + result.getName() + " again and the count is " + (reTrycount + 1));
			reTrycount++;
			return true;
		}
		return false;

	}
}
