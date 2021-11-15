package services.weatherShopper;

import Hooks.hooks;
import Methods.defaultMethods;
import org.junit.Assert;
import pageObject.weatherShopper.homePageObjects;

import static Hooks.hooks.*;

public class homePageMethods {
    homePageObjects homePageObj = new homePageObjects(defaultMethods.getTLDriver());
    public static ThreadLocal<String> productSelected = new ThreadLocal<>();

    //########################################################################################################
    // METHOD NAME        : valHomePage
    // METHOD DESCRIPTION : To validate Home Page
    //########################################################################################################
    public synchronized void valHomePage() {
        defaultMethods.getTLDriver().get(envProp.get().getProperty("url"));
        defaultMethods.getTLDriver().manage().window().maximize();
        Assert.assertEquals(getWebProp().getProperty("homePageTitle"),(defaultMethods.getTLDriver()).getTitle());
        hooks.softAssert.assertThat(homePageObj.pageHeader.getText()).isEqualTo(getWebProp().getProperty("pageHeader"));
        homePageObj.temperature.isDisplayed();

        hooks.softAssert.assertThat(homePageObj.prodType1Header.getText()).isEqualTo(getWebProp().getProperty("prodType1Header"));
        hooks.softAssert.assertThat(homePageObj.prodType1Content.getText()).isEqualTo(getWebProp().getProperty("prodType1Content"));
        hooks.softAssert.assertThat(homePageObj.prodType1SelectorButton.getText()).isEqualTo(getWebProp().getProperty("prodType1SelectorButton"));

        hooks.softAssert.assertThat(homePageObj.prodType2Header.getText()).isEqualTo(getWebProp().getProperty("prodType2Header"));
        hooks.softAssert.assertThat(homePageObj.prodType2Content.getText()).isEqualTo(getWebProp().getProperty("prodType2Content"));
        hooks.softAssert.assertThat(homePageObj.prodType2SelectorButton.getText()).isEqualTo(getWebProp().getProperty("prodType2SelectorButton"));

    }

    //########################################################################################################
    // METHOD NAME        : chooseProductType
    // METHOD DESCRIPTION : To choose a product type based on current weather
    //########################################################################################################
    public synchronized void chooseProductType() {
        String[] a = homePageObj.temperature.getText().split("\\s+");
        int currTemp = Integer.parseInt(a[0]);
        System.out.println("Temperature:"+currTemp);

        if(currTemp<19){
            homePageObj.prodType1SelectorButton.click();
            Assert.assertEquals(getWebProp().getProperty("moisturizersPageTitle"),defaultMethods.getTLDriver().getTitle());
            productSelected.set("Moisturizers");
        }
        else if(currTemp>34){
            homePageObj.prodType2SelectorButton.click();
            Assert.assertEquals(getWebProp().getProperty("sunscreensPageTitle"),defaultMethods.getTLDriver().getTitle());
            productSelected.set("Sunscreens");
        }
        else{
            Assert.fail("Current Temperature on application doesn't satisfy any of the given task conditions, hence stopping the execution");
        }

    }
}
