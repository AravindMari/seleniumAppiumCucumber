package services.sampleNativeApp;

import Hooks.hooks;
import Methods.defaultMethods;
import pageObject.sampleNativeApp.*;

import java.time.Duration;

public class searchPage {
    search searchObj = new search(hooks.driver);
    cart cartObj = new cart(hooks.driver);
    defaultMethods defMethod = new defaultMethods();

    //########################################################################################################
    // METHOD NAME        :launchPage
    // METHOD DESCRIPTION : To validate home page
    //########################################################################################################
    public void launchPage(){
        hooks.softAssert.assertThat(searchObj.homeHeader1.getText()).isEqualTo("Shop");
        hooks.softAssert.assertThat(searchObj.homeHeader2.getText()).isEqualTo("ee");
    }

    //########################################################################################################
    // METHOD NAME        :emptyCartHomePage
    // METHOD DESCRIPTION : To check whether a cart is empty, if not will make it empty
    //########################################################################################################
    public void emptyCartHomePage(){
        searchObj.actionCart.click();
        if(defMethod.is_element_available("id~empty")) {
            System.out.println("Into cart method");
            cartObj.emptyCartButton.click();
            cartObj.confirmEmptyCart.click();
        }

        else{
            System.out.println("Empty cart pop up");
            cartObj.emptyCartOkButton.click();
        }
    }

    //########################################################################################################
    // METHOD NAME        :addAddress
    // METHOD DESCRIPTION : To add/change all fields in address form
    //########################################################################################################
    public void validateHomePage(){
        hooks.driver.runAppInBackground(Duration.ofSeconds(1));
        hooks.softAssert.assertThat(searchObj.homeHeader1.getText()).isEqualTo("Shop");
        hooks.softAssert.assertThat(searchObj.homeHeader2.getText()).isEqualTo("ee");
    }

    //########################################################################################################
    // METHOD NAME        :searchProduct
    // METHOD DESCRIPTION : To search and add a product to cart
    //########################################################################################################
    public void searchProduct(){
        searchObj.searchIcon.click();
        searchObj.searchTextBox.sendKeys("bag");
        searchObj.searchSubmit.click();
        searchObj.addToCart.click();
    }

    //########################################################################################################
    // METHOD NAME        :selectSizeCheckout
    // METHOD DESCRIPTION : To select product size and checkout
    //########################################################################################################
    public void selectSizeCheckout(){
        hooks.softAssert.assertThat(searchObj.selectSizeText.getText()).isEqualTo("SELECT SIZE");
        searchObj.firstSize.click();
        searchObj.selectSizeConfirm.click();
        searchObj.checkout.click();
    }

    //########################################################################################################
    // METHOD NAME        :placeOrder
    // METHOD DESCRIPTION : To place an order
    //########################################################################################################
    public void placeOrder(){
        hooks.softAssert.assertThat(cartObj.cartHeader.getText()).isEqualTo("Cart");
        cartObj.placeOrder.click();
        cartObj.cartLogo.isDisplayed();
        cartObj.changeAddress.click();
    }

}
