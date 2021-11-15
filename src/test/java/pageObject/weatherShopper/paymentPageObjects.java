package pageObject.weatherShopper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class paymentPageObjects {

    @FindBy(id = "email")
    public WebElement payEmail;

    @FindBy(xpath = "//div[@class='image']")
    public WebElement payImage;

    @FindBy(xpath = "//div[@class='title']//h1")
    public WebElement payHeader1;

    @FindBy(xpath = "//div[@class='title']//h2")
    public WebElement payHeader2;

    @FindBy(xpath = "//a[@class='close right']")
    public WebElement payCloseButton;

    @FindBy(id = "card_number")
    public WebElement cardNumber;

    @FindBy(id = "cc-exp")
    public WebElement cardExp;

    @FindBy(id = "cc-csc")
    public WebElement cardCVC;

    @FindBy(id = "billing-zip")
    public WebElement payZip;

    @FindBy(xpath = "//span[@class='iconTick']")
    public WebElement paySubmit;

    public paymentPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
