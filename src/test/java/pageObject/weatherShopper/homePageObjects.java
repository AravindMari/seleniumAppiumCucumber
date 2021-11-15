package pageObject.weatherShopper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePageObjects {

    @FindBy(xpath = "//div[@class='row justify-content-center']//child::h2")
    public WebElement pageHeader;

    @FindBy(xpath = "//span[@id='temperature']")
    public WebElement temperature;

    @FindBy(xpath = "//div[@class='text-center col-4']//child::h3")
    public WebElement prodType1Header;

    @FindBy(xpath = "//h3[text()='Moisturizers']//parent::div//child::p")
    public WebElement prodType1Content;

    @FindBy(xpath = "//h3[text()='Moisturizers']//parent::div//child::a//child::button")
    public WebElement prodType1SelectorButton;

    @FindBy(xpath = "//div[@class='text-center col-4 offset-4']//child::h3")
    public WebElement prodType2Header;

    @FindBy(xpath = "//h3[text()='Sunscreens']//parent::div//child::p")
    public WebElement prodType2Content;

    @FindBy(xpath = "//h3[text()='Sunscreens']//parent::div//child::a//child::button")
    public WebElement prodType2SelectorButton;

    public homePageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
