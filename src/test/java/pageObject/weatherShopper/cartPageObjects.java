package pageObject.weatherShopper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPageObjects {

    @FindBy(xpath = "//div[@class='row justify-content-center']/child::h2")
    public WebElement cartPageHeader;

    @FindBy(xpath = "//button[@type='submit']//child::span")
    public WebElement payButton;

    @FindBy(xpath = "//table[@class='table table-striped']//th[1]")
    public WebElement cartTableHead1;

    @FindBy(xpath = "//table[@class='table table-striped']//th[2]")
    public WebElement cartTableHead2;

    @FindBy(xpath = "//p[@id='total']")
    public WebElement totalPrice;

    public cartPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
