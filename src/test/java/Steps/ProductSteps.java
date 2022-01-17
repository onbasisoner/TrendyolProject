package Steps;

import Base.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;

public class ProductSteps extends BaseStep {


    @Given("a web browser page")
    public void aWebBrowserPage() {
        geturl("https://www.trendyol.com/butik/liste/2/erkek");
    }

    /*Default login function */
    @Then("User logins")
    public void userLogins() {
        findElementClick("account-user",Pather.className);
        findElement("login-email",Pather.id,TimeOut.LOW).sendKeys("soneronbasi@grr.la");
        findElement("login-password-input",Pather.id,TimeOut.LOW).sendKeys("so123456");
        findElementClick("submit",Pather.className);
        waitElement(findElement("user-loggedin-container",Pather.className, TimeOut.LOW),TimeOut.MIDDLE);
    }


    @Then("Write brand on search area")
    public void writeBrandOnSearchArea() {
        findElement("search-box",Pather.className,TimeOut.MIDDLE).sendKeys("Oyuncu Bilgisayarı");
        findElement("search-box",Pather.className,TimeOut.LOW).sendKeys(Keys.ENTER);
    }

    @Then("Filter brand")
    public void filterBrand() {
        findElement("Monster",Pather.linkText,TimeOut.MIDDLE).click();
    }

    @Then("Enter min price")
    public void enterMinPrice() {
        PageScrolldown();
        findElement("min",Pather.className,TimeOut.LOW).sendKeys("3000");
    }

    @And("Enter max price")
    public void enterMaxPrice() {
        findElement("max",Pather.className,TimeOut.LOW).sendKeys("10000");
    }

    @Then("Filter price")
    public void filterPrice() {
        findElementClick("fltr-srch-prc-rng-srch",Pather.className);
    }

    /*There is only on product that between price limit, i check filter existence, then add to product to basket */
    @Then("Add product to basket")
    public void addProductToBasket() {
        waitElement(findElement("//span[contains(text(),'3000 TL - 10000 TL')]",Pather.xPath, TimeOut.MIDDLE),TimeOut.LOW);
        RefreshPage();
//        waitElement(findElement("add-to-basket-button",Pather.className,TimeOut.MIDDLE),TimeOut.LOW);
        findElementClick("add-to-basket-button",Pather.className);
    }

    @Then("check product on basket")
    public void checkProductOnBasket() {
        waitElement(findElement("basket-preview-container", Pather.className, TimeOut.MIDDLE),TimeOut.LOW);
        assert isExist("basket-preview-container", Pather.className, TimeOut.MIDDLE);
    }

    @Then("Close Driver")
    public void closeDriver() {
        DriverQuit();
    }


}
