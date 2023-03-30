package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;
import testComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FirstTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test (dataProvider = "getData", groups = {"purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {
        ProductCataloguePage productCataloguePage = homePage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCataloguePage.getProducts();
        productCataloguePage.addProductToCart(input.get("product"));
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.verifyProductInCart(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirmMessage = confirmationPage.verifyConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test (dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(){
        ProductCataloguePage productCataloguePage = homePage.loginApplication("terus@test.com","Test!123");
        OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

    }

    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName +".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap("src/test/java/data/purchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};


        /*secondWay with HashMap
        HashMap<String, String> map = new HashMap<>();
        map.put("email","terus@test.com" );
        map.put("password","Test!123" );
        map.put("product","ZARA COAT 3" );

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("email","terus@test.com" );
        map1.put("password","Test!123" );
        map1.put("product","ADIDAS ORIGINAL" );

        return new Object[][] {{map}, {map1}};
          */

        /*
        return new Object [] [] {{"terus@test.com", "Test!123", "ZARA COAT 3"},
        {"rahul@test.com", "12345", "ADIDAS ORIGINAL"}};

        with public void submitOrder(String email, String password, String prodName)
        provide runing one test twice with two different parameters. Can substitute with hashmap
         */
    }



}
