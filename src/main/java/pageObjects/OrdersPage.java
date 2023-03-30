package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {

    @FindBy(xpath ="//div//tr//td[2]")
    List<WebElement> productNames;

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay (String productName){
        Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }
}
