package pages;

import org.openqa.selenium.*;

public class LeadsPage {
    private WebDriver driver;

    public LeadsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNew() {
        driver.findElement(By.xpath("//div[text()='New']")).click();
    }

    public void fillLeadForm(String firstName, String lastName, String company, String phone, String email, String status) {
        driver.findElement(By.xpath("//input[@name='First Name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='Last Name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name='Company']")).sendKeys(company);
        driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys(phone);
        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='Lead Status']")).sendKeys(status);
    }

    public void save() {
        driver.findElement(By.xpath("//button[@name='Save']")).click();
    }

    public void convertLead() {
        driver.findElement(By.xpath("//button[contains(text(),'Show more actions')]")).click();
        driver.findElement(By.xpath("//span[text()='Convert']")).click();
        // Add waits and modal handling as needed
        driver.findElement(By.xpath("//button[text()='Convert']")).click();
    }

    public boolean verifyLeadConverted() {
        return driver.getPageSource().contains("Your lead has been converted");
    }
}