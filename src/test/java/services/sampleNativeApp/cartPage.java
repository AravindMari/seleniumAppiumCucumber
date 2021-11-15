package services.sampleNativeApp;

import Hooks.hooks;
import org.junit.Assert;

import java.util.regex.Pattern;
import pageObject.sampleNativeApp.*;


public class cartPage {
    cart cartObj = new cart(hooks.driver);

    //########################################################################################################
    // METHOD NAME        :addAddress
    // METHOD DESCRIPTION : To add/change all fields in address form
    //########################################################################################################
    public void addAddress(){
        hooks.softAssert.assertThat(cartObj.addressSave.getText()).isEqualTo("Save");
        cartObj.customerName.clear();
        cartObj.customerName.sendKeys("Aravind");
        cartObj.mobileNumber.clear();
        cartObj.mobileNumber.sendKeys("7639296555");
        cartObj.address.clear();
        cartObj.address.sendKeys("92");

        cartObj.state.click();
        cartObj.stateValue.click();
        cartObj.city.click();
        cartObj.cityValue.click();

        cartObj.landmark.clear();
        cartObj.landmark.sendKeys("Aldi");
        cartObj.pincode.clear();
        cartObj.pincode.sendKeys("628004");
    }

    //########################################################################################################
    // METHOD NAME        :saveAddress
    // METHOD DESCRIPTION : To save the address form
    //########################################################################################################
    public void saveAddress(){
        cartObj.addressSave.click();
        cartObj.cartLogo.isDisplayed();
    }

    //########################################################################################################
    // METHOD NAME        :enterMobileNum
    // METHOD DESCRIPTION : To add runtime mobile number fields in address form
    //########################################################################################################
    public void enterMobileNum(String mobileNum){
        hooks.softAssert.assertThat(cartObj.addressSave.getText()).isEqualTo("Save");
        cartObj.customerName.clear();
        cartObj.customerName.sendKeys("Aravind");
        cartObj.mobileNumber.clear();
        cartObj.mobileNumber.sendKeys(mobileNum);
    }

    //########################################################################################################
    // METHOD NAME        :validateMobileNumField
    // METHOD DESCRIPTION : To validate whether only digit are allowed in mobile number field
    //########################################################################################################
    public void validateMobileNumField(){
        String mobileNum = cartObj.mobileNumber.getText();
        if((mobileNum!=null) || !(mobileNum.isEmpty())) {
            String mobileNumber = mobileNum.replace("\"","");
            Pattern onlyDigit = Pattern.compile(".*\\D.*");
            boolean onlyDig = onlyDigit.matcher(mobileNumber).matches();

            if (onlyDig == false) {
                System.out.println("Mobile number field allow only digits value:" + mobileNumber);
            } else {
                Assert.fail("Mobile number field allows non digits value :" + mobileNumber);
            }

        }
        else{
            System.out.println("Mobile number field is null or empty:" + mobileNum);

        }
    }
}
