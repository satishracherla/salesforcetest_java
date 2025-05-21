package pages;
import org.openqa.selenium.*;

public class ContactsPage {
    private WebDriver driver;
    public ContactsPage(WebDriver driver) { this.driver = driver; }
    public void clickNew() { driver.findElement(By.xpath("//div[text()='New']")).click(); }
    public void fillContactForm(String firstName, String lastName, String email, String phone, String title, String accountName) {
        driver.findElement(By.xpath("//input[@name='First Name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='Last Name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys(phone);
        driver.findElement(By.xpath("//input[@name='Title']")).sendKeys(title);
        // Account linking may require a search and select
        driver.findElement(By.xpath("//input[contains(@placeholder,'Search Accounts')]")).sendKeys(accountName);
        // Add wait and select logic as needed
    }
    public void save() { driver.findElement(By.xpath("//button[@name='Save']")).click(); }
    public boolean verifyContactCreated(String name) {
        return driver.getPageSource().contains(name);
    }
}