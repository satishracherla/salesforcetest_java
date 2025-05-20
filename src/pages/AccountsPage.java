package pages;
import org.openqa.selenium.*;

public class AccountsPage {
    private WebDriver driver;
    public AccountsPage(WebDriver driver) { this.driver = driver; }
    public void clickNew() { driver.findElement(By.xpath("//div[text()='New']")).click(); }
    public void fillAccountForm(String name, String phone, String website, String industry, String employees, String revenue) {
        driver.findElement(By.xpath("//input[@name='Account Name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys(phone);
        driver.findElement(By.xpath("//input[@name='Website']")).sendKeys(website);
        driver.findElement(By.xpath("//input[@name='Industry']")).sendKeys(industry);
        driver.findElement(By.xpath("//input[@name='Employees']")).sendKeys(employees);
        driver.findElement(By.xpath("//input[@name='Annual Revenue']")).sendKeys(revenue);
    }
    public void save() { driver.findElement(By.xpath("//button[@name='Save']")).click(); }
    public boolean verifyAccountCreated(String name) {
        return driver.getPageSource().contains(name);
    }
}