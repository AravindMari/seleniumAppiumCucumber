package pageObject.weatherShopper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class paySuccessPageObjects {

    @FindBy(xpath = "//div[@class='row justify-content-center']//child::h2")
    public WebElement paySucPageHeader;

    @FindBy(xpath = "//p[@class='text-justify']")
    public WebElement paySuccessContent;

    public paySuccessPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
