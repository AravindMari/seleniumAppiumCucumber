package Methods;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.android.Android;
import com.testvagrant.mdb.ios.IOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import static Hooks.hooks.*;

public class defaultMethods {
    public static final Properties prop = new Properties();
    public static List<String> udidList = new ArrayList<>();
    public static ThreadLocal<WebDriver> TLwebdriver = new ThreadLocal<>();

    //########################################################################################################
    // METHOD NAME        : is_element_available
    // METHOD DESCRIPTION : To check an element is available in the page without failing the test case
    //########################################################################################################
    public void getDevices() {
        List<DeviceDetails> deviceDetails;
        if (platform.equalsIgnoreCase("android")) {
            System.out.println("Android device Connected");
            deviceDetails = new Android().getDevices();
        } else {
            System.out.println("iOS device Connected");
            deviceDetails = new IOS().getDevices();
        }

        for (DeviceDetails dd : deviceDetails) {
            udidList.add(dd.getUdid());
        }

        System.out.println("deviceDetails:" + deviceDetails);
        System.out.println("udid of first device:" + udidList.get(0));
    }


    //########################################################################################################
    // METHOD NAME        : is_element_available
    // METHOD DESCRIPTION : To check an element is available in the page without failing the test case
    //########################################################################################################
    public boolean is_element_available(String element) {
        boolean success = false;
        try {
            get_object(element).isDisplayed();
            success = true;
        } catch (NoSuchElementException ignored) {
            success = false;
        } finally {
            return success;
        }
    }

    //########################################################################################################
    // METHOD NAME        : get_object
    // METHOD DESCRIPTION : To fetch object w.r.to it's type from inputted string
    //########################################################################################################
    public WebElement get_object(String element) {
        String[] element1;
        WebElement elem = null;
        element1 = element.split("~");
        String type = element1[0];
        String value = element1[1];
        switch (type) {
            case "id":
                elem = (driver.findElement(By.id(value)));
                break;
            case "xpath":
                elem = (driver.findElement(By.xpath(value)));
                break;
            case "class":
                elem = (driver.findElement(By.className(value)));
                break;
            default:
                System.out.println("Error: Check the object defined syntax");
        }
        return elem;
    }

    //########################################################################################################
    // METHOD NAME        : getRunTimeProperties
    // METHOD DESCRIPTION : To fetch a property value from properties file using runtime path
    //########################################################################################################
    public Properties getRunTimeProperties(String path) {
        try {
//            prop = new Properties();
            InputStream input = new FileInputStream(path + ".properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;

    }

    //########################################################################################################
    // METHOD NAME        : getFileAbsolutePath
    // METHOD DESCRIPTION : To get absolute path of a file
    //########################################################################################################
    public String getFileAbsolutePath(String applicationPath) {
        File rootFile = new File("");
        File appFile = new File(rootFile.getAbsolutePath() + applicationPath);
        return appFile.getAbsolutePath();
    }

    //########################################################################################################
    // METHOD NAME        : switchContext
    // METHOD DESCRIPTION : To switch Mobile view/context
    //########################################################################################################
    public static void switchContext(String context) {
        driver.getContext();
        Set<String> con = driver.getContextHandles();
        for (String c : con) {
            if (c.contains(context)) {
                driver.context(c);
                System.out.println("Switched to " + context + " view");
                break;
            }
        }
    }


    //########################################################################################################
    // METHOD NAME        : nativeValEleNoFail
    // METHOD DESCRIPTION : To verify an Element availability without fail in Native App
    //########################################################################################################
    public boolean nativeValEleNoFail(String element, AppiumDriver<MobileElement> driver) {
        boolean success = false;
        try {
            getLocatorNativeMobileApp(element, driver).isDisplayed();
            success = true;
        } catch (NoSuchElementException ignored) {
            success = false;
        } finally {
            return success;
        }
    }

    //########################################################################################################
    // METHOD NAME        : getLocatorNativeMobileApp
    // METHOD DESCRIPTION : To fetch locator w.r.to it's type from inputted string from Native Mobile App
    //########################################################################################################
    public WebElement getLocatorNativeMobileApp(String element, AppiumDriver<MobileElement> driver) {
        String[] element1;
        MobileElement elem = null;
        element1 = element.split("~");
        String type = element1[0];
        String value = element1[1];
        switch (type) {
            case "id":
                elem = (driver.findElement(By.id(value)));
                break;
            case "xpath":
                elem = (driver.findElement(By.xpath(value)));
                break;
            case "class":
                elem = (driver.findElement(By.className(value)));
                break;
            default:
                System.out.println("Error: Check the object defined syntax");
        }
        return elem;
    }


    //########################################################################################################
    // METHOD NAME        : setWebDriver
    // METHOD DESCRIPTION : To set Web driver instance and use for parallel runs
    //########################################################################################################
    public static synchronized void setTLDriver(WebDriver driver) {
        TLwebdriver.set(driver);
    }

    //########################################################################################################
    // METHOD NAME        : getTLDriver
    // METHOD DESCRIPTION : To get Web driver instance and use for parallel runs
    //########################################################################################################
    public static synchronized WebDriver getTLDriver() {
        return TLwebdriver.get();
    }
}
