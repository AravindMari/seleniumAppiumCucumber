package stepDefinition.sampleNativeApp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.sampleNativeApp.*;

public class shopeeStepDefinition {
	searchPage searchPageObj = new searchPage();
	cartPage cartPageObj= new cartPage();

	@Given("User launch the Shopee app")
	public void user_launch_the_Shopee_app() throws InterruptedException {
		System.out.println("Welcome to Shopee App");
		searchPageObj.launchPage();
	}

	@When("user add a product to cart")
	public void add_product() throws InterruptedException {
		searchPageObj.emptyCartHomePage();
		searchPageObj.validateHomePage();
		searchPageObj.searchProduct();
		searchPageObj.selectSizeCheckout();
		searchPageObj.placeOrder();

	}

	@And("tries to change address before payment")
	public void edit_address() throws InterruptedException {
		cartPageObj.addAddress();

	}

	@Then("user is able to save his new address")
	public void save_address() throws InterruptedException {
		cartPageObj.saveAddress();
	}

	@And("^enter mobile number during change address \"(.*?)\"$")
	public void enter_mobile_number(String mobileNum) throws InterruptedException {
		cartPageObj.enterMobileNum(mobileNum);
	}

	@Then("user should be able to only enter digit")
	public void validate_mobile_number_field() throws InterruptedException {
		cartPageObj.validateMobileNumField();
	}

}
