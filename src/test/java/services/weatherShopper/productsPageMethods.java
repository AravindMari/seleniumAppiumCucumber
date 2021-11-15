package services.weatherShopper;

import Hooks.hooks;
import Methods.defaultMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObject.weatherShopper.productsPageObjects;
import pageObject.weatherShopper.cartPageObjects;
import java.util.*;
import static Hooks.hooks.*;
import static java.lang.Integer.sum;
import static services.weatherShopper.homePageMethods.*;

public class productsPageMethods {
    productsPageObjects prodPageObj = new productsPageObjects(defaultMethods.getTLDriver());
    cartPageObjects cartPageObj = new cartPageObjects(defaultMethods.getTLDriver());
    public static ThreadLocal<Integer> totalPrice = new ThreadLocal<>();
    public int leastProd;
    public Map<String,String> addedProdNamePrice = new LinkedHashMap<>();

    //########################################################################################################
    // METHOD NAME        : addToCart
    // METHOD DESCRIPTION : To add products to the cart based on requirement
    //########################################################################################################
    public synchronized void addToCart() {

        prodPageObj.goToCartButton.isDisplayed();
        hooks.softAssert.assertThat(prodPageObj.goToCartButton.getText()).contains("Cart - Empty");
        if((productSelected.get()).contains("Moisturizers")){
            hooks.softAssert.assertThat(prodPageObj.prodPageHeader.getText()).isEqualTo(getWebProp().getProperty("prodType1Header"));

            leastProd = algToSelectProduct("Aloe");
            defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::button)["+leastProd+"]")).click();
            addedProdNamePrice.put(defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[1])["+leastProd+"]")).getText(),defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[2])["+leastProd+"]")).getText());

            leastProd = algToSelectProduct("Almond");
            defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::button)["+leastProd+"]")).click();
            addedProdNamePrice.put(defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[1])["+leastProd+"]")).getText(),defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[2])["+leastProd+"]")).getText());

        }
        else{
            hooks.softAssert.assertThat(prodPageObj.prodPageHeader.getText()).isEqualTo(getWebProp().getProperty("prodType2Header"));
            leastProd = algToSelectProduct("SPF-50");
            defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::button)["+leastProd+"]")).click();
            addedProdNamePrice.put(defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[1])["+leastProd+"]")).getText(),defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[2])["+leastProd+"]")).getText());


            leastProd = algToSelectProduct("SPF-30");
            defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::button)["+leastProd+"]")).click();
            addedProdNamePrice.put(defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[1])["+leastProd+"]")).getText(),defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[2])["+leastProd+"]")).getText());

        }
        System.out.println("Map of all added product:"+ Collections.singletonList(addedProdNamePrice));
        Assert.assertEquals("Cart - 2 item(s)",prodPageObj.goToCartButton.getText());
        prodPageObj.goToCartButton.click();
    }

    //########################################################################################################
    // METHOD NAME        : algToSelectProduct
    // METHOD DESCRIPTION : Derived algorithm logic to select product based on requirement
    //########################################################################################################
    public synchronized Integer algToSelectProduct(String productType) {
        int leastIndex = 0;
        Map<Integer,Integer> reqProdPrice = new HashMap<>();
        for(WebElement ele: prodPageObj.allProductsName) {
            if((ele.getText().toLowerCase()).contains(productType.toLowerCase())) {
                String temp = defaultMethods.getTLDriver().findElement(By.xpath("(//div[@class='text-center col-4']//child::p[2])["+ sum(prodPageObj.allProductsName.indexOf(ele),1)+"]")).getText();
                String[] tempArr = temp.split("\\s+");
                int prodPrice;
                try{
                    prodPrice = Integer.parseInt(tempArr[2]);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    prodPrice = Integer.parseInt(tempArr[1]);
                }
                reqProdPrice.put(sum(prodPageObj.allProductsName.indexOf(ele),1),prodPrice);
            }
        }
        if(reqProdPrice.size()<1){
            Assert.fail("Requested "+productType+" is not available in the list. So, execution stopped");
        }
        System.out.println("Map of "+productType+" product:"+ Collections.singletonList(reqProdPrice));
        System.out.println("Least Expensive "+productType+" product rate:"+Collections.min(reqProdPrice.values()));
        for (Map.Entry<Integer, Integer> entry : reqProdPrice.entrySet()) {
            if (entry.getValue().equals(Collections.min(reqProdPrice.values()))) {
                leastIndex=entry.getKey();
            }
        }

        return leastIndex;
    }

    //########################################################################################################
    // METHOD NAME        : validateCart
    // METHOD DESCRIPTION : To validate that the cart looks correct with added products
    //########################################################################################################
    public synchronized void validateCart() {
        hooks.softAssert.assertThat(cartPageObj.cartPageHeader.getText()).isEqualTo(getWebProp().getProperty("cartPageHeader"));
        hooks.softAssert.assertThat(cartPageObj.cartTableHead1.getText()).isEqualTo(getWebProp().getProperty("cartTableHead1"));
        hooks.softAssert.assertThat(cartPageObj.cartTableHead2.getText()).isEqualTo(getWebProp().getProperty("cartTableHead2"));
        hooks.softAssert.assertThat(cartPageObj.payButton.getText()).isEqualTo(getWebProp().getProperty("payButton"));
        hooks.softAssert.assertThat(cartPageObj.totalPrice.getText()).contains(getWebProp().getProperty("totalPrice"));
        String[] totPriceArr = (cartPageObj.totalPrice.getText()).split("\\s+");

        int i=0;
        totalPrice.set(0);
        for (Map.Entry<String, String> entry : addedProdNamePrice.entrySet()) {
            i++;
            String[] tempArr = (entry.getValue()).split("\\s+");
            int prodPrice;
            try{
                prodPrice = Integer.parseInt(tempArr[2]);
            }
            catch (ArrayIndexOutOfBoundsException e){
                prodPrice = Integer.parseInt(tempArr[1]);
            }

            hooks.softAssert.assertThat(defaultMethods.getTLDriver().findElement(By.xpath("//table[@class='table table-striped']//tbody//tr["+i+"]//td[1]")).getText()).isEqualTo(entry.getKey());
            hooks.softAssert.assertThat(Integer.parseInt(defaultMethods.getTLDriver().findElement(By.xpath("//table[@class='table table-striped']//tbody//tr["+i+"]//td[2]")).getText())).isEqualTo(prodPrice);
            totalPrice.set(totalPrice.get()+Integer.parseInt(defaultMethods.getTLDriver().findElement(By.xpath("//table[@class='table table-striped']//tbody//tr["+i+"]//td[2]")).getText()));
        }
        hooks.softAssert.assertThat(Integer.parseInt(totPriceArr[2])).isEqualTo(totalPrice.get());
        getWebScreenshot();
        logToReport("Product added to Cart Successfully-Total Price:"+totalPrice.get());
        cartPageObj.payButton.click();
    }
}
