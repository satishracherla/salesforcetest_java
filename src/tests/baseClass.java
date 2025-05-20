package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.HomePage;
import utils.TestUtils;

import java.time.Duration;

public class baseClass {
    private WebDriver driver;
    private final String username = "satish.r177@agentforce.com";
    private final String password = "Agentforce@2025";
    private final String loginUrl = "https://login.salesforce.com";
    private final String homeUrl = loginUrl + "/lightning/page/home";

    @BeforeClass
    public void setup() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("about:blank");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginAndHome() {
        driver.get(homeUrl);
        HomePage homePage = new HomePage(driver);
        if (homePage.needsLogin()) {
            driver.get(loginUrl);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
        }
        // Generate unique names
        String accountName = TestUtils.generateUniqueAccountName();
        String contactName = TestUtils.generateUniqueContactName();
        String leadName = TestUtils.generateUniqueLeadName();

        // Add your test steps here, e.g., create account, contact, lead, etc.
        System.out.println(accountName + ", " + contactName + ", " + leadName);
    }
}