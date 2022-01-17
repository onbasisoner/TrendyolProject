package Base;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class BaseStep {

    public WebDriver driver;
    public String browser = "chrome";

    public BaseStep(){

        /*Browser Managing there*/
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","src/test/java/Base/geckodriver");
            driver = new FirefoxDriver();

        }
        else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/test/java/Base/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();

    }
   /* Create Pather for all the selector, i didnt use most of btw */
    public enum Pather {
        id,
        className,
        name,
        xPath,
        cssSelector,
        linkText
    }
   /*For waiting i created this class, i use default number but it can be changeable */
    public enum TimeOut {
        LOW(5),
        MIDDLE(10),
        HIGH(15),
        CUSTOM_MAX(60);
        private final int value;

        public int getValue() {
            return value;
        }

        // enum constructor - cannot be public or protected
        private TimeOut(int value) {
            this.value = value;
        }

    }


    public void geturl(String url)
    {
        driver.get(url);

    }

    /*Explicit Wait function */
    public void waitElement(WebElement element, TimeOut timeOut) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut.value);
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /*For the refresh page, normally add to basket button on product, somehow it's not working sometimes */
    public void RefreshPage(){
        driver.navigate().refresh();
    }

    /*For the dropdown, i used for choosing size */
    public void selectFromDropdown(WebElement element,int index){
        try {
            Select dropdown = new Select(element);
            dropdown.selectByIndex(index);

        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    /*handle popup with that, i need to click somewhere on page */
    public void moveToCursor(WebElement element){
       try {
           Actions act = new Actions(driver);
           act.moveToElement(element).moveByOffset(100,100).click().perform();
       }catch (Exception ex){
           System.out.println(ex);
       }
    }

    /*Default click function */
    public void findElementClick(String path, Pather type) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TimeOut.MIDDLE.value);
            switch (type) {
                case className:
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(path))).click();
                    break;
                case id:
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(path))).click();
                    break;
                case name:
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(path))).click();
                    break;
                case xPath:
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path))).click();
                    break;
                case cssSelector:
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(path))).click();
                    break;
                case linkText:
                    wait.until(ExpectedConditions.elementToBeClickable(By.linkText(path))).click();
                    break;
                default:
                    new NotFoundException();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*Size of list for WebElements */
    public int sizeOfElement(String path, Pather type, TimeOut timeOut){
    try{
        WebDriverWait wait = new WebDriverWait(driver, timeOut.value);
        List<WebElement> elements = null;
        switch (type) {
            case className:
                elements = driver.findElements(By.className(path));
                break;
            default:
                new NotFoundException();
        }
        if (elements != null) {
            return elements.size();
        }
        else {
            return 0;
        }
    }catch (Exception ex){
        return 0;
    }
    }

    public boolean isExist(String path, Pather type, TimeOut timeOut){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut.value);
            List<WebElement> elements = null;
            switch (type) {
                case className:
                    elements = driver.findElements(By.className(path));
                    break;
                default:
                    new NotFoundException();
            }
            if (elements.size()>0) {
                return true;
            }
            else {
                return false;
            }
        }catch (Exception ex){
            return false;
        }

    }
    /* Default find function for web elements */
    public WebElement findElements(String path, Pather type, TimeOut timeOut, int index){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut.value);
            List<WebElement> elements = null;
            switch (type) {
                case className:
                    elements = driver.findElements(By.className(path));
                    break;
                default:
                    new NotFoundException();
            }
            return elements.get(index);
        }catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }

    /*Default function for find element, i want path, type and timeout default */
    public WebElement findElement(String path, Pather type,TimeOut timeOut) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut.value);
            WebElement element = null;
            switch (type) {
                case className:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(path)));
                    break;
                case id:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(path)));
                    break;
                case name:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(path)));
                    break;
                case xPath:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
                    break;
                case cssSelector:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));
                    break;
                case linkText:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(path)));
                    break;
                default:
                    new NotFoundException();
            }
            return element;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }


    /*Page scrolldown and scrollup for page, it's usable but i dont need to use most of the project */
    public void PageScrolldown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)", "");

    }

    public void PageScrollup() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-300)", "");
    }
    public void DriverQuit() {
        driver.quit(); }

}
