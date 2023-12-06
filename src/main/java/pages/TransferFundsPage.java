package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferFundsPage extends BasePage{

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    By ammount = By.id("amount");
    By transferFundsText = By.className("title");
    By fromAccountMenu = By.id("fromAccountId");
    By fromAccountOption = By.xpath("//*[@id='fromAccountId']//option");
    By toAccountMenu = By.id("toAccountId");
    By toAccountOption = By.xpath("(//*[@id='fromAccountId']//option)[2]");
    By transferButton = By.xpath("//*[@value='Transfer']");

    public String getTransferFundsText() throws InterruptedException {
        return getText(transferFundsText);
    }

    public void fillAmmount() {
        sendKey(ammount, "100");
    }

    public void selectFromAccount() {
        clickOn(fromAccountMenu);
        clickOn(fromAccountOption);
    }

    public void selectToAccount() {
        clickOn(toAccountMenu);
        clickOn(toAccountOption);
    }

    public void submitTransfer() {
        waitTime();
        clickOn(transferButton);
    }

    public String getTransferSuccessMessage() throws InterruptedException {
        waitTime();
        return getText(transferFundsText);
    }

    public void validateMenu() {
        int retries = 0;
        boolean success = false;
        while (retries < 5 && !success) {
            try {
                clickOn(toAccountOption);
                success = true;
            } catch(Exception e) {
                refresh();
            }
        }
    }

}
