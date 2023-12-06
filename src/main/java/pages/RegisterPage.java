package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {


    // LOCALIZADORES

    By firstName = By.id("customer.firstName");
    By lastName = By.id("customer.lastName");
    By addressStreet = By.id("customer.address.street");
    By city = By.id("customer.address.city");
    By state = By.id("customer.address.state");
    By zipCode = By.id("customer.address.zipCode");
    By phone = By.id("customer.phoneNumber");
    By ssn = By.id("customer.ssn");
    By userName = By.id("customer.username");
    By password = By.id("customer.password");
    By passwordConfirm = By.id("repeatedPassword");
    By registerButton = By.xpath("//*[@value='Register']");
    By confirmSuccessMessage = By.xpath("//*[@id='rightPanel']//p");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // METODO PARA RELLENAR DATOS

    public void fillRegisterFormUser() {
        sendKey(firstName, "Carla");
        sendKey(lastName, "Antonini");
        sendKey(addressStreet, "9");
        sendKey(city, "Berazategui");
        sendKey(state, "Argentina");
        sendKey(zipCode, "1884");
        sendKey(phone, "1162224444");
        sendKey(ssn,"1234565768787");
        sendKey(userName, "Username" + (int)(Math.random()*100000));
        sendKey(password, "password");
        sendKey(passwordConfirm, "password");
    }

    public void fillRegisterFormMainUser() {
        sendKey(firstName, "Francesca");
        sendKey(lastName, "Pagliano");
        sendKey(addressStreet, "Tanaro 13");
        sendKey(city, "Asti");
        sendKey(state, "Asti");
        sendKey(zipCode, "1406");
        sendKey(phone, "1162228888");
        sendKey(ssn,"456789");
        sendKey(userName, "username123456");
        sendKey(password, "password");
        sendKey(passwordConfirm, "password");
    }


    public void submitFormRegister() {
        clickOn(registerButton);
    }

    public String getSuccessMessage() throws InterruptedException {
        return getText(confirmSuccessMessage);
    }
}
