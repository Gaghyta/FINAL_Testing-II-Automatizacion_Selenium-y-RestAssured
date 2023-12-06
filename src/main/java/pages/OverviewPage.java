package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage extends BasePage{
    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    By newAccount = By.xpath("//a[contains(text(), \"Open New Account\")]");
    By accountsOverview = By.xpath("//a[contains(text(), \"Accounts Overview\")]");
    By transferFunds = By.xpath("//a[contains(text(), \"Transfer Funds\")]");
    By disclaimerText = By.xpath("//*[@id='accountTable']//*[contains(text(), \"*Balance includes deposits that may be subject to holds\")]");
    By accountLink = By.xpath("//*[@id='accountTable']//a");
    By accountDetailsText = By.className("title");
    By activityPeriodMenu = By.id("month");
    By getActivityPeriodOption = By.xpath("//*[@id='month']//option[@value='All']");
    By transactionTypeMenu = By.id("transactionType");
    By getTransactionTypeOption = By.xpath("//*[@id='transactionType']//option[@value='All']");
    By submitButton = By.xpath("//input[@value='Go']");



    public void goToNewAccount() {
        clickOn(newAccount);
    }

    public void goToTransferFunds() {
        clickOn(transferFunds);
    }

    public void goToAccountsOverview() {
        clickOn(accountsOverview);
    }

    public String getDisclaimer() throws InterruptedException {
        return getText(disclaimerText);
    }

    public void selectAccount() {
        clickOn(accountLink);
    }
    public String getAccountDetailsText() throws InterruptedException {
        return getText(accountDetailsText);
    }

    public void selectActivityPeriod() {
        clickOn(activityPeriodMenu);
        clickOn(getActivityPeriodOption);
    }

    public void selectTransactionType() {
        clickOn(transactionTypeMenu);
        clickOn(getTransactionTypeOption);
    }

    public void submit() {
        waitTime();
        clickOn(submitButton);
    }
}
