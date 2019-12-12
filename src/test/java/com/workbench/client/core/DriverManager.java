package com.workbench.client.core;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.workbench.client.pages.DataSourceConfigPage;

public class DriverManager {
	private static ThreadLocal<EventFiringWebDriver> driver = new ThreadLocal<EventFiringWebDriver>();
	private static ThreadLocal<String> browserName = new ThreadLocal<String>();
	private static ThreadLocal<String> environmentName = new ThreadLocal<String>();
	private static ThreadLocal<String> loginid = new ThreadLocal<String>();
//private static ThreadLocal<String> password = new ThreadLocal<String>();
	private static ThreadLocal<Log> log = new ThreadLocal<Log>();
	private static ThreadLocal<Boolean> reTryFlag = new ThreadLocal<Boolean>();
	private static ThreadLocal<String> dataBase = new ThreadLocal<String>();
	private static ThreadLocal<DataSourceConfigPage> dataBaseConfig = new ThreadLocal<DataSourceConfigPage>();
	
	public static EventFiringWebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(EventFiringWebDriver webdriver) {
		driver.set(webdriver);
	}

	public static String getBrowserName() {
		return browserName.get();
	}

	public static void setBrowserName(String name) {
		browserName.set(name);
	}

	public static String getEnvironmentName() {
		return environmentName.get();
	}

	public static void setEnvironmentName(String name) {
		environmentName.set(name);
	}

	public static String getloginid() {
		return loginid.get();
	}

	public static void setloginid(String name) {
		loginid.set(name);
	}

//	public static String getPassword() {
//		return password.get();
//	}
//
//	public static void setPassword(String passwordValue) {
//		password.set(passwordValue);
//	}

	public static ThreadLocal<Log> getLog() {
		return log;
	}

	public static void setLog(Log logObject) {
		log.set(logObject);
	}

	public static  Boolean getReTryFlag() {
		return reTryFlag.get();
	}

	public static void setReTryFlag(Boolean reTry) {
		reTryFlag.set(reTry);
	}

	public static String getDataBase() {
		return dataBase.get();
	}

	public static void setDataBase(String dB) {
		dataBase.set(dB);
	}

	public static DataSourceConfigPage getDataBaseConfig() {
		return dataBaseConfig.get();
	}

	public static void setDataBaseConfig(DataSourceConfigPage dBConfig) {
		dataBaseConfig.set(dBConfig);
	}



	

}
