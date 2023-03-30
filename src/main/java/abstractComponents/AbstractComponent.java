package abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CartPage;
import pageObjects.OrdersPage;

import java.time.Duration;

public class AbstractComponent {

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartIcon;

    @FindBy(xpath = "//button[[@routerlink='/dashboard/myorders']]")
    WebElement ordersButton;

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;

    }

    public void waitElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitElementToDisappear(WebElement element){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCartPage(){
        cartIcon.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;

    }
    public OrdersPage goToOrdersPage(){
        ordersButton.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;

    }
}
