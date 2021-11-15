package Hooks;

import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.assertj.core.api.SoftAssertions;
import Methods.defaultMethods;
import org.openqa.selenium.safari.SafariDriver;

import static Methods.defaultMethods.udidList;

public class hooks {
    public static AppiumDriver<MobileElement> driver;
    public static SoftAssertions softAssert;
    public static AppiumDriverLocalService driverLocalService = null;
    public static int defaultWaitTime = 30;
    public static Properties property;
    public static Properties appContentProp;
    public static defaultMethods defObj = new defaultMethods();
    public String appPath;
    public String profileName;
    public String envName;
    public static String platform;
    public WebDriver webdriver;
    public String browserName;
    public static ThreadLocal<Scenario> scenario = new ThreadLocal<>();
    private static final ThreadLocal<Properties> webContentProp = new ThreadLocal<>();
    public static ThreadLocal<Properties> envProp = new ThreadLocal<>();


    //########################################################################################################
    // METHOD NAME        : setUpNativeDriver
    // METHOD DESCRIPTION : To launch appium server, initialize Mobile Native driver before every scenario
    //########################################################################################################
    @Before("@mobileNative")
    public void setUpNativeDriver() throws MalformedURLException {

        profileName = getMavenSystemProperties("profileName");
        platform = getMavenSystemProperties("platform");
        defObj.getDevices();
        startAppiumServer();
        DesiredCapabilities capabilities = new DesiredCapabilities();
//Android capabilities
        if (platform.equalsIgnoreCase("android")) {
            System.out.println("Running on Android device");
            property = defObj.getRunTimeProperties("./src/main/resources/" + profileName + "/androidDevice");
            appContentProp = defObj.getRunTimeProperties("./src/test/resources/appContents/" + profileName + "/appContents");
            appPath = defObj.getFileAbsolutePath("app/" + profileName + "/Android/app-development-debug.apk");


            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("platformVersion", property.getProperty("androidVersion"));
            capabilities.setCapability("deviceName", property.getProperty("deviceName"));
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("udid", udidList.get(0));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, property.getProperty("newCommandTimeout"));
            capabilities.setCapability("noReset", property.getProperty("androidNoReset"));
            capabilities.setCapability("appPackage", property.getProperty("appPackage"));
            capabilities.setCapability("appActivity", property.getProperty("appActivity"));
            capabilities.setCapability("userProfile", property.getProperty("userProfile"));

            driver = new AndroidDriver<>(driverLocalService.getUrl(), capabilities);
        }
//	iOS capabilities
        else if (platform.equalsIgnoreCase("iOS")) {
            System.out.println("Running on iOS device");
            property = defObj.getRunTimeProperties("./src/main/resources/" + profileName + "/androidDevice");
            appContentProp = defObj.getRunTimeProperties("./src/test/resources/appContents/" + profileName + "/appContents");

/************************            iOS Yet to Implement

 capabilities.setCapability("platformName", property.getProperty("platformName"));
 capabilities.setCapability("platformVersion", property.getProperty("platformVersion"));
 capabilities.setCapability("app", property.getProperty("app"));
 capabilities.setCapability("bundleId", property.getProperty("bundleId"));
 capabilities.setCapability("wdaLocalPort", property.getProperty("wdaPort"));
 capabilities.setCapability("usePrebuiltWDA", property.getProperty("usePrebuiltWDA"));
 capabilities.setCapability("xcodeOrgId", property.getProperty("xcodeOrgId"));
 capabilities.setCapability("xcodeSigningId", property.getProperty("xcodeSigningId"));
 capabilities.setCapability("useNewWDA", property.getProperty("useNewWDA"));

 driver = new IOSDriver<MobileElement>(driverLocalService.getUrl(), capabilities);
 ********************************************/

        } else {
            System.out.println("Please provide a valid platform name in maven command...Refer readme");
        }

