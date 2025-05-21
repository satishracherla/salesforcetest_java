package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.*;
import utils.TestUtils;
import utils.WaitUtils;
import utils.ConfigLoader;


public class SalesforceTest {
    private static final Duration TIMEOUT = Duration.ofSeconds(30);
    private static final Duration POLLING_INTERVAL = Duration.ofSeconds(2);
    private WebDriver driver;
    private WebDriverWait wait;
    // Page objects
    private HomePage homePage;
    private AccountsPage accountsPage;
    private ContactsPage contactsPage;
    private ReportsPage reportsPage;

    @BeforeClass
    public void setup() {
        initializeDriver();
        initializePageObjects();
        loginToSalesforce();
    }

    private void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    private void initializePageObjects() {
        homePage = new HomePage(driver);
        accountsPage = new AccountsPage(driver);
        contactsPage = new ContactsPage(driver);
        reportsPage = new ReportsPage(driver);
    }

    private void loginToSalesforce() {
        String username = ConfigLoader.getProperty("salesforce.username");
        String password = ConfigLoader.getProperty("salesforce.password");
        String loginUrl = ConfigLoader.getProperty("salesforce.url");

        driver.get(loginUrl);

        new LoginPage(driver).login(username, password);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void createAccountContactAndExportReport() {
        String accountName = TestUtils.generateUniqueAccountName();
        String contactName = TestUtils.generateUniqueContactName();

        // Create Account
        createAccount(accountName);
        verifyAccountCreation(accountName);

        // Create Contact
        createContact(contactName, accountName);
        verifyContactCreation(contactName);

        // Export Report
        exportAccountReport();
    }

    private void createAccount(String accountName) {
        homePage.goToSales()
                .navigateToAccounts();

        accountsPage.clickNew();
        WaitUtils.waitForElementVisible(driver, accountsPage.getAccountFormLocator());

        accountsPage.fillAccountForm(AccountDetails.builder()
                .name(accountName)
                .phone("+1-415-555-1234")
                .website("www.copadotesting.com")
                .industry("Technology")
                .employees("500")
                .revenue("1000000")
                .build());

        accountsPage.save();
    }

    private void verifyAccountCreation(String accountName) {
        WaitUtils.waitForElementVisible(driver, accountsPage.getAccountListLocator());
        Assert.assertTrue(accountsPage.verifyAccountCreated(accountName),
                "Account creation verification failed");
    }

    private void createContact(String contactName, String accountName) {
        homePage.navigateToContacts();

        contactsPage.clickNew();
        WaitUtils.waitForElementVisible(driver, contactsPage.getContactFormLocator());

        contactsPage.fillContactForm(ContactDetails.builder()
                .firstName(contactName)
                .lastName("Smith")
                .email(contactName + ".smith@copadotesting.com")
                .phone("+1-415-555-5678")
                .title("QA Engineer")
                .accountName(accountName)
                .build());

        contactsPage.save();
    }

    private void exportAccountReport() {
        try {
            reportsPage.navigateToReports()
                    .createNewReport()
                    .searchReportType("Accounts")
                    .selectReportType("Accounts")
                    .startReport()
                    .saveAndRun()
                    .exportReport("Comma Delimited .csv");
        } catch (Exception e) {
            throw new TestException("Failed to export report", e);
        }
    }
}
