package stepDefinition.weatherShopper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import services.weatherShopper.paymentPageMethods;

public class paymentScreen {
	paymentPageMethods payPageObj = new paymentPageMethods();

	@And("made a payment")
	public void user_add_products() {
		payPageObj.makePayment();
	}

	@Then("user should be able to order products successfully")
	public void user_validate_payment_success() {
		payPageObj.validatePaySuccess();
	}
}
