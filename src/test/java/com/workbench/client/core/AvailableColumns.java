package com.workbench.client.core;

import java.util.ArrayList;
import java.util.List;

public class AvailableColumns {
	
	public static List<String> getRFQAvailableColumnOption()
	{
		List<String> columnOption = new ArrayList<String>();
		columnOption.add("CSR ID");
		columnOption.add("Customer ID");
		columnOption.add("Customer Name");
		columnOption.add("Date Promised");
		columnOption.add("Time Promised");
		columnOption.add("Estimator ID");
		columnOption.add("Form ID");
		columnOption.add("Project ID");
		columnOption.add("Revision ID");
		columnOption.add("Review Notes");
		columnOption.add("Submitted Date");
		columnOption.add("Submitted Time");
		columnOption.add("Description");
		columnOption.add("Status");
		columnOption.add("SalesRep ID");
		columnOption.add("SalesRep Name");
		columnOption.add("System ID");
		columnOption.add("CustomerSystem ID");

		return columnOption;
		
	}

}
