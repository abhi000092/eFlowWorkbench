package com.workbench.client.config;

import com.github.javafaker.Faker;
import com.workbench.client.core.CommonUtils;
import com.workbench.client.core.Config.UserDetails;

public class WidgetData extends CommonUtils {
	
	private static String strColumnName =null;
	
	private static String dateColumnName =null;
	
	private static String numColumnName=null;


	public WidgetData() {
	
	}

	public static String getStrColumnName() {
		return strColumnName;
	}

	public static void setStrColumnName(String strcolumnName) {
		strColumnName = strcolumnName;
	}
	
	public static String getDateColumnName() {
		return dateColumnName;
	}

	public static void setDateColumnName(String datecolumnName) {
		dateColumnName = datecolumnName;
	}
	
	public static String getNumColumnName() {
		return dateColumnName;
	}

	public static void setNumColumnName(String numcolumnName) {
		numColumnName = numcolumnName;
	}
	

}
