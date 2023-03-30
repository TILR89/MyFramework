package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;

public class ErrorValidations extends BaseTest {

    @Test (groups = {"errorHandling"})
    public void loginErrorValidation() throws IOException {
        homePage.loginApplication("terus23@test.com","Test!12353");
        Assert.assertEquals("Incorrect email or password.", homePage.getErrorMessage());
    }
}