        driver.manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
        softAssert = new SoftAssertions();
    }

    //########################################################################################################
    // METHOD NAME        : tearNativeDriver
    // METHOD DESCRIPTION : To kill Mobile Native driver and appium server after every scenario
    //########################################################################################################
    @After("@mobileNative")
    public void tearNativeDriver(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed_Screen");
            }
            driver.quit();
            scenario.log("Appium Driver stopped");
        } catch (Exception e) {
            scenario.log("Driver value is null");
        } finally {
            driverLocalService.stop();
            scenario.log("Appium Server stopped");
        }
        softAssert.assertAll();
    }

    //########################################################################################################
    // METHOD NAME        : startAppiumServer
    // METHOD DESCRIPTION : To launch Appium server in any free port
    //########################################################################################################
    public void startAppiumServer() throws MalformedURLException {

//	Start Appium server
        try {
            driverLocalService = AppiumDriverLocalService
                    .buildService(new AppiumServiceBuilder()
                            .withIPAddress("127.0.0.1")
//                            .usingPort(4723)
                            .usingAnyFreePort()
                            .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                            .withArgument(GeneralServerFlag.LOG_LEVEL, "debug"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driverLocalService.start();
    }


    //########################################################################################################
    // METHOD NAME        : setWebDriver
    // METHOD DESCRIPTION : To initialize Web driver before every scenario
    //########################################################################################################
    @Before("@web")
    public synchronized void setWebDriver(Scenario scenario) {
        hooks.scenario.set(scenario);
        profileName = getMavenSystemProperties("profileName");
        envName = getMavenSystemProperties("env");
        webContentProp.set(defObj.getRunTimeProperties("./src/test/resources/appContents/" + profileName + "/appContents"));
        envProp.set(defObj.getRunTimeProperties("./src/main/resources/" + profileName + "/" + envName + "Details"));
        browserName = getMavenSystemProperties("browser");
        switch (browserName) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                webdriver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webdriver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                webdriver = new SafariDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webdriver = new EdgeDriver();
                break;
            default:
                System.out.println("Please provide a valid browser name in maven command...Refer readme");
        }

        defaultMethods.setTLDriver(webdriver);
        defaultMethods.getTLDriver().manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
        softAssert = new SoftAssertions();
    }

    //########################################################################################################
    // METHOD NAME        : setChromeWebDriver
    // METHOD DESCRIPTION : To initialize Chrome Web driver before every scenario
    //########################################################################################################
    @Before("@chrome")
    public synchronized void setChromeWebDriver(Scenario scenario) {
        hooks.scenario.set(scenario);
        profileName = getMavenSystemProperties("profileName");
        envName = getMavenSystemProperties("env");
        webContentProp.set(defObj.getRunTimeProperties("./src/test/resources/appContents/" + profileName + "/appContents"));
        setWebProp(webContentProp.get());
        envProp.set(defObj.getRunTimeProperties("./src/main/resources/" + profileName + "/" + envName + "Details"));

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        webdriver = new ChromeDriver(chromeOptions);

        defaultMethods.setTLDriver(webdriver);
        defaultMethods.getTLDriver().manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
        softAssert = new SoftAssertions();
    }

    //########################################################################################################
    // METHOD NAME        : setFirefoxWebDriver
    // METHOD DESCRIPTION : To initialize Firefox Web driver before every scenario
    //########################################################################################################
    @Before("@firefox")
    public synchronized void setFirefoxWebDriver(Scenario scenario) {
        hooks.scenario.set(scenario);
        profileName = getMavenSystemProperties("profileName");
        envName = getMavenSystemProperties("env");
        webContentProp.set(defObj.getRunTimeProperties("./src/test/resources/appContents/" + profileName + "/appContents"));
        setWebProp(webContentProp.get());
        envProp.set(defObj.getRunTimeProperties("./src/main/resources/" + profileName + "/" + envName + "Details"));

        WebDriverManager.firefoxdriver().setup();
        webdriver = new FirefoxDriver();

        defaultMethods.setTLDriver(webdriver);
        defaultMethods.getTLDriver().manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
        softAssert = new SoftAssertions();
    }

    //########################################################################################################
    // METHOD NAME        : setEdgeWebDriver
    // METHOD DESCRIPTION : To initialize Edge Web driver before every scenario
    //########################################################################################################
    @Before("@edge")
    public synchronized void setEdgeWebDriver(Scenario scenario) {
        hooks.scenario.set(scenario);
        profileName = getMavenSystemProperties("profileName");
        envName = getMavenSystemProperties("env");
        webContentProp.set(defObj.getRunTimeProperties("./src/test/resources/appContents/" + profileName + "/appContents"));
        setWebProp(webContentProp.get());
        envProp.set(defObj.getRunTimeProperties("./src/main/resources/" + profileName + "/" + envName + "Details"));

        WebDriverManager.edgedriver().setup();
        webdriver = new EdgeDriver();

        defaultMethods.setTLDriver(webdriver);
        defaultMethods.getTLDriver().manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
        softAssert = new SoftAssertions();
    }

    //########################################################################################################
    // METHOD NAME        : tearWebDriver
    // METHOD DESCRIPTION : To Kill Web driver after every scenario
    //########################################################################################################
    @After("@web")
    public synchronized void tearWebDriver(Scenario scenario) {
        if ((hooks.scenario.get()).isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) defaultMethods.getTLDriver()).getScreenshotAs(OutputType.BYTES);
            (hooks.scenario.get()).attach(screenshot, "image/png", "Failed_Screen");
        }
        webdriver.quit();
        softAssert.assertAll();
    }

    //########################################################################################################
    // METHOD NAME        : tearChromeWebDriver
    // METHOD DESCRIPTION : To Kill Chrome Web driver after every scenario
    //########################################################################################################
    @After("@chrome")
    public synchronized void tearChromeWebDriver(Scenario scenario) {
        if ((hooks.scenario.get()).isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) defaultMethods.getTLDriver()).getScreenshotAs(OutputType.BYTES);
            (hooks.scenario.get()).attach(screenshot, "image/png", "Failed_Screen");
        }
        webdriver.quit();
        softAssert.assertAll();
    }

    //########################################################################################################
    // METHOD NAME        : tearFirefoxWebDriver
    // METHOD DESCRIPTION : To Kill Firefox Web driver after every scenario
    //########################################################################################################
    @After("@firefox")
    public synchronized void tearFirefoxWebDriver(Scenario scenario) {
        if ((hooks.scenario.get()).isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) defaultMethods.getTLDriver()).getScreenshotAs(OutputType.BYTES);
            (hooks.scenario.get()).attach(screenshot, "image/png", "Failed_Screen");
        }
        webdriver.quit();
        softAssert.assertAll();
    }

    //########################################################################################################
    // METHOD NAME        : tearEdgeWebDriver
    // METHOD DESCRIPTION : To Kill Edge Web driver after every scenario
    //########################################################################################################
    @After("@edge")
    public synchronized void tearEdgeWebDriver(Scenario scenario) {
        if ((hooks.scenario.get()).isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) defaultMethods.getTLDriver()).getScreenshotAs(OutputType.BYTES);
            (hooks.scenario.get()).attach(screenshot, "image/png", "Failed_Screen");
        }
        webdriver.quit();
        softAssert.assertAll();
    }

    //########################################################################################################
    // METHOD NAME        : getMavenSystemProperties
    // METHOD DESCRIPTION : To get Maven System Property
    //########################################################################################################
    public String getMavenSystemProperties(String propValue) {
        return System.getProperty(propValue);
    }

    //########################################################################################################
    // METHOD NAME        : getWebScreenshot
    // METHOD DESCRIPTION : To take screenshot of any screen during execution and embed to report
    //########################################################################################################
    public static synchronized void getWebScreenshot() {
        final byte[] screenshot = ((TakesScreenshot) defaultMethods.getTLDriver()).getScreenshotAs(OutputType.BYTES);
        (hooks.scenario.get()).attach(screenshot, "image/png", "Passed_Screen");
    }

    //########################################################################################################
    // METHOD NAME        : logToReport
    // METHOD DESCRIPTION : To log contents to report
    //########################################################################################################
    public static synchronized void logToReport(String message) {
        (hooks.scenario.get()).log(message);
    }

    //########################################################################################################
    // METHOD NAME        : getWebProp
    // METHOD DESCRIPTION : To get instance on WebContent Property file
    //########################################################################################################
    public static Properties getWebProp() {
        return webContentProp.get();
    }

    //########################################################################################################
    // METHOD NAME        : setWebProp
    // METHOD DESCRIPTION : To set instance on WebContent Property file
    //########################################################################################################
    public static void setWebProp(Properties webContentProp) {
        hooks.webContentProp.set(webContentProp);
    }

}
