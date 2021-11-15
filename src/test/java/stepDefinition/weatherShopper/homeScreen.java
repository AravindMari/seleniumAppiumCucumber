package stepDefinition.weatherShopper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import services.weatherShopper.homePageMethods;

public class homeScreen {
	homePageMethods homePageObj = new homePageMethods();

	@Given("user is on Weather Shopper application Home screen")
	public synchronized void user_is_on_Home_screen() {
		System.out.println("Welcome to Weather Shopper Web Application");
		homePageObj.valHomePage();
	}

	@When("user choose a product type based on current weather")
	public synchronized void user_choose_product_type() {
		homePageObj.chooseProductType();
	}
}
