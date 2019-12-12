package com.workbench.client.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.workbench.client.core.Config;
import com.workbench.client.core.DefaultColumns;
import com.workbench.client.pages.WidgetPage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Stories("WORK BENCH")
@Features("Module - WIDGET")

public class wb03_WidgetsTest {
	private WidgetPage widget;

	@BeforeClass(groups = { Config.REGRESSION_TEST })
	public void initClass() {
		widget = new WidgetPage();
	}

	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.1")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 301,enabled= true)
	@Description("Verify the widget RFQ for Job ")
	public void addRFQWidgetTest() throws Exception {
		widget.addWidget("RFQ for Job");
	}

	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.2")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 302,enabled= true)
	@Description("Verify the  default columns widget RFQ for Job ")
	public void verifyRFQDefaultColumnsWidgetTest() throws Exception {
		widget.verifyDefaultColumns("RFQ for Job",DefaultColumns.getRFQDefaultColumnOptions());

	}

	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.3")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 303,enabled= true)
	@Description("ADD Column String For Widget RFQ for Job ")
	public void addColumnStringTest() throws Exception {
		widget.addColumnString("RFQ for Job");
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.4")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 304)
	@Description("Delete Column String For Widget RFQ for Job")
	public void deleteColumnStringTest() throws Exception {
		widget.deleteColumnString("RFQ for Job");
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.5")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 305)
	@Description("ADD Column Date For Widget RFQ for Job ")
	public void addColumnDateTest() throws Exception {
		widget.addColumnDate("RFQ for Job");
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.6")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 306)
	@Description("Delete Column String For Widget RFQ for Job")
	public void deleteColumnDateTest() throws Exception {
		widget.deleteColumnDate("RFQ for Job");
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.7")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 307)
	@Description("ADD Column Num For Widget RFQ for Job ")
	public void addColumnNumTest() throws Exception {
		widget.addColumnNum("RFQ for Job");
	}
	

	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.8")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 308)
	@Description("Delete Column String For Widget RFQ for Job")
	public void deleteColumnNumTest() throws Exception {
		widget.deleteColumnNum("RFQ for Job");
	}
	
//	@Severity(SeverityLevel.CRITICAL)
//	@TestCaseId("MONARCH-14083.6")
//	@Test(groups = { Config.REGRESSION_TEST }, priority = 306)
//	@Description("ADD Column Bool For Widget RFQ for Job ")
//	public void addColumnBoolTest() throws Exception {
//		widget.addColumnBool("RFQ for Job");
//	}
	
	

	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.9")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 309)
	@Description("Apply the Display Rule String for  RFQ for Job ")
	public void addRuleString() throws Exception {
		widget.addRuleString("RFQ for Job", "Customer Name",DefaultColumns.getRFQDefaultColumnOptions());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.10")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 310)
	@Description("Apply the Display Rule Date for  RFQ for Job ")
	public void addRuleDate() throws Exception {
		widget.addRuleDate("RFQ for Job", "Date Promised",DefaultColumns.getRFQDefaultColumnOptions());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.11")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 311)
	@Description("Apply the Display Rule Num for  RFQ for Job ")
	public void addRuleNum() throws Exception {
		widget.addRuleNum("RFQ for Job", "Customer ID",DefaultColumns.getRFQDefaultColumnOptions());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.12")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 312)
	@Description("Apply the Display Rule String for  RFQ for Job ")
	public void modifyRuleString() throws Exception {
		widget.modifyRuleString("RFQ for Job", "Customer Name",DefaultColumns.getRFQDefaultColumnOptions());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.13")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 313)
	@Description("Apply the Display Rule Date for  RFQ for Job ")
	public void modifyRuleDate() throws Exception {
		widget.modifyRuleDate("RFQ for Job", "Date Promised",DefaultColumns.getRFQDefaultColumnOptions());
	}
	

	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.14")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 314)
	@Description("Apply the Display Rule Num for  RFQ for Job ")
	public void modifyRuleNum() throws Exception {
		widget.modifyRuleNum("RFQ for Job", "Customer ID",DefaultColumns.getRFQDefaultColumnOptions());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("MONARCH-14083.15")
	@Test(groups = { Config.REGRESSION_TEST }, priority = 315)
	@Description("Apply the Advance Rule  for  RFQ for Job ")
	public void advanceRule() throws Exception {
		widget.applyAdvanceRule("RFQ for Job", "Customer Name", "#:customername# =  #:customerid#",DefaultColumns.getRFQDefaultColumnOptions());
	}
	
	 

}
