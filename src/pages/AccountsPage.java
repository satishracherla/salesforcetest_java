package pages;

import org.openqa.selenium.*;

public class AccountsPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 20); // 20 seconds timeout
    }

    public void clickNew() {
        By newButton = By.xpath("//div[text()='New']");
        waitUtils.waitForElementClickable(newButton).click();
    }

    public void fillAccountForm(String name) {
        By nameField = By.xpath("//input[@name='Account Name']");
        waitUtils.waitForElementVisible(nameField).sendKeys(name);
        // ... fill other fields similarly
    }

    public void save() {
        By saveButton = By.xpath("//button[@name='Save']");
        waitUtils.waitForElementClickable(saveButton).click();
    }

    public boolean verifyAccountCreated(String name) {
        By accountName = By.xpath("//span[text()='" + name + "']");
        return waitUtils.waitForElementVisible(accountName).isDisplayed();
    }
}