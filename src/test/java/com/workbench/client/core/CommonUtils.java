package com.workbench.client.core;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.core.buffer.Strided1DWritableBuffer;
import org.testng.Assert;

import com.google.common.base.Function;
import com.workbench.client.core.Config.LocatorStrategy;
import com.workbench.client.core.Config.ScrollDiection;

import ru.yandex.qatools.allure.annotations.Step;

@SuppressWarnings({ "unused" })
public class CommonUtils {
	private static Process process;
	private static int reliableRetryClickCount = 0;
	private static boolean reliableRetryClickFlag = true;
	private static Robot robot;
	private static Wait<WebDriver> fluentWait;
	private static WebElement element;
	private static JavascriptExecutor javaScriptExecutor;
	private static Actions actions;
	private int startX, startY, endX, endY;



	// ***************** Actions and Events methods *******************

	@Step("Moving to Element By Locator : {0}")
	public static void moveToElementByLoc( By loc) {
		try
		{
			Actions a = new Actions(DriverManager.getDriver());
			a.moveToElement(DriverManager.getDriver().findElement(loc)).build().perform();
		}
		catch(NoSuchElementException Ex) {
			Ex.printStackTrace();
			System.out.println("Failed to perform moveToElement");
		}
	}

	@Step("Moving to Element :{0}")
	public static void moveToElement( WebElement element) {
		actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}

	@Step("Double Click on  Element : {0}")
	public static void doubleClick(WebElement element) {
		actions = new Actions(DriverManager.getDriver());
		actions.doubleClick(element).build().perform();
	}

	@Step("Moving and Clicking on Element : {0}")
	public static void moveAndClickElement(WebElement element) {
		actions.moveToElement(element).click().build().perform();
	}

	// *********************** Scroll methods **************************************

	@Step("Scrolling by Steps : {0}")
	public void scroll(int scrollSteps, int scrollCount) {
		javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
		for (int i = 0; i < scrollCount; i++) {
			javaScriptExecutor.executeScript("window.scrollBy(0," + scrollSteps + ")", "");
		}
	}

