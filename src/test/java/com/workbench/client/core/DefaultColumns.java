package com.workbench.client.core;

import java.util.ArrayList;
import java.util.List;

public class DefaultColumns {
	
	public static List<String> getRFQDefaultColumnOptions() {
		List<String> columnOption = new ArrayList<String>();
		columnOption.add("Customer ID");
		columnOption.add("Customer Name");
		columnOption.add("Date Promised");
		columnOption.add("Time Promised");
		columnOption.add("Project ID");
		columnOption.add("Submitted Date");
		columnOption.add("Submitted Time");
		//columnOption.add("Status");

		return columnOption;
	}

}
