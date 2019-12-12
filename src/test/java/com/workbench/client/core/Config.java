package com.workbench.client.core;

import java.io.File;

public class Config 
{
	
private static File folder;




//****  Test Data *****

	final public static String progressDataBaseHostName="mon-wb-progress";
	final public static String progressDataBasePortName="12701";
	final public static String progressUserName="demo";
	final public static String progressDataBasePasswordName="demo";
	
	
	final public static String company="accuprint";
	final public static String plant="accuprint";

	
	// **** driver  name constants **** 
	final public static String CHROME_DRIVER = "Chrome";
	final public static String FIREFOX_DRIVER = "Firefox";
	final public static String IE_DRIVER = "InternetExploler";
	final public static String SAFARI_DRIVER = "Safari";
	
	// **** environment name constants *****

	final public static String ENVIRONMENT = "PreProduction";
	final public static String BROWSER = "Browser";
	final public static String Browser_Version = "Browser.Version";
	final public static String APPLICATION_URL = "Application URL";
	
	
	// ****  Test  suit Type name constants *****
	
	final public static String REGRESSION_TEST="regression_test";
	final public static String TEST="test";
	final public static String DATABASE_CONFIG_TEST="database_config_test";
	// *********** User Details ********************
	public enum UserDetails
	{
		CUSTOMER_NAME, FIRST_NAME, LAST_NAME, ADDRESS, CITY, POSTCODE, ACTIVITY_NAME, CONTACT_NAME, OPPORTUNITY_NAME, NOTE,
		PUBLICATION, COMPLAINT, CAMPAIGN,CAMPAIGNPROJECT,TEMPLATE;
	}
	
	//*****************RFQ Default Columns***************************
	
	

	
	// ****** Time out Constant **********
	final public static int TIMEOUT_IN_SECONDS = 60;
	final public static int POLLING_TIME_IN_SECONDS = 1;
	

	
	// *********** file path **************
	public final static String DRIVER_PATH = CommonUtils.getUserCurrentDirectoryPath()+"/drivers";
	public final static String DEFAULT_DOWNLOAD_PATH = CommonUtils.getUserCurrentDirectoryPath()+"/Downloads";
	public final static String Env_Property = CommonUtils.getUserCurrentDirectoryPath()+"/PropertiesFiles/Environment.properties";
	public final static String Performance_JsonFile = CommonUtils.getUserCurrentDirectoryPath()+"/files/performanceMatric.json";
	public final static String Performance_ExcelFile = CommonUtils.getUserCurrentDirectoryPath()+"/files/PerformanceMatrix.xlsx";
	
	
	public enum LocatorStrategy
	{

		WEB_LOCATOR_STRATEGY_ID, WEB_LOCATOR_STRATEGY_CSS, WEB_LOCATOR_STRATEGY_CSS_INPUT_DATA_FEATURE,
		WEB_LOCATOR_STRATEGY_XPATH, WEB_LOCATOR_STRATEGY_XPATH_TEXT, WEB_LOCATOR_STRATEGY_XPATH_DIV_TEXT_CONTAINS, WEB_LOCATOR_STRATEGY_XPATH_SPAN_TEXT_CONTAINS,
		WEB_LOCATOR_STRATEGY_XPATH_TEXT_CONTAINS,
		
		NONE 
	}
	
	// **** element locator strategies constants ****
	public final static String WEB_LOCATOR_STRATEGY_ID = "id";
	public final static String WEB_LOCATOR_STRATEGY_CSS = "css";
	public final static String WEB_LOCATOR_STRATEGY_XPATH = "xpath";
	public final static String WEB_LOCATOR_STRATEGY_XPATH_DIV_TEXT_CONTAINS = "xpath_div_text_contains";
	public final static String WEB_LOCATOR_STRATEGY_XPATH_SPAN_TEXT_CONTAINS = "xpath_span_text_contains";

    
    // **** seprators in session start time stamp **** 
 	final public static String WEB_DATE_FORMAT = "MM/dd/yyyy";
 	final public static String WEB_TIME_FORMAT = "H:mm";


	
	// ****** scroll direction *********	
	public enum ScrollDiection
	{
		RIGHT, LEFT, UP, DOWN, NEXT
	}
	

    //********* File Type ****************
	

	public static String getDownloadFolder() {
	folder = new File(Config.DEFAULT_DOWNLOAD_PATH);
	folder.mkdir();
	 return folder.getAbsolutePath();
	}
	
	

	
}
