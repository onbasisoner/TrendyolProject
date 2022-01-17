package Steps;

import Base.BaseStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.util.ArrayList;

public class FavSteps extends BaseStep {
    @Given("a web page")
    public void aWebPage() {
        geturl("https://www.trendyol.com/butik/liste/2/erkek");

    }

    @Then("User login")
    public void userLogin() {
        findElementClick("account-user",Pather.className);
        findElement("login-email",Pather.id,TimeOut.LOW).sendKeys("soneronbasi@grr.la");
        findElement("login-password-input",Pather.id,TimeOut.LOW).sendKeys("so123456");
        findElementClick("submit",Pather.className);
        waitElement(findElement("user-loggedin-container",Pather.className, TimeOut.LOW),TimeOut.MIDDLE);
    }

    @Then("Write product on search area")
    public void writeProductOnSearchArea() {
        findElement("search-box",Pather.className,TimeOut.MIDDLE).sendKeys("Gömlek");
        findElement("search-box",Pather.className,TimeOut.LOW).sendKeys(Keys.ENTER);

    }

    /*i use moveToCursor function for handling color popup, i added the first product to fav */
    @Then("Favorite product")
    public void favoriteProduct() {
        moveToCursor(findElement("color-option",Pather.className,TimeOut.MIDDLE));
        findElement("fvrt-btn", Pather.className,TimeOut.MIDDLE).click();
    }

    @Then("Click favorites")
    public void clickFavorites() {
        findElementClick("account-favorites",Pather.className);
    }

    @Then("Add fav'd product to basket")
    public void addFavDProductToBasket() {
        findElement("size-dropdown",Pather.className,TimeOut.MIDDLE).click();
        findElements("lower-dropdown-li", Pather.className,TimeOut.MIDDLE,0).click();
        findElements("basket-button",Pather.className,TimeOut.MIDDLE,0).click();
    }

    @Then("Driver Quit")
    public void driverQuit() {
        DriverQuit();
    }
}
