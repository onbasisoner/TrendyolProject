package Steps;

import Base.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginSteps extends BaseStep {


    @Given("a web browser is at the homepage")
    public void aWebBrowserIsAtTheHomepage() {
        geturl("https://www.trendyol.com");
    }

    /*Handle gender choice popup, doesnt matter selection so i select first one */
    @And("User clicks gender choice")
    public void userClicksGenderChoice() {
        findElementClick("homepage-popup-gender",Pather.className);
    }

    @Then("User clicks login button on homepage")
    public void userClicksLoginButtonOnHomepage() {
        findElementClick("account-user",Pather.className);
    }


    @Then("User fill username info")
    public void userFillUsernameInfo() {
        findElement("login-email",Pather.id,TimeOut.LOW).sendKeys("soneronbasi@grr.la");
    }

    @And("User fill password info")
    public void userFillPasswordInfo() {
        findElement("login-password-input",Pather.id,TimeOut.LOW).sendKeys("so123456");
    }

    @Then("User clicks login button on login page")
    public void userClicksLoginButtonOnLoginPage() {
        findElementClick("submit",Pather.className);
    }

    @And("User login successfully")
    public void userLoginSuccessfully() {
        waitElement(findElement("user-name",Pather.className,TimeOut.LOW),TimeOut.MIDDLE);
        assert isExist("user-name",Pather.className, TimeOut.MIDDLE);
    }

    @Then("Driver close")
    public void driverClose() {
        DriverQuit();
    }
}
