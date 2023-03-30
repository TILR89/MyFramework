package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    @FindBy(xpath = "//a[.='Place Order ']")
    WebElement placeOrderButton;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountryField;

    @FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountryOption;

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(selectCountryField, countryName).build().perform();
        waitElementToAppear(By.cssSelector(".ta-results"));
        selectCountryOption.click();

    }

    public ConfirmationPage placeOrder() {
        placeOrderButton.click();
        return new ConfirmationPage(driver);
    }

























}
