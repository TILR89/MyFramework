package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    @FindBy(xpath = "//button[.='Buy Now']")
    WebElement buyNowButton;

    @FindBy(xpath = "//button[@class='btn btn-danger']")
    WebElement deleteProduct;

    @FindBy(xpath = "//button[.='Checkout']")
    WebElement checkoutButton;

    @FindBy(xpath = "(//span[@class='value'])[2]")
    WebElement totalValue;

    @FindBy(xpath = "//div[@class='cartSection']//h3")
    List<WebElement> cartProducts;

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductInCart(String productName) {
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage checkout(){
        checkoutButton.click();
        return new CheckoutPage(driver);

    }
}
