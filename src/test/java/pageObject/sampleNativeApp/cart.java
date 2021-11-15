package pageObject.sampleNativeApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cart {
    public WebDriverWait cartWait;

    @AndroidFindBy(id = "empty")
//    @iOSXCUITFindBy(id = "")
    public MobileElement emptyCartButton;

    @AndroidFindBy(id = "delete")
    public MobileElement confirmEmptyCart;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'OK')]")
    public MobileElement emptyCartOkButton;

    @AndroidFindBy(id = "toolbar_title")
    public MobileElement cartHeader;

    @AndroidFindBy(id = "placeorder")
    public MobileElement placeOrder;

    @AndroidFindBy(id = "logot")
    public MobileElement cartLogo;

    @AndroidFindBy(id = "changeaddress")
    public MobileElement changeAddress;

    @AndroidFindBy(id = "save")
    public MobileElement addressSave;

    @AndroidFindBy(id = "customer")
    public MobileElement customerName;

    @AndroidFindBy(id = "mobile")
    public MobileElement mobileNumber;

    @AndroidFindBy(id = "address")
    public MobileElement address;

    @AndroidFindBy(id = "state")
    public MobileElement state;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[contains(@text,'Andhra Pradesh')]")
    public MobileElement stateValue;

    @AndroidFindBy(id = "city")
    public MobileElement city;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[contains(@text,'Adoni')]")
    public MobileElement cityValue;

    @AndroidFindBy(id = "landmark")
    public MobileElement landmark;

    @AndroidFindBy(id = "pincode")
    public MobileElement pincode;


    public cart(AppiumDriver driver){
        cartWait = new WebDriverWait(driver,15);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
