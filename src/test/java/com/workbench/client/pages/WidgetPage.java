package com.workbench.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.hotkey.Keys;
import org.testng.Assert;

import com.workbench.client.config.WidgetData;
import com.workbench.client.core.CommonUtils;
import com.workbench.client.core.Config;
import com.workbench.client.core.DefaultColumns;
import com.workbench.client.core.DriverManager;
import com.workbench.client.core.Log;

import io.qameta.allure.Step;

public class WidgetPage extends CommonUtils {

	@FindBy(how=How.XPATH,using="//div[contains(@style,'block')]//button[text()='Close']")
	private WebElement addNewFieldClose;

	@FindBy(how = How.XPATH, using = "//div[@class='widget_customize']/img[@title='Configure Workbench Layout']")
	private WebElement workbenchConfigPage;

	@FindBy(how = How.XPATH, using = "//div[text()='Done']")
	private WebElement done;

	@FindBy(how = How.XPATH, using = "//span[contains(@onclick,'AddWidget')]//img")
	private WebElement addWidget;

	@FindBy(how = How.XPATH, using = "//span[text()='Select Widget']")
	private WebElement selectWidget;

	@FindBy(how = How.XPATH, using = "//div[@id='workbencharea_add']//button[contains(@onclick,'false')]")
	private WebElement save;

	@FindBy(how = How.XPATH, using = "//div[contains(@style,'display: block')]//button[text()='Save Rule']")
	private WebElement verifyDisplayPage;

	@FindBy(how = How.XPATH, using = "//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]")
	private WebElement deleteDisplayRule;

