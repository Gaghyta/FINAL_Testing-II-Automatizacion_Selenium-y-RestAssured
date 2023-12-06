package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage{


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    By accountTypeMenu = By.id("type");
    By accountTypeSavings = By.xpath("//*[@id='type']//option[@value='1']");
    By openAccountButton = By.xpath("//*[@value='Open New Account']");
    By successMessage = By.xpath("//*[@id='rightPanel']//p[contains(text(),'Congratulations, your account is now open.')]");

    public void selectSavings() {
        clickOn(accountTypeMenu);
        clickOn(accountTypeSavings);
    }

    public void submitNewAccount() {
        waitTime();
        clickOn(openAccountButton);
    }

    public String getSuccessMessage() throws InterruptedException {
        return getText(successMessage);
    }


}
