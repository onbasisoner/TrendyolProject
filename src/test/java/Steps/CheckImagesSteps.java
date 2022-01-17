package Steps;

import Base.BaseStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CheckImagesSteps extends BaseStep {
    @Given("web page")
    public void webPage() {
        geturl("https://www.trendyol.com/butik/liste/2/erkek");
    }

    /* I created steps for first tab detailed */
    @Then("Click first tab")
    public void clickFirstTab() {
        findElements("category-header",Pather.className,TimeOut.MIDDLE,0).click();
    }

    @Then("Click first component")
    public void clickFirstComponent() {
        findElements("component-item",Pather.className,TimeOut.MIDDLE,0).click();
    }

    @Then("Check Product Images")
    public void checkProductImages() {
        moveToCursor(findElement("color-option",Pather.className,TimeOut.MIDDLE));
        for(int i=0; i<4;i++){
            boolean checkImage = findElements("p-card-img",Pather.className,TimeOut.MIDDLE,i).isDisplayed();
            if (!checkImage) throw new AssertionError();
            i=i+1;
        }
    }

    /*For other tabs, i created this steps, it repeats itself until all tabs done */
    @Then("Control other tabs")
    public void controlOtherTabs() {

        int size = sizeOfElement("category-header", Pather.className, TimeOut.MIDDLE);
        for (int i=1;i<size-1;i++){
            findElements("category-header",Pather.className,TimeOut.MIDDLE,i).click();
            findElements("component-item",Pather.className,TimeOut.MIDDLE,0).click();
            for (int j=0;j<4;j++){
                boolean checkImage = findElements("p-card-img",Pather.className,TimeOut.MIDDLE,j).isDisplayed();
                if (!checkImage) throw new AssertionError();
                j=j+1;
            }
            i=i+1;
        }
    }

    @Then("Quit Driver")
    public void quitDriver() {
        DriverQuit();
    }


}
