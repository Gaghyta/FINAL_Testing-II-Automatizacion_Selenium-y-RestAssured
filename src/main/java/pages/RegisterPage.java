package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {

        super(driver);
    }

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

    public void submitFormRegister() {
        clickOn(registerButton);
    }

    public String getSuccessMessage() throws InterruptedException {
        return getText(confirmSuccessMessage);
    }
}
