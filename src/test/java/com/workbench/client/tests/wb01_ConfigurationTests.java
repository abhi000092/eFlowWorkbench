package com.workbench.client.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.workbench.client.core.Config;
import com.workbench.client.pages.DataSourceConfigPage;
import com.workbench.client.pages.DataSourceConfigPage.DatabaeType;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;



@Stories("")
@Features("")



public class wb01_ConfigurationTests {
	
	private DataSourceConfigPage data;
	
	@BeforeClass(groups = { Config.DATABASE_CONFIG_TEST})
	public void initClass() {
	data = new DataSourceConfigPage();
	}
	
	
	
	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("")
	@Test(groups = {Config.DATABASE_CONFIG_TEST }, priority = 101)
	@Description("")
	@Parameters("database")
	public void initDataBase(@Optional("null") String database) throws Exception {
		if (database.equals("progress")) {
			data.setDataBase(DatabaeType.PROGRESS);
		}else if (database.equals("")) {
			
		}
	
	}
	
	
	
	
	
	
	

}
