package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractComponent {

    WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//input[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//input[@id='userPassword']")
    WebElement passwordField;

    @FindBy (xpath = "//input[@id='login']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@role='alertdialog']")
        WebElement errorMessage;

    public void  openWebPage(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCataloguePage loginApplication(String email, String password){
        userEmail.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
         return productCataloguePage;
    }
    public String getErrorMessage(){
        waitWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }




























}