	@Step("Scrolling to the Element : {0}")
	public boolean scrollToElement(WebElement element) {
		try {
			javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
			javaScriptExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			if (element.isDisplayed())
				return true;
			else
				return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	@Step("Scrolling to the Element : {0}")
	public boolean scrollToMiddle() {
		try {
			javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
			javaScriptExecutor.executeScript("window.scrollBy(0,new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().getScreenSize()/2)");
			if (element.isDisplayed())
				return true;
			else
				return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	@Step("Scrolling to the Element :{0}  in the direction : {1}")
	public boolean scrollToElement(WebElement element, ScrollDiection scrollDirection, int scrollCount) {
		if (isDisplayed(10, 1, element))
			return true;
		for (int i = 0; i < scrollCount; i++) {
			if (scrollDirection == ScrollDiection.DOWN) {
				javaScriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				CommonUtils.wait(2);
				if (isDisplayed(10, 1, element))
					return true;
			} else if (scrollDirection == ScrollDiection.UP) {
				javaScriptExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				CommonUtils.wait(2);
				if (isDisplayed(10, 1, element))
					return true;
			}
		}
		return false;
	}

	@Step("Pop up scrolling : {0}")
	public void popupScroll(WebElement parentElement, WebElement childElement, ScrollDiection scrollDirection,
			int scrollCount) {
		javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
		for (int i = 0; i < scrollCount; i++) {
			if (scrollDirection == ScrollDiection.DOWN) {
				javaScriptExecutor.executeScript("arguments[0].scrollTop = arguments[1];", parentElement, 100);
				if (isDisplayed(10, 1, childElement))
					break;
			} else if (scrollDirection == ScrollDiection.UP) {
				javaScriptExecutor.executeScript("arguments[0].scrollBottom = arguments[1];", parentElement, 100);
				if (isDisplayed(10, 1, childElement))
					break;
			}
		}
		CommonUtils.wait(2);
	}

	@Step("Pop up scrolling to element : {0}")
	public void webPopupScroll(WebElement parentElement, int scrollSteps, int scrollCount) {
		javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
		if (scrollSteps <= 0)
			javaScriptExecutor.executeScript("arguments[0].scrollTop = arguments[1];", parentElement,
					Math.abs(scrollSteps));
		else if (scrollSteps > 0)
			javaScriptExecutor.executeScript("arguments[0].scrollBottom = arguments[1];", parentElement,
					Math.abs(scrollSteps));
		CommonUtils.wait(2);
	}

	@Step("Scroll to bottom of the page")
	public static void scrollToBottom() {
		javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
		javaScriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	@Step("Scroll to Top of the page")
	public static void scrollToTop() {
		javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
		javaScriptExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}

	// **************************** Find ELEMENT METHODS ******************

	@Step("Getting the webElement : {1}")
	public static WebElement getElement(LocatorStrategy webLocatorStrategy, String webAttributeValue) {
		WebElement returnElement = null;

		try {

			switch (webLocatorStrategy) {
			case WEB_LOCATOR_STRATEGY_ID:
				returnElement = DriverManager.getDriver().findElement(By.id(webAttributeValue));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH:
				returnElement = DriverManager.getDriver().findElement(By.xpath(webAttributeValue));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH_TEXT:
				returnElement = DriverManager.getDriver().findElement(By.xpath("//*[. = '" + webAttributeValue + "']"));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH_DIV_TEXT_CONTAINS:
				returnElement = DriverManager.getDriver()
				.findElement(By.xpath("//*[contains(text(), '" + webAttributeValue + "')]"));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH_SPAN_TEXT_CONTAINS:
				returnElement = DriverManager.getDriver()
				.findElement(By.xpath("//span[contains(text(), '" + webAttributeValue + "')]"));
				break;
			case WEB_LOCATOR_STRATEGY_CSS:
				returnElement = DriverManager.getDriver().findElement(By.cssSelector(webAttributeValue));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH_TEXT_CONTAINS:
				returnElement = DriverManager.getDriver()
				.findElement(By.xpath("//*[contains(text(), '" + webAttributeValue + "')]"));
				break;
			default:
			}
			return returnElement;

		} catch (Throwable e) {
			return null;
		}
	}

	@Step("Getting the list of webElements")
	public static List<WebElement> getElements(WebElement parentElement, LocatorStrategy webLocatorStrategy,
			String webAttributeValue) {
		try {
			List<WebElement> returnElement = null;

			switch (webLocatorStrategy) {
			case WEB_LOCATOR_STRATEGY_ID:
				returnElement = parentElement.findElements(By.id(webAttributeValue));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH:
				returnElement = parentElement.findElements(By.xpath(webAttributeValue));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH_TEXT:
				returnElement = parentElement.findElements(By.xpath("//*[. = '" + webAttributeValue + "']"));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH_DIV_TEXT_CONTAINS:
				returnElement = parentElement
				.findElements(By.xpath("//div[contains(text(), '" + webAttributeValue + "')]"));
				break;
			case WEB_LOCATOR_STRATEGY_XPATH_SPAN_TEXT_CONTAINS:
				returnElement = parentElement
				.findElements(By.xpath("//span[contains(text(), '" + webAttributeValue + "')]"));
				break;
			case WEB_LOCATOR_STRATEGY_CSS:
				returnElement = parentElement.findElements(By.cssSelector(webAttributeValue));
				break;
			case WEB_LOCATOR_STRATEGY_CSS_INPUT_DATA_FEATURE:
				returnElement = parentElement
				.findElements(By.cssSelector("input[data-feature = '" + webAttributeValue + "']"));
				break;
			default:
			}
			return returnElement;

		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}

	}

	// **************** IS DISPLAYED / ENABLED METHODS *************************

	@Step("Is element is Displayed : {2}")
	public static boolean isDisplayed(int timeoutInSeconds, int pollingTimeInSeconds, WebElement aNelement) {
		try {
			fluentWait = new FluentWait<WebDriver>(DriverManager.getDriver())
					.pollingEvery(Duration.ofSeconds(pollingTimeInSeconds))
					.withTimeout(Duration.ofSeconds(timeoutInSeconds))
					.ignoring(NoSuchElementException.class, Error.class);
			element = aNelement;
			return fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					return element.isDisplayed();
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	@Step("Is Displayed element is enabled : {0}")
	public static boolean isDisplayedAndEnabled(int timeoutInSeconds, int pollingTimeInSeconds, WebElement aNelement) {
		try {
			if (timeoutInSeconds == 0)
				timeoutInSeconds = Config.TIMEOUT_IN_SECONDS;
			if (pollingTimeInSeconds == 0)
				pollingTimeInSeconds = Config.POLLING_TIME_IN_SECONDS;
			fluentWait = new FluentWait<WebDriver>(DriverManager.getDriver())
					.pollingEvery(Duration.ofSeconds(pollingTimeInSeconds))
					.withTimeout(Duration.ofSeconds(timeoutInSeconds))
					.ignoring(NoSuchElementException.class, Error.class);
			element = aNelement;
			return fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (element.isDisplayed() && element.isEnabled());
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	// ****************** CLICK METHODS ****************************

	/*@Step("Clicking on the WebElement : {0}")
	public static void click(WebElement element) {
		try {
			if (isDisplayed(Config.TIMEOUT_IN_SECONDS, Config.POLLING_TIME_IN_SECONDS, element))
				element.click();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}*/

	@Step("Clicking on the WebElement : {0}")
	public static void click(WebElement element) {
		try {
			waitForLoad(DriverManager.getDriver());
			(new WebDriverWait(DriverManager.getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


	@Step("Clicking on the WebElement : {2}")
	public static void click(int timeoutInSeconds, int pollingTimeInSeconds, WebElement elementToBeClicked,
			WebElement elementToBeVisibleAfterClick, int retryCount) throws Exception {
		try {
			boolean retryFlag = true;
			int retryCounter = 0;
			boolean clickFlag = false;
			while (retryFlag) {
				if (isDisplayedAndEnabled(timeoutInSeconds, pollingTimeInSeconds, elementToBeClicked)) {
					elementToBeClicked.click();
					clickFlag = true;
				}
				if (clickFlag && isDisplayed(10, 1, elementToBeVisibleAfterClick)) {
					retryFlag = false;
				} else if (retryCounter == retryCount) {
					retryFlag = false;
				} else {
					retryCounter++;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	@Step("Clicking on the WebElement : {0}")
	public static void jsclick(WebElement element) {
		try {
			javaScriptExecutor.executeScript("arguments[0].click();", element);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	@Step("Selecting from the drop down by index :{1}")
	public void selectFromDropdown(WebElement element, String option) {
		Select aSelect = new Select(element);
		aSelect.selectByVisibleText(option);
	}

	//*To select the sales person randomly*/
	@Step("Selecting from the drop down by index :{1}")
	public void selectFromDropdown(WebElement element, int option) {
		Select aSelect = new Select(element);
		aSelect.selectByIndex(option);
	}

	public static void clickEnterKey() {
		try {
			robot = new Robot();
			CommonUtils.wait(1);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			CommonUtils.wait(1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Step("Clearing on the WebElement : {0}")
	public static void clear(WebElement element) {
		try {
			waitForLoad(DriverManager.getDriver());
			(new WebDriverWait(DriverManager.getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// ************** SEND KEYS METHODS **************************

	@Step("Sending Text : {1} to element : {0}")
	public static void sendKeys(WebElement element, String textToBeTyped) {
		try {
			waitForLoad(DriverManager.getDriver());
			(new WebDriverWait(DriverManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(textToBeTyped);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Step("Sending Text : {3} to element : {2}")
	public static void sendKeys(int timeoutInSeconds, int pollingTimeInSeconds, WebElement element,
			String textToBeTyped, int retryCount) {
		try {
			boolean retryFlag = true;
			boolean setTextFlag = false;
			int retryCounter = 0;
			while (retryFlag) {
				if (isDisplayed(timeoutInSeconds, pollingTimeInSeconds, element)) {

					{
						element.clear();
						element.sendKeys(textToBeTyped);
						setTextFlag = true;
					}
				}
				if (setTextFlag) {
					if (element.getAttribute("value").contains(textToBeTyped)) {
						retryFlag = false;
					} else {
						retryCounter++;
					}
				}
				if (retryCounter == retryCount) {
					retryFlag = false;
				} else {
					retryCounter++;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


	//********************** Sorting the List ***************************


	@Step("Sorting the list{0} with ascending order")
	public static boolean ascendingOrderSort(List<WebElement> ElementList) {
		ArrayList<String> obtainedList = new ArrayList<>();
		for (WebElement anEle : ElementList) {
			obtainedList.add(anEle.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		return (sortedList.equals(obtainedList));

	}

	@Step("Sorting the list{0} with descending order")
	public static boolean descendingOrderSort(List<WebElement> ElementList) {
		ArrayList<String> obtainedList = new ArrayList<>();
		for (WebElement anEle : ElementList) {
			obtainedList.add(anEle.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return (sortedList.equals(obtainedList));

	}

	//************************ File Utils Methods ******************************


	@Step("Is file download at location:{0}")
	public static boolean isFileDownloaded(String downloadPath) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		Assert.assertTrue(dir_contents.length > 0);
		for (File file : dir_contents) {
			if (file.length() > 0) {
				flag = true;
			}
		}
		isAllFileDeleted(downloadPath);
		return flag;
	}

	@Step("Deleting the Files at location :{0}")
	public static void isAllFileDeleted(String downLoadpath) {
		System.out.println(downLoadpath);
		File dir = new File(downLoadpath);
		for (File file : dir.listFiles()) {
			if (!file.isDirectory()) {
				file.delete();
			}

		}
		dir.delete();
	}

	// ************************ Miscellaneous methods *****************************

	@Step("Executing Command : {0} ")
	public static void executeCommand(String command) {
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (Exception e) {
		}
	}

	@Step("Waiting for : {0} seconds")
	public static void wait(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (Exception e) {
		}
	}

	@Step("Waiting for the Page to Load Completely")
	public static void waitForLoad(WebDriver driver) {
		Log.info("waiting to load the page");
		new WebDriverWait(driver, 120).until((ExpectedCondition<Boolean>) wd ->
		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	@Step("waiting for the element{0} to load")
	public static void waitForElement(WebElement ele) {
		WebDriverWait wait3 = new WebDriverWait(DriverManager.getDriver(), 20);
		wait3.until(ExpectedConditions.visibilityOf((ele)));
	}

	@Step("Spliting the string : {0} with separator : {1}")
	public static String[] split(String mainString, String... sepeartor) {
		String getString = Arrays.toString(sepeartor);
		return mainString.split(getString);
	}

	@Step("Get the screen width")
	public int getScreenWidth() {
		return DriverManager.getDriver().manage().window().getSize().getWidth();
	}

	@Step("Get the Screen height")
	public int getScreenHeight() {
		return DriverManager.getDriver().manage().window().getSize().getHeight();
	}

	@Step("Get the Element location : {0}")
	public Point getElementLocation(WebElement element) {
		return element.getLocation();
	}

	@Step("Get the Element Dimension : {0}")
	public Dimension getElementDimension(WebElement element) {
		return element.getSize();
	}

	@Step("Getting the User home directory path")
	public static String getUserHomeDirectoryPath() {
		return System.getProperty("user.home");
	}

	@Step("Getting the  User Current directory path")
	public static String getUserCurrentDirectoryPath() {
		return System.getProperty("user.dir");
	}

	@Step("switching to Frame : {0}")
	public void switchToFrame(WebElement element) {
		DriverManager.getDriver().switchTo().frame(element);
	}

	@Step("switching to default Content")
	public static void switchToDefaultContent() {
		DriverManager.getDriver().switchTo().defaultContent();
	}

	@Step("get the current Frame")
	public Object getCurrentFrame() {
		return javaScriptExecutor.executeScript("return self.name");
	}

	@Step("execute JavaScript")
	public  static Object jsExeCute(String script) {
		return javaScriptExecutor.executeScript(script);
	}

	@Step("accept the alert")
	public static void acceptAlert(Boolean value) {
		WebDriverWait wait  = new WebDriverWait(DriverManager.getDriver(), 20);
		wait.until(ExpectedConditions.alertIsPresent());
		if (value) {
			DriverManager.getDriver().switchTo().alert().accept();
		} else {
			DriverManager.getDriver().switchTo().alert().dismiss();
		}
	}

	@Step("get future Date of :{0} days from current date")
	public String getFutureDate(int numberOfDayToAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, numberOfDayToAdd);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		return String.valueOf(sdf.format(c.getTime()));

	}

	@Step("get future Date of :{0} days from current date")
	public String getDate(int numberOfDayToAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, numberOfDayToAdd);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return String.valueOf(sdf.format(c.getTime()));

	}

	@Step("switch to new Tab ")
	public static void  switchToNewTab(String tabName) {
		//	String parentWin = DriverManager.getDriver().getWindowHandle();
		Set<String> newTabs = DriverManager.getDriver().getWindowHandles();
		for(String aTabName: newTabs) {
			if (DriverManager.getDriver().switchTo().window(aTabName).getTitle().contains(tabName)) {

			}
		}

	}


}
