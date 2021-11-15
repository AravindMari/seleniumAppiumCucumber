package pageObject.sampleNativeApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class search {
    public WebDriverWait searchWait;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='cj.m.shopee:id/subtitle']//following-sibling::android.widget.TextView)[1]")
//    @iOSXCUITFindBy(xpath = "")
    public MobileElement homeHeader1;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='cj.m.shopee:id/subtitle']//following-sibling::android.widget.TextView)[2]")
    public MobileElement homeHeader2;

    @AndroidFindBy(id = "action_cart")
    public MobileElement actionCart;

    @AndroidFindBy(id = "search")
    public MobileElement searchIcon;

    @AndroidFindBy(id = "searchn")
    public MobileElement searchTextBox;

    @AndroidFindBy(id = "searchbtn")
    public MobileElement searchSubmit;

    @AndroidFindBy(xpath = "//android.widget.Button [@resource-id='cj.m.shopee:id/addtocart']")
    public MobileElement addToCart;

    @AndroidFindBy(id = "bx")
    public MobileElement selectSizeText;

    @AndroidFindBy(xpath = "(//android.widget.RadioButton[@resource-id='cj.m.shopee:id/radio'])[1]")
    public MobileElement firstSize;

    @AndroidFindBy(id = "selectsize")
    public MobileElement selectSizeConfirm;

    @AndroidFindBy(id = "checkout")
    public MobileElement checkout;


    public search(AppiumDriver driver){
        searchWait = new WebDriverWait(driver,15);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }
}
