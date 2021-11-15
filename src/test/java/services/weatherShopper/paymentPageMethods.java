package services.weatherShopper;

import Hooks.hooks;
import Methods.defaultMethods;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import pageObject.weatherShopper.paymentPageObjects;
import pageObject.weatherShopper.paySuccessPageObjects;
import static Hooks.hooks.*;
import static services.weatherShopper.productsPageMethods.*;

public class paymentPageMethods {
    paymentPageObjects payPageObj = new paymentPageObjects(defaultMethods.getTLDriver());
    paySuccessPageObjects valPaySucPageObj = new paySuccessPageObjects(defaultMethods.getTLDriver());

    //########################################################################################################
    // METHOD NAME        : makePayment
    // METHOD DESCRIPTION : To add payment details and make payment
    //########################################################################################################
    public synchronized void makePayment() {
        defaultMethods.getTLDriver().switchTo().frame(0);
        payPageObj.payImage.isDisplayed();
        payPageObj.payCloseButton.isDisplayed();
        hooks.softAssert.assertThat(payPageObj.payHeader1.getAttribute("innerHTML")).isEqualTo(getWebProp().getProperty("payHeader1"));
        hooks.softAssert.assertThat(payPageObj.payHeader2.getAttribute("innerHTML")).isEqualTo(getWebProp().getProperty("payHeader2"));
        hooks.softAssert.assertThat(payPageObj.paySubmit.getAttribute("innerHTML")).contains("Pay INR");
        hooks.softAssert.assertThat(payPageObj.paySubmit.getAttribute("innerHTML")).contains(totalPrice.get()+".00");
        payPageObj.payHeader1.isDisplayed();
        payPageObj.payHeader2.isDisplayed();
        payPageObj.payEmail.sendKeys("aravind11@gmail.com");
        payPageObj.cardNumber.clear();
        JavascriptExecutor jExe = (JavascriptExecutor)defaultMethods.getTLDriver();
        jExe.executeScript("document.getElementById('card_number').value='4242 4242 4242 4242'");
        payPageObj.cardExp.sendKeys("02");
        payPageObj.cardExp.sendKeys("2025");
        payPageObj.cardCVC.sendKeys("234");
        payPageObj.payZip.sendKeys("654556");
        payPageObj.paySubmit.click();
        defaultMethods.getTLDriver().switchTo().defaultContent();

    }

    //########################################################################################################
    // METHOD NAME        : validatePaySuccess
    // METHOD DESCRIPTION : To validate payment successful screen
    //########################################################################################################
    public synchronized void validatePaySuccess() {
        Assert.assertEquals(getWebProp().getProperty("paySuccessContent"),valPaySucPageObj.paySuccessContent.getText());
        Assert.assertEquals(getWebProp().getProperty("paySucPageHeader"),valPaySucPageObj.paySucPageHeader.getText());
        getWebScreenshot();
        logToReport("Payment Successful");
    }

}
