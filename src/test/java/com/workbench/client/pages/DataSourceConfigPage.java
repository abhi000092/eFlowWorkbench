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

public class DataSourceConfigPage extends CommonUtils {
	
	
	
	@FindBy(how = How.XPATH ,using ="//label[@class='k-checkbox-label' and .=' Monarch - demo ']/parent::div/following-sibling::div/img")
	private WebElement monarchDataSourceEdit;
	
	@FindBy(how = How.CSS, using= "[aria-owns =Dbtype_listbox] .k-dropdown-wrap .k-select")
    private WebElement monarchDatabaseTypeDropdown;
	
	@FindBy(how = How.CSS , using = "#Host_holder_1 input")
	private WebElement HostField;
	
	@FindBy(how = How.CSS , using ="#Port_holder_1 input")
	private WebElement PortField;
	
	@FindBy(how = How.CSS , using ="#Dbuser_holder_1 input")
	private WebElement DBUserField;
	
	@FindBy(how = How.CSS , using ="#Dbpass_holder_1 input")
	private WebElement DbPasswordField;
	
	@FindBy(how = How.XPATH , using ="//span[text()='Test and Activate']")
	private WebElement ActivateBtn;
	
	@FindBy(how = How.CSS , using ="span#wbc_asokbutton")
	private WebElement elasticSearchEnableBtn;
	
	@FindBy(how = How.CSS , using ="span#Next")
	private WebElement nextBtn;
	
	@FindBy(how = How.CSS , using ="span#SaveSettingsButton")
	private WebElement saveSettingBtn;
	
	
	
	
	
	
	
	
	
	public DataSourceConfigPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		Log.setLogger("DataSourceConfigPage");
		
	}

	public static  enum DatabaeType {
		PROGRESS,MSSQLSERVER;
	}
	
	public void setDataBase(DatabaeType aType) {
		waitForLoad(DriverManager.getDriver());
		wait(5);
		monarchDataSourceEdit.click();
		wait(4);
		monarchDatabaseTypeDropdown.click();
		wait(3);
		
		
		switch (aType) {
		
		case PROGRESS:
			wait(3);
			click(DriverManager.getDriver().findElement(By.xpath("//li[text()='Progress']")));
			clear(HostField);
			sendKeys(HostField, Config.progressDataBaseHostName);
			clear(PortField);
			sendKeys(PortField, Config.progressDataBasePortName);
			clear(DBUserField);
			sendKeys(DBUserField, Config.progressUserName);
			clear(DbPasswordField);
			sendKeys(DbPasswordField, Config.progressDataBasePasswordName);
			break;
		case MSSQLSERVER:
			wait(3);
			click(DriverManager.getDriver().findElement(By.xpath("//li[text()='Progress']")));
			clear(HostField);
			sendKeys(HostField, Config.progressDataBaseHostName);
			clear(PortField);
			sendKeys(PortField, Config.progressDataBasePortName);
			clear(DBUserField);
			sendKeys(DBUserField, Config.progressUserName);
			clear(DbPasswordField);
			sendKeys(DbPasswordField, Config.progressDataBasePasswordName);
			break;

		default:
			break;
		}
		
		click(ActivateBtn);
		click(elasticSearchEnableBtn);
		scrollToElement(nextBtn);
		wait(3);
		click(nextBtn);
		wait(3);
		click(saveSettingBtn);
		
		
	}
	
}
