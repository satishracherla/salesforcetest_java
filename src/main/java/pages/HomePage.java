package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By loginPrompt = By.xpath("//*[contains(text(),'To access this page, you have to log in to Salesforce.')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean needsLogin() {
        return !driver.findElements(loginPrompt).isEmpty();
    }
}