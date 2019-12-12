package com.workbench.client.core;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.workbench.client.pages.DataSourceConfigPage;
import com.workbench.client.pages.DataSourceConfigPage.DatabaeType;
import com.workbench.client.performance.EventListnerUtils;

@Listeners(ListenerUtils.class)
public class DriverFactory {
	public static WebDriver webdriver;
	public static EventFiringWebDriver driver;
	public static EventListnerUtils eventlistner;
//	public DataSourceConfigPage data ;
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	private Log log;
	private ChromeOptions chromeOptions = new ChromeOptions();

	@BeforeTest(groups = { Config.REGRESSION_TEST, Config.TEST, Config.DATABASE_CONFIG_TEST })
	@Parameters({ "browserName", "environmentName", "loginid", "password", "performanceFlag", "reTryFlag",
			"browserVersion", "database" })
	public void setUp(@Optional("null") String browserName, @Optional("null") String environmentName,
			@Optional("null") String loginid, @Optional("null") String password,
			@Optional("false") Boolean performanceFlag, @Optional("true") Boolean reTryFlag,
			@Optional("null") String browserVersion, @Optional("null") String database) throws Exception {
		// data= new DataSourceConfigPage();
		eventlistner = new EventListnerUtils();
		AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.<String, String>builder()
				.put(Config.BROWSER, browserName).put(Config.Browser_Version, browserVersion)
				.put(Config.ENVIRONMENT, environmentName)
				.put(Config.APPLICATION_URL, FileUtil.readFromPropertyFile(Config.Env_Property, "BASEURL")).build());

		DriverManager.setLog(log);
		DriverManager.setReTryFlag(reTryFlag);
		// DriverManager.setDataBaseConfig(data);

		try {
			if (browserName.equals("ChromeHeadless")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivercanary");
				chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("window-size=2560x1600");
				webdriver = new ChromeDriver(chromeOptions);
				DriverManager.setBrowserName("ChromeHeadless");
			}

			else if (browserName.equals("Chrome")) {

				System.setProperty("webdriver.chrome.driver", Config.DRIVER_PATH + "/chromedriver.exe");

				LoggingPreferences loggingprefs = new LoggingPreferences();
				loggingprefs.enable(LogType.BROWSER, Level.INFO);
				loggingprefs.enable(LogType.PERFORMANCE, Level.INFO);
				capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", Config.getDownloadFolder());
				prefs.put("download.prompt_for_download", false);
				prefs.put("profile.default_content_settings.popups", 0);
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setExperimentalOption("prefs", prefs);
				chromeOptions.addArguments("--disable-pdf-material-ui");
				chromeOptions.addArguments("--disable-out-of-process-pdf");
				chromeOptions.addArguments("--start-maximized");

				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

				webdriver = new ChromeDriver(chromeOptions);
				DriverManager.setBrowserName(Config.CHROME_DRIVER);

			} else if (browserName.equals("Firefox")) {

				System.setProperty("webdriver.gecko.driver", Config.DRIVER_PATH + "/geckodriver.exe");

				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("browser.download.folderList", 2);
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
				firefoxProfile.setPreference("browser.download.dir", Config.DEFAULT_DOWNLOAD_PATH);
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xls");
				firefoxProfile.setPreference("pdfjs.disabled", true);
				firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
				firefoxProfile.setPreference("plugin.scan.plid.all", false);
				firefoxProfile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");

				// JavaScriptError.addExtension(firefoxProfile);
				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(firefoxProfile);
				webdriver = new FirefoxDriver(options);
				DriverManager.setBrowserName(Config.FIREFOX_DRIVER);
			}

			driver = new EventFiringWebDriver(webdriver);
			DriverManager.setDriver(driver);
			DriverManager.setDataBase(database);

			if (performanceFlag) {
				driver.register(eventlistner);
			}

			if (database.equals("progress") || database.equals("mssqlserver")) {
				driver.get(FileUtil.readFromPropertyFile(Config.Env_Property, "BASEURL"));
			} else if (database.equals("")) {
				driver.get(FileUtil.readFromPropertyFile(Config.Env_Property, "LAUNCHURL"));
			}

			DriverManager.setBrowserName(browserName);
			DriverManager.setloginid(loginid);
//			DriverManager.setPassword(password);
			// Ensure to load the page
			CommonUtils.waitForLoad(DriverManager.getDriver());

			// login = new LogInPage();
			// login.login();

		} catch (

		Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterTest(groups = { Config.REGRESSION_TEST, Config.TEST, Config.DATABASE_CONFIG_TEST })
	public void tearDown() {
		try {
			CommonUtils.wait(2);
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
