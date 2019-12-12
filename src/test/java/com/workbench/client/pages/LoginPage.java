package com.workbench.client.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.workbench.client.core.CommonUtils;
import com.workbench.client.core.Config;
import com.workbench.client.core.DriverManager;
import com.workbench.client.core.Log;

import io.qameta.allure.Step;

public class LoginPage extends CommonUtils {

	@FindBy(how = How.CSS, using = "input#loginid")
	private WebElement LoginIdField;

	@FindBy(how = How.CSS, using = "input#username")
	private WebElement UserNameField;

	@FindBy(how = How.CSS, using = "input#company")
	private WebElement CompanyField;

	@FindBy(how = How.CSS, using = "input#plant")
	private WebElement PlantField;

	@FindBy(how = How.CSS, using = "[aria-owns='Role_listbox'] .k-dropdown-wrap")
	private WebElement RoleDropDownList;

	@FindBy(how = How.XPATH, using = "//li[text()='CSR - Monarch']")
	private WebElement CSRMonarch;

	@FindBy(how = How.XPATH, using = "//span[text()='Launch Workbench']")
	private WebElement LaunchBtn;

	public LoginPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		Log.setLogger("LoginPage");

	}

@Step("Launch Workbench with loginid {0}")
	public  void login(String loginid ) {
	clear(LoginIdField);
	sendKeys(LoginIdField, loginid);
	clear(UserNameField);
	sendKeys(UserNameField, loginid);
	clear(CompanyField);
	sendKeys(CompanyField, Config.company);
	clear(PlantField);
	sendKeys(PlantField,Config.plant);
	wait(3);
    click(RoleDropDownList);
    wait(3);
    click(CSRMonarch);
    wait(3);
    click(LaunchBtn);
	
	}
}