	@FindBy(how = How.XPATH, using = "//div[contains(@style,'display: block')]//span[contains(@class,'fieldlist')]//span[text()='select']")
	private WebElement selectIf;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'rowoperator-list')]//li[text()='Equals']")
	private WebElement operatorEquals;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'rowoperator-list')]//li[text()='Contains']")
	private WebElement operatorContains;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//input[contains(@data-bind,'datavalue')]")
	private WebElement displayTextfield;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//div[contains(@id,'Bold')]")
	private WebElement styleBold;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//div[contains(@id,'Italics')]")
	private WebElement styleItalics;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//div[contains(@id,'Underline')]")
	private WebElement styleUnderline;

	@FindBy(how = How.XPATH, using = "//div[contains(@style,'display: block')]//span[contains(@aria-owns,'ifstyle_listbox')]//span[text()='select']")
	private WebElement selectPriorityDropDownBar;

	@FindBy(how = How.XPATH, using = "//div[contains(@style,'display: block')]//span[contains(@aria-owns,'ifstyle_listbox')]//span[contains(text(),'Red Bar')]")
	private WebElement selectPriorityRed;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//button[contains(text(),'Save Rule')]")
	private WebElement displaySave;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//span[text()='Close']")
	private WebElement displayClose;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//form[contains(@id,'rowrule')]//input[contains(@data-value-update,'keyup')]")
	private WebElement displayTextBox;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//div[@class='span9']/span")
	private WebElement ruleAdded;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//span[text()='Close']")
	private WebElement closeRule;

	@FindBy(how= How.XPATH,using ="//div[contains(@style,'display: block')]//textarea[contains(@id,'customrule')]")
	private WebElement customRuleTextField;

	@FindBy(how= How.XPATH,using ="//span[contains(text(),'Advanced Column rules')]/ancestor::div[contains(@style,'display: block')]//button[text()='Save']")
	private WebElement saveCustomRule;

	@FindBy(how= How.XPATH,using ="//span[contains(text(),'Advanced Column rules')]/ancestor::div[contains(@style,'display: block')]//button[contains(text(),'Delete')]")
	private WebElement deleteCustomRule;

	public WidgetPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		Log.setLogger("WidgetPage");
	}

	@Step("Verify {0} is Visible")
	public boolean IsWidgetVisible(String WidgetName) {
		boolean bValue = false;
		try {
			bValue = isDisplayed(90, 4, DriverManager.getDriver()
					.findElement(By.xpath("//div[@class='widget_panel_header']//h3[text()='" + WidgetName + "']")));
		} catch (NoSuchElementException e) {
			bValue = false;
		}
		return bValue;

	}

	@Step(" ADD WIDGET")
	public void addWidget(String widgetName) {
		waitForLoad(DriverManager.getDriver());

		wait(3);

		// Switching to workBench
		switchToNewTab("workbench");

		wait(3);

		workbenchConfigPage.click();

		try {
			if (IsWidgetVisible(widgetName)) {
				int count = DriverManager.getDriver()
						.findElements(By.xpath("//div[@class='widget_panel_header']//h3[text()='" + widgetName + "']"))
						.size();
				System.out.println("Count is :" + count);
				for (int i = 1; i <= count; i++) {
					wait(4);
					click(DriverManager.getDriver()
							.findElement(By.xpath("//div[@class='widget_panel_header']//h3[text()='" + widgetName
									+ "']/ancestor::div/following-sibling::div[@class='widget-remove']")));
					acceptAlert(true);
				}
				wait(3);
				click(addWidget);
				waitForElement(selectWidget);
				scrollToElement(DriverManager.getDriver()
						.findElement(By.xpath("//div[@class='widget_name']/span[text()='" + widgetName + "']")));
				wait(3);
				click(DriverManager.getDriver()
						.findElement(By.xpath("//div[@class='widget_name']/span[text()='" + widgetName + "']")));
				wait(3);
				click(save);
				wait(3);
				click(done);
			} else {
				System.out.println("Widget already deleted or not available");
				click(done);
			}
		} catch (Exception Ex) {
			System.out.println("unable to add widget");
		}
	}

	@Step(" VERIFY DEFAULT COLUMNS")
	public void verifyDefaultColumns(String widgetName, List<String> ColumnToBeChecked) {

		//		String wn = DriverManager.getDriver()
		//				.findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName + "']"))
		//				.getText();
		//		String newWN = wn.replaceAll("\\s", "");
		waitForLoad(DriverManager.getDriver());
		try {
			if (IsWidgetVisible(widgetName)) {
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(3);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//li[@class='k-item k-state-default k-first']")));
				wait(5);

				Assert.assertTrue(checkColumnOptions(widgetName, ColumnToBeChecked));

			} else {
				System.err.println("Default Number of columns doesn't exist");
			}
		} catch (Exception Ex) {

		}	
	}

	@Step("check the Column Options")
	public boolean checkColumnOptions(String widgetName, List<String> ColumnToBeChecked) {
		List<String> ObtainedColumnList = new ArrayList<String>();
		List<WebElement> columnList = DriverManager.getDriver()
				.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
						+ "']/ancestor::div//div[@role='option']/span[1]"));
		int columnSize = columnList.size();
		for (WebElement anEle : columnList) {
			ObtainedColumnList.add(anEle.getText());
		}
		return ObtainedColumnList.containsAll(ColumnToBeChecked);
	}

	@Step("Get Column Size")
	public int getColumnSize(String widgetName, List<String> ColumnToBeChecked)
	{
		List<WebElement> columnList = DriverManager.getDriver()
				.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
						+ "']/ancestor::div//div[@role='option']/span[1]"));
		int columnSize = columnList.size();

		return columnSize;
	}

	public boolean verifyColumnString(String widgetName)
	{
		boolean bValue = false;
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title='" + widgetName
					+ "']/ancestor::div/following-sibling::div//th[@role='columnheader']/a[text()='"
					+ WidgetData.getStrColumnName() +"']")));
			bValue = true;

		} catch (NoSuchElementException e) {
			bValue = false;
		}
		return bValue;

	}

	public boolean verifyColumnDate(String widgetName)
	{
		boolean bValue = false;
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title='" + widgetName
					+ "']/ancestor::div/following-sibling::div//th[@role='columnheader']/a[text()='"
					+ WidgetData.getDateColumnName() +"']")));
			bValue = true;

		} catch (NoSuchElementException e) {
			bValue = false;
		}
		return bValue;

	}

	public boolean verifyColumnNum(String widgetName)
	{
		boolean bValue = false;
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title='" + widgetName
					+ "']/ancestor::div/following-sibling::div//th[@role='columnheader']/a[text()='"
					+ WidgetData.getNumColumnName() +"']")));
			bValue = true;

		} catch (NoSuchElementException e) {
			bValue = false;
		}
		return bValue;

	}

	public void addColumnString(String widgetName)
	{
		waitForLoad(DriverManager.getDriver());

		List<WebElement> addedColumn = DriverManager.getDriver()
				.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
						+ "']/ancestor::div//div[@role='option']/span[1]"));
		System.out.println("Size Of Column before adding new column:"+addedColumn.size());

		wait(4);
		click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//img[contains(@src,'add')]")));
		if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'block')]//large[text()='Add New Field']")).size()>0) {

			wait(5);

			List<WebElement> uniqueColumns= DriverManager.getDriver().findElements(By.xpath("//div[contains(@ondblclick,'AddNewItem')]//div[1]"));
			//		List<WebElement> aList = new ArrayList<WebElement>();
			for(WebElement li:uniqueColumns)
			{
				if(! DefaultColumns.getRFQDefaultColumnOptions().contains(li.getText()) ) {
					if (li.getText().contains("Name")) {
						System.out.println(li.getText());
						WidgetData.setStrColumnName(li.getText());
						String s = li.getText();

						doubleClick(li);
						wait(5);
						System.out.println(s);
						click(addNewFieldClose);
						wait(4);

						if(DriverManager.getDriver()
								.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
										+ "']/ancestor::div//div[@role='option']/span[1]")).size()>addedColumn.size()) {
							System.out.println("New Column Added succefully");

						}

						else
						{
							System.out.println("Failed To Add New Column");
						}					
						click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@class='modal_footer']//button[contains(@onclick,'true')]")));
						break;
					}
				}
			}
		}
		//	}
		/*}
			catch(Exception Ex){

			}*/
		Assert.assertTrue(verifyColumnString(widgetName));	
	}

	public void addColumnDate(String widgetName)
	{
		try {
			if (IsWidgetVisible(widgetName)) {
				wait(3);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(5);

				waitForLoad(DriverManager.getDriver());

				List<WebElement> addedColumn = DriverManager.getDriver()
						.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
								+ "']/ancestor::div//div[@role='option']/span[1]"));
				System.out.println("Size Of Column before adding new column:"+addedColumn.size());

				wait(4);
				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//img[contains(@src,'add')]")));
				if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'block')]//large[text()='Add New Field']")).size()>0) {

					wait(5);

					List<WebElement> uniqueColumns= DriverManager.getDriver().findElements(By.xpath("//div[contains(@ondblclick,'AddNewItem')]//div[2]"));
					//		List<WebElement> aList = new ArrayList<WebElement>();
					for(WebElement li:uniqueColumns)
					{
						if(! DefaultColumns.getRFQDefaultColumnOptions().contains(li.getText()) ) {
							if (li.getText().contains("_delta")) {
								System.out.println(li.getText());
								WidgetData.setDateColumnName(li.getText());
								String s = li.getText();

								doubleClick(li);
								wait(5);
								System.out.println(s);
								click(addNewFieldClose);
								wait(4);

								if(DriverManager.getDriver()
										.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
												+ "']/ancestor::div//div[@role='option']/span[1]")).size()>addedColumn.size()) {
									System.out.println("New Column Added succefully");

								}

								else
								{
									System.out.println("Failed To Add New Column");
								}					
								click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@class='modal_footer']//button[contains(@onclick,'true')]")));
								break;
							}
						}
					}
				}

				Assert.assertTrue(verifyColumnDate(widgetName));
			}
		}
		catch(Exception Ex)
		{

		}
	}

	public void addColumnNum(String widgetName )
	{
		try {
			if (IsWidgetVisible(widgetName)) {
				wait(3);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(5);

				waitForLoad(DriverManager.getDriver());

				List<WebElement> addedColumn = DriverManager.getDriver()
						.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
								+ "']/ancestor::div//div[@role='option']/span[1]"));
				System.out.println("Size Of Column before adding new column:"+addedColumn.size());

				wait(4);
				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//img[contains(@src,'add')]")));
				if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'block')]//large[text()='Add New Field']")).size()>0) {

					wait(5);

					List<WebElement> uniqueColumns= DriverManager.getDriver().findElements(By.xpath("//div[contains(@ondblclick,'AddNewItem')]//div[1]"));
					//		List<WebElement> aList = new ArrayList<WebElement>();
					for(WebElement li:uniqueColumns)
					{
						if(! DefaultColumns.getRFQDefaultColumnOptions().contains(li.getText()) ) {
							if (li.getText().contains("ID")) {
								System.out.println(li.getText());
								WidgetData.setDateColumnName(li.getText());
								String s = li.getText();

								doubleClick(li);
								wait(5);
								System.out.println(s);
								click(addNewFieldClose);
								wait(4);

								if(DriverManager.getDriver()
										.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
												+ "']/ancestor::div//div[@role='option']/span[1]")).size()>addedColumn.size()) {
									System.out.println("New Column Added succefully");

								}

								else
								{
									System.out.println("Failed To Add New Column");
								}					
								click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@class='modal_footer']//button[contains(@onclick,'true')]")));
								break;
							}
						}
					}
				}

				Assert.assertTrue(verifyColumnNum(widgetName));
			}
		}
		catch(Exception Ex)
		{

		}
	}

	public void deleteColumnString(String widgetName)
	{
		waitForLoad(DriverManager.getDriver());

		try {
			if (IsWidgetVisible(widgetName)) {
				wait(3);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(5);

				List<WebElement> addedColumn = DriverManager.getDriver()
						.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
								+ "']/ancestor::div//div[@role='option']/span[1]"));

				for(WebElement ac:addedColumn )
				{
					if(ac.getText().equals(WidgetData.getStrColumnName())) {

						System.out.println("Found Text:" +ac.getText());

						click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//img[contains(@src,'remove')]")));
						wait(5);
						click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@class='modal_footer']//button[contains(@onclick,'true')]")));
						break;
					}
					else
						System.out.println("Found Text:" +ac.getText()+" "+"Required Text:"+WidgetData.getStrColumnName());
				}

			}
		}
		catch(Exception Ex)
		{

		}
	}

	public void deleteColumnDate(String widgetName)
	{
		waitForLoad(DriverManager.getDriver());

		try {
			if (IsWidgetVisible(widgetName)) {
				wait(3);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(5);

				List<WebElement> addedColumn = DriverManager.getDriver()
						.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
								+ "']/ancestor::div//div[@role='option']/span[2]"));

				for(WebElement ac:addedColumn )
				{

					if(ac.getText().equals(WidgetData.getDateColumnName())) {

						System.out.println("Found Text:" +ac.getText());

						click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//img[contains(@src,'remove')]")));
						wait(5);
						click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@class='modal_footer']//button[contains(@onclick,'true')]")));
						break;
					}
					else
						System.out.println("Found Text:" +ac.getText()+" "+"Required Text:"+WidgetData.getDateColumnName());
				}
			}
		}
		catch(Exception Ex)
		{

		}
	}

	public void deleteColumnNum(String widgetName)
	{
		waitForLoad(DriverManager.getDriver());

		try {
			if (IsWidgetVisible(widgetName)) {
				wait(3);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(5);

				List<WebElement> addedColumn = DriverManager.getDriver()
						.findElements(By.xpath("//span[contains(@id,'rigTitle') and text()='" + widgetName
								+ "']/ancestor::div//div[@role='option']/span[1]"));

				for(WebElement ac:addedColumn )
				{

					if(ac.getText().equals(WidgetData.getNumColumnName())) {

						System.out.println("Found Text:" +ac.getText());

						click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//img[contains(@src,'remove')]")));
						wait(5);
						click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@class='modal_footer']//button[contains(@onclick,'true')]")));
						break;
					}
					else
						System.out.println("Found Text:" +ac.getText()+" "+"Required Text:"+WidgetData.getNumColumnName());
				}
			}
		}
		catch(Exception Ex)
		{

		}
	}

	public void addRuleString(String widgetName,String columnName,List<String> ColumnToBeChecked)
	{
		waitForLoad(DriverManager.getDriver());		

		try {

			if (IsWidgetVisible(widgetName)) {
				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='"+widgetName+"']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(3);

				int size= getColumnSize(widgetName,ColumnToBeChecked);

				System.out.println("Number Of columns present are :"+size);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@role='option']/span[@class='wb-fieldlistval1' and text()='"+columnName+"']")));

				for(int i=1;i<=size;i++)
				{
					wait (3);

					click(DriverManager.getDriver().findElement(By.xpath("//div[@class='k-widget k-window' and contains(@style,'block')] //a[contains(@onclick,'Up')]//img")));
				}

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'"+widgetName+"')]/parent::b/parent::div[contains(@style,'display: block')]//button[contains(text(),'Close')]")));

				wait(5);

				String columnVal= DriverManager.getDriver().findElement(By.xpath("//*[@title='"+widgetName+"']/ancestor::div[@class='widget_panel_header']/following-sibling::div//div[contains(@class,'k-grid-content')]//tr[2]//td[2]")).getText();
				System.out.println("Picked Column Value:"+columnVal);

				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));

				wait(3);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[contains(@src,'rules_menu')]")));

				waitForLoad(DriverManager.getDriver());
				//waitForElement(verifyDisplayPage);

				if(DriverManager.getDriver().findElement(By.xpath("//div[contains(@style,'display: block')]//button[contains(text(),'Save Rule')]"))!=null)
				{
					System.out.println("On Display Tab");

					if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]")).size()>0) 
					{
						List<WebElement> noOfRulesAlreadyPresent = DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]"));

						System.out.println("Number Of rules Need To Be Deleted:" +noOfRulesAlreadyPresent.size());

						for(int i=1;i<=noOfRulesAlreadyPresent.size();i++)
						{
							wait(3);
							click(deleteDisplayRule);
							wait(3);
							CommonUtils.acceptAlert(true);
							System.out.println("Rule Deleted Succesfully");
						}
					}
					else
					{
						System.out.println("No rule exists");
					}
					click(selectIf);
					wait(3);
					String condition= columnName.toLowerCase();
					String displayrule =condition.replaceAll("\\s", "");

					moveToElementByLoc(By.xpath("//li[text()='"+displayrule+"']"));
					wait(3);

					DriverManager.getDriver().findElement(By.xpath("//li[text()='"+displayrule+"']")).click();

					wait(3);
					click(operatorEquals);
					wait(3);

					sendKeys(displayTextBox,columnVal);	
					wait(3);
					click(styleBold);
					wait(3);
					click(displaySave);		

					wait(5);

					Assert.assertTrue(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//div[@class='span9']/span")).size()>0);

					click(closeRule);
				}		
				else
				{
					System.out.println("Unable to Click on Display Tab");

				}

				if(DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]")).size()>0)
				{
					List<WebElement> vr = DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]"));
					System.out.println("Total Number of values rule applied:"+vr.size());

					System.out.println("rule applied successfully");
				}
				else
				{
					System.err.println("Failed to apply rule");
				}
			}
			else {
				System.err.println("Widget Not Available");
			}

		}catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void modifyRuleString(String widgetName,String columnName,List<String> ColumnToBeChecked)
	{
		waitForLoad(DriverManager.getDriver());		

		try {

			if (IsWidgetVisible(widgetName)) {
				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='"+widgetName+"']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(3);

				int size= getColumnSize(widgetName,ColumnToBeChecked);

				System.out.println("Number Of columns present are :"+size);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@role='option']/span[@class='wb-fieldlistval1' and text()='"+columnName+"']")));

				for(int i=1;i<=size;i++)
				{
					wait (3);

					click(DriverManager.getDriver().findElement(By.xpath("//div[@class='k-widget k-window' and contains(@style,'block')] //a[contains(@onclick,'Up')]//img")));
				}

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'"+widgetName+"')]/parent::b/parent::div[contains(@style,'display: block')]//button[contains(text(),'Close')]")));

				wait(5);

				String columnVal= DriverManager.getDriver().findElement(By.xpath("//*[@title='"+widgetName+"']/ancestor::div[@class='widget_panel_header']/following-sibling::div//div[contains(@class,'k-grid-content')]//tr[2]//td[2]")).getText();
				System.out.println("Picked Column Value:"+columnVal);

				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));

				wait(3);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[contains(@src,'rules_menu')]")));

				waitForLoad(DriverManager.getDriver());
				//waitForElement(verifyDisplayPage);

				if(DriverManager.getDriver().findElement(By.xpath("//div[contains(@style,'display: block')]//button[contains(text(),'Save Rule')]"))!=null)
				{
					System.out.println("On Display Tab");

					if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]")).size()>0) 
					{
						List<WebElement> noOfRulesAlreadyPresent = DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]"));

						System.out.println("Number Of rules Need To Be Deleted:" +noOfRulesAlreadyPresent.size());

						for(int i=1;i<=noOfRulesAlreadyPresent.size();i++)
						{
							wait(3);
							click(deleteDisplayRule);
							wait(3);
							CommonUtils.acceptAlert(true);
							System.out.println("Rule Deleted Succesfully");
						}
					}
					else
					{
						System.out.println("No rule exists");
					}
					click(selectIf);
					wait(3);
					String condition= columnName.toLowerCase();
					String displayrule =condition.replaceAll("\\s", "");

					moveToElementByLoc(By.xpath("//li[text()='"+displayrule+"']"));
					wait(3);

					DriverManager.getDriver().findElement(By.xpath("//li[text()='"+displayrule+"']")).click();

					wait(3);
					click(operatorEquals);
					wait(3);

					sendKeys(displayTextBox,columnVal);	
					wait(3);
					click(styleItalics);
					wait(3);
					click(styleUnderline);
					wait(3);
					click(displaySave);		
					wait(5);

					Assert.assertTrue(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//div[@class='span9']/span")).size()>0);

					click(closeRule);
				}		
				else
				{
					System.out.println("Unable to Click on Display Tab");

				}

				if(DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]")).size()>0)
				{
					List<WebElement> vr = DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]"));
					System.out.println("Total Number of values rule applied:"+vr.size());

					System.out.println("rule applied successfully");
					Assert.assertTrue(true, "rule applied successfully");
				}
				else
				{
					
					Assert.fail("Failed to apply rule");
				}
			}
			else {
				System.err.println("Widget Not Available");
			}

		}catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void addRuleDate(String widgetName,String columnName,List<String> ColumnToBeChecked)
	{
		waitForLoad(DriverManager.getDriver());

		try {

			if (IsWidgetVisible(widgetName)) {
				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='"+widgetName+"']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(3);

				int size= getColumnSize(widgetName,ColumnToBeChecked);

				System.out.println("Number Of columns present are :"+size);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@role='option']/span[@class='wb-fieldlistval1' and text()='"+columnName+"']")));

				for(int i=1;i<=size;i++)
				{
					wait (3);

					click(DriverManager.getDriver().findElement(By.xpath("//div[@class='k-widget k-window' and contains(@style,'block')] //a[contains(@onclick,'Up')]//img")));
				}

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'"+widgetName+"')]/parent::b/parent::div[contains(@style,'display: block')]//button[contains(text(),'Close')]")));

				wait(5);

				String columnVal= DriverManager.getDriver().findElement(By.xpath("//*[@title='"+widgetName+"']/ancestor::div[@class='widget_panel_header']/following-sibling::div//div[contains(@class,'k-grid-content')]//tr[3]//td[2]")).getText();
				System.out.println("Picked Column Value:"+columnVal);

				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));

				wait(3);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[contains(@src,'rules_menu')]")));

				waitForLoad(DriverManager.getDriver());
				//waitForElement(verifyDisplayPage);

				if(DriverManager.getDriver().findElement(By.xpath("//div[contains(@style,'display: block')]//button[contains(text(),'Save Rule')]"))!=null)
				{
					System.out.println("On Display Tab");


					if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]")).size()>0) 
					{
						List<WebElement> noOfRulesAlreadyPresent = DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]"));

						System.out.println("Number Of rules Need To Be Deleted:" +noOfRulesAlreadyPresent.size());

						for(int i=1;i<=noOfRulesAlreadyPresent.size();i++)
						{
							wait(3);
							click(deleteDisplayRule);
							wait(3);
							CommonUtils.acceptAlert(true);
							System.out.println("Rule Deleted Succesfully");
						}
					}
					else
					{
						System.out.println("No rule exists");
					}
					click(selectIf);
					wait(3);
					String condition= columnName.toLowerCase();
					String displayrule =condition.replaceAll("\\s", "");

					moveToElementByLoc(By.xpath("//li[text()='"+displayrule+"']"));
					wait(3);

					DriverManager.getDriver().findElement(By.xpath("//li[text()='"+displayrule+"']")).click();

					wait(3);
					click(operatorEquals);
					wait(4);

					sendKeys(displayTextBox,columnVal);	
					wait(3);
					click(styleBold);
					wait(3);
					click(displaySave);		

					wait(5);

					Assert.assertTrue(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//div[@class='span9']/span")).size()>0);

					click(closeRule);
				}		
				else
				{
					System.out.println("Unable to Click on Display Tab");

				}

				if(DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]")).size()>0)
				{
					List<WebElement> vr = DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]"));
					System.out.println("Total Number of values rule applied:"+vr.size());

					System.out.println("rule applied successfully");
				}
				else
				{
					System.err.println("Failed to apply rule");
				}
			}
			else {
				System.err.println("Widget Not Available");
			}

		}catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void modifyRuleDate(String widgetName,String columnName,List<String> ColumnToBeChecked)
	{
		waitForLoad(DriverManager.getDriver());

		try {

			if (IsWidgetVisible(widgetName)) {
				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='"+widgetName+"']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(3);

				int size= getColumnSize(widgetName,ColumnToBeChecked);

				System.out.println("Number Of columns present are :"+size);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@role='option']/span[@class='wb-fieldlistval1' and text()='"+columnName+"']")));

				for(int i=1;i<=size;i++)
				{
					wait (3);

					click(DriverManager.getDriver().findElement(By.xpath("//div[@class='k-widget k-window' and contains(@style,'block')] //a[contains(@onclick,'Up')]//img")));
				}

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'"+widgetName+"')]/parent::b/parent::div[contains(@style,'display: block')]//button[contains(text(),'Close')]")));

				wait(5);

				String columnVal= DriverManager.getDriver().findElement(By.xpath("//*[@title='"+widgetName+"']/ancestor::div[@class='widget_panel_header']/following-sibling::div//div[contains(@class,'k-grid-content')]//tr[3]//td[2]")).getText();
				System.out.println("Picked Column Value:"+columnVal);

				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));

				wait(3);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[contains(@src,'rules_menu')]")));

				waitForLoad(DriverManager.getDriver());
				//waitForElement(verifyDisplayPage);

				if(DriverManager.getDriver().findElement(By.xpath("//div[contains(@style,'display: block')]//button[contains(text(),'Save Rule')]"))!=null)
				{
					System.out.println("On Display Tab");


					if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]")).size()>0) 
					{
						List<WebElement> noOfRulesAlreadyPresent = DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]"));

						System.out.println("Number Of rules Need To Be Deleted:" +noOfRulesAlreadyPresent.size());

						for(int i=1;i<=noOfRulesAlreadyPresent.size();i++)
						{
							wait(3);
							click(deleteDisplayRule);
							wait(3);
							CommonUtils.acceptAlert(true);
							System.out.println("Rule Deleted Succesfully");
						}
					}
					else
					{
						System.out.println("No rule exists");
					}
					click(selectIf);
					wait(3);
					String condition= columnName.toLowerCase();
					String displayrule =condition.replaceAll("\\s", "");

					moveToElementByLoc(By.xpath("//li[text()='"+displayrule+"']"));
					wait(3);

					DriverManager.getDriver().findElement(By.xpath("//li[text()='"+displayrule+"']")).click();

					wait(3);
					click(operatorEquals);
					wait(3);

					sendKeys(displayTextBox,columnVal);	
					wait(3);
					click(styleBold);
					wait(3);
					click(styleUnderline);
					wait(3);
					click(displaySave);		

					wait(5);

					Assert.assertTrue(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//div[@class='span9']/span")).size()>0);

					click(closeRule);
				}		
				else
				{
					System.out.println("Unable to Click on Display Tab");

				}

				if(DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]")).size()>0)
				{
					List<WebElement> vr = DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]"));
					System.out.println("Total Number of values rule applied:"+vr.size());

					System.out.println("rule applied successfully");
				}
				else
				{
					System.err.println("Failed to apply rule");
				}
			}
			else {
				System.err.println("Widget Not Available");
			}

		}catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void addRuleNum(String widgetName,String columnName,List<String> ColumnToBeChecked)
	{
		waitForLoad(DriverManager.getDriver());

		try {

			if (IsWidgetVisible(widgetName)) {
				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='"+widgetName+"']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(3);

				int size= getColumnSize(widgetName,ColumnToBeChecked);

				System.out.println("Number Of columns present are :"+size);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@role='option']/span[@class='wb-fieldlistval1' and text()='"+columnName+"']")));

				for(int i=1;i<=size;i++)
				{
					wait (3);

					click(DriverManager.getDriver().findElement(By.xpath("//div[@class='k-widget k-window' and contains(@style,'block')] //a[contains(@onclick,'Up')]//img")));
				}

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'"+widgetName+"')]/parent::b/parent::div[contains(@style,'display: block')]//button[contains(text(),'Close')]")));

				wait(5);

				String columnVal= DriverManager.getDriver().findElement(By.xpath("//*[@title='"+widgetName+"']/ancestor::div[@class='widget_panel_header']/following-sibling::div//div[contains(@class,'k-grid-content')]//tr[4]//td[2]")).getText();
				System.out.println("Picked Column Value:"+columnVal);

				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));

				wait(3);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[contains(@src,'rules_menu')]")));

				waitForLoad(DriverManager.getDriver());
				//waitForElement(verifyDisplayPage);

				if(DriverManager.getDriver().findElement(By.xpath("//div[contains(@style,'display: block')]//button[contains(text(),'Save Rule')]"))!=null)
				{
					System.out.println("On Display Tab");


					if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]")).size()>0) 
					{
						List<WebElement> noOfRulesAlreadyPresent = DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]"));

						System.out.println("Number Of rules Need To Be Deleted:" +noOfRulesAlreadyPresent.size());

						for(int i=1;i<=noOfRulesAlreadyPresent.size();i++)
						{
							wait(3);
							click(deleteDisplayRule);
							wait(3);
							CommonUtils.acceptAlert(true);
							System.out.println("Rule Deleted Succesfully");
						}
					}
					else
					{
						System.out.println("No rule exists");
					}
					click(selectIf);
					wait(3);
					String condition= columnName.toLowerCase();
					String displayrule =condition.replaceAll("\\s", "");

					moveToElementByLoc(By.xpath("//li[text()='"+displayrule+"']"));
					wait(3);

					DriverManager.getDriver().findElement(By.xpath("//li[text()='"+displayrule+"']")).click();

					wait(3);
					click(operatorEquals);
					wait(3);

					sendKeys(displayTextBox,columnVal);	
					wait(3);
					click(styleBold);
					wait(3);
					click(displaySave);		

					wait(5);

					Assert.assertTrue(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//div[@class='span9']/span")).size()>0);

					click(closeRule);
				}		
				else
				{
					System.out.println("Unable to Click on Display Tab");

				}

				if(DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]")).size()>0)
				{
					List<WebElement> vr = DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]"));
					System.out.println("Total Number of values rule applied:"+vr.size());

					System.out.println("rule applied successfully");
				}
				else
				{
					System.err.println("Failed to apply rule");
				}
			}
			else {
				System.err.println("Widget Not Available");
			}

		}catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void modifyRuleNum(String widgetName,String columnName,List<String> ColumnToBeChecked)
	{
		waitForLoad(DriverManager.getDriver());

		try {

			if (IsWidgetVisible(widgetName)) {
				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='"+widgetName+"']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(3);

				int size= getColumnSize(widgetName,ColumnToBeChecked);

				System.out.println("Number Of columns present are :"+size);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@role='option']/span[@class='wb-fieldlistval1' and text()='"+columnName+"']")));

				for(int i=1;i<=size;i++)
				{
					wait (3);

					click(DriverManager.getDriver().findElement(By.xpath("//div[@class='k-widget k-window' and contains(@style,'block')] //a[contains(@onclick,'Up')]//img")));
				}

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'"+widgetName+"')]/parent::b/parent::div[contains(@style,'display: block')]//button[contains(text(),'Close')]")));

				wait(5);

				String columnVal= DriverManager.getDriver().findElement(By.xpath("//*[@title='"+widgetName+"']/ancestor::div[@class='widget_panel_header']/following-sibling::div//div[contains(@class,'k-grid-content')]//tr[4]//td[2]")).getText();
				System.out.println("Picked Column Value:"+columnVal);

				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));

				wait(3);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
						+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[contains(@src,'rules_menu')]")));

				waitForLoad(DriverManager.getDriver());
				//waitForElement(verifyDisplayPage);

				if(DriverManager.getDriver().findElement(By.xpath("//div[contains(@style,'display: block')]//button[contains(text(),'Save Rule')]"))!=null)
				{
					System.out.println("On Display Tab");


					if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]")).size()>0) 
					{
						List<WebElement> noOfRulesAlreadyPresent = DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Delete')]"));

						System.out.println("Number Of rules Need To Be Deleted:" +noOfRulesAlreadyPresent.size());

						for(int i=1;i<=noOfRulesAlreadyPresent.size();i++)
						{
							wait(3);
							click(deleteDisplayRule);
							wait(3);
							CommonUtils.acceptAlert(true);
							System.out.println("Rule Deleted Succesfully");
						}
					}
					else
					{
						System.out.println("No rule exists");
					}
					click(selectIf);
					wait(3);
					String condition= columnName.toLowerCase();
					String displayrule =condition.replaceAll("\\s", "");

					moveToElementByLoc(By.xpath("//li[text()='"+displayrule+"']"));
					wait(3);

					DriverManager.getDriver().findElement(By.xpath("//li[text()='"+displayrule+"']")).click();

					wait(3);
					click(operatorEquals);
					wait(3);

					sendKeys(displayTextBox,columnVal);	
					wait(3);
					click(styleBold);
					wait(3);
					click(selectPriorityDropDownBar);
					wait(3);
					for(int i=1;i<=1;i++)
					{
					CommonUtils.sendKeys(selectPriorityRed, Keys.DOWN);
					CommonUtils.sendKeys(selectPriorityRed, Keys.ENTER);
					}
				//	CommonUtils.moveAndClickElement(selectPriorityRed)
					wait(3);
					click(selectPriorityRed);
					click(displaySave);		
					wait(5);

					Assert.assertTrue(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//div[@class='span9']/span")).size()>0);

					click(closeRule);
				}		
				else
				{
					System.out.println("Unable to Click on Display Tab");

				}

				if(DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]")).size()>0)
				{
					List<WebElement> vr = DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//td//div[contains(@class,'bold')]"));
					System.out.println("Total Number of values rule applied:"+vr.size());

					System.out.println("rule applied successfully");
				}
				else
				{
					System.err.println("Failed to apply rule");
				}
			}
			else {
				System.err.println("Widget Not Available");
			}

		}catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void applyAdvanceRule(String widgetName, String columnName, String customRule,List<String> ColumnToBeChecked)
	{

		waitForLoad(DriverManager.getDriver());

		try {

			if (IsWidgetVisible(widgetName)) {
				wait(5);

				click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='"+widgetName+"']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
				wait(5);
				click(DriverManager.getDriver().findElement(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//img[contains(@src,'column_menu')]")));
				wait(3);

				int size= getColumnSize(widgetName,ColumnToBeChecked);

				System.out.println("Number Of columns present are :"+size);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(@id,'rigTitle') and text()='"+widgetName+"']/ancestor::div//div[@role='option']/span[@class='wb-fieldlistval1' and text()='"+columnName+"']")));

				for(int i=1;i<=size;i++)
				{
					wait (3);

					click(DriverManager.getDriver().findElement(By.xpath("//div[@class='k-widget k-window' and contains(@style,'block')] //a[contains(@onclick,'Up')]//img")));
				}

				wait(3);

				click(DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'"+widgetName+"')]/parent::b/parent::div[contains(@style,'display: block')]//a[contains(text(),'Advanced Rule')]")));
				wait(3);

				CommonUtils.acceptAlert(true);

				if(DriverManager.getDriver().findElements(By.xpath("//div[contains(@style,'display: block')]//span[contains(text(),'Advanced Column rules')]")).size()>0)
				{
					System.out.println("On Advance Rule page");
				}
				else
				{
					System.out.println("Failed To display advance rule page");	
				}

				click(deleteCustomRule);

				wait(3);

				CommonUtils.acceptAlert(true);
				wait(3);

				CommonUtils.sendKeys(customRuleTextField, customRule);
				wait(5);

				click(saveCustomRule);

				List<WebElement> var=DriverManager.getDriver().findElements(By.xpath("//h3[@title='"+widgetName+"']/ancestor::div[@class='gscontent removable']//tbody//td[contains(text(),'=')]"));
				if(var.size()>0){
					//List<String> colNamesArr =new ArrayList<>(var.size());
					for(int c=0;c<var.size();c++){
						String colNameText = var.get(c).getText().trim();
						String[] adrule=colNameText.split("=");
						if(adrule[0].length()>0)
						{
							System.out.println("adrule:"+adrule[0]);	
						}
						else
						{
							break;
						}
					}
				}
			}
		}

		catch(Exception Ex)
		{
			Ex.printStackTrace();
			System.out.println("failed to add Advance rule");
			Assert.fail("failed to add Advance rule");
		}
	}
}

