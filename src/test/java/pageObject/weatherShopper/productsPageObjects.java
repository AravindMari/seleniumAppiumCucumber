package pageObject.weatherShopper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class productsPageObjects {

    @FindBy(xpath = "//div[@class='row justify-content-center']//child::h2")
    public WebElement prodPageHeader;

    @FindBy(xpath = "//button[@onclick='goToCart()']")
    public WebElement goToCartButton;

    @FindBy(xpath = "//div[@class='text-center col-4']//child::p[1]")
    public List<WebElement> allProductsName;

    public productsPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
