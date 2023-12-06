package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {

    private By myAccount = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a");
    protected By registerMenu = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a");

    public static WebDriver driver;
    public static WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }

    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver.manage().window().maximize();
    }

    public WebElement webElement(By by) {
        return driver.findElement(by);
    }
    protected WebElement elementFind(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void clickOn(By locator) {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        driver.findElement(locator).click();
    }

    public void url(String url) throws InterruptedException  {
        driver.get(url);
    }

    protected void sendText(String inputText, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.elementFind(locator).clear();
        this.elementFind(locator).sendKeys(inputText);
    }

    protected String getText(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        return this.elementFind(locator).getText();
    }

    public void clickMyAccount() throws InterruptedException {
        this.clickOn(myAccount);
    }

    public void clickRegister() throws InterruptedException {
        this.clickOn(registerMenu);
    }

    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        this.elementFind(locator).sendKeys(key);
    }

    public void waitTime() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void close() {
        driver.quit();
    }


}