//		public void verifyAvailableColumns(String widgetName, List<String>ColumnToBeChecked)
//		{
//			waitForLoad(DriverManager.getDriver());
//			try {
//				if (IsWidgetVisible(widgetName)) {
//					wait(5);
//					click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
//							+ "']//ancestor::div[@class='widget_panel_header']//ul[contains(@id,'menu')]//img[@src='svg/more_link.svg']")));
//					wait(3);
//					click(DriverManager.getDriver().findElement(By.xpath("//h3[text()='" + widgetName
//							+ "']//ancestor::div[@class='widget_panel_header']//li[@class='k-item k-state-default k-first']")));
//					wait(5);
//				}
//					
//				} catch (Exception Ex) {
//					
//				}	
//		}

//	List<String> addedColumn = new ArrayList<String>();
//				List<WebElement> columnList = DriverManager.getDriver().findElements(By.xpath("//*[@title='" + widgetName
//						+ "']/ancestor::div[@class='widget_panel_header']/following-sibling::div//th[@role='columnheader']/a"));
//				System.out.println("The number of columns in the list: "+columnList.size());
//				for(WebElement anEle : columnList) {
//					addedColumn.add(anEle.getText());
//					System.out.println("column Name: "+anEle.getText());
//				}
//				int i = addedColumn.indexOf(columnName)+2;
//				
//				List<WebElement>columnData =DriverManager.getDriver().findElements(By.xpath("//*[@title='"+widgetName+"']/ancestor::div[@class='widget_panel_header']/following-sibling::div//div[contains(@class,'k-grid-content')]//td["+i+"]"));
//				
//				for(WebElement anEle1 : columnData) {
//					addedColumn.add(anEle1.getText());
//					
//				Assert.assertTrue(columnData.contains(anEle1.getText()));
//				System.out.println(anEle1.getText())



