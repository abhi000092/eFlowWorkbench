package com.workbench.client.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.workbench.client.config.CredentialsProvider;
import com.workbench.client.core.Config;
import com.workbench.client.pages.LoginPage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;



@Stories("eCRM-Core")
@Features("Module - Login Page")



public class wb02_LoginTests {
	private LoginPage login;


	@BeforeClass(groups = { Config.REGRESSION_TEST,Config.TEST })
	public void initClass() {
		login = new LoginPage();
	}



	/*	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("EFISWCRM-852")
	@Test(groups = {Config.REGRESSION_TEST }, priority = 103,dataProvider = "RegressionCredentials", dataProviderClass = CredentialsProvider.class)
	@Description("Login to the Application using invalid credentials ")
	public void loginwithInvalidCredentialsTest(String userId, String password) throws Exception {
		login.login(userId,password);
	}*/



	@Severity(SeverityLevel.CRITICAL)
	@TestCaseId("EFISWCRM-852")
	@Test(groups = {Config.REGRESSION_TEST ,Config.TEST}, priority = 104,dataProvider = "RegressionCredentials", dataProviderClass = CredentialsProvider.class)
	@Description("Login to the Application using existing ")
	public void loginwithvalidCredentialsTest(String loginid) throws Exception {
		login.login(loginid);
	}





}
