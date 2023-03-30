package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.HomePage;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args){

        String productName = "ZARA COAT 3";
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        /*
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");

         */
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        driver.get("https://rahulshettyacademy.com/client");

        HomePage homePage = new HomePage(driver);

        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("terus@test.com");
        driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Test!123");
        driver.findElement(By.xpath("//input[@id='login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));

        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));

        WebElement prod = products.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
        Boolean cart = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(cart);
        System.out.println(cart);
        driver.findElement(By.xpath("//button[.='Checkout']")).click();


    }
}
