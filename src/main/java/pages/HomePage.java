package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    By registerLink = By.xpath("//a[contains(text(), \"Register\")]");
    By usernameFormField = By.name("username");
    By passwordFormField = By.name("password");
    By loginButton = By.xpath("//*[@value='Log In']");


    public void goToHomePage() throws InterruptedException {
        url("https://parabank.parasoft.com/parabank/index.htm");
    }

    public void goToRegister() {
        clickOn(registerLink);
    }

    public void login() {
        sendKey(usernameFormField, "username1234");
        sendKey(passwordFormField, "password");
        clickOn(loginButton);
    }



}
