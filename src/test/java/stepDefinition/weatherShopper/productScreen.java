package stepDefinition.weatherShopper;

import io.cucumber.java.en.And;
import services.weatherShopper.productsPageMethods;

public class productScreen {
	productsPageMethods prodPageObj = new productsPageMethods();

	@And("add products to the cart based on requirement")
	public void user_add_products()  {
		prodPageObj.addToCart();
		prodPageObj.validateCart();
	}
}
