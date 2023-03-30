package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomersListPage;
import pages.MainPage;
import utils.Webdriver;

import java.util.List;

public class FindCustomerTest {
    WebDriver driver = Webdriver.getChromeDriver();

    Faker faker = new Faker();

    MainPage mainPage = new MainPage(driver);

    AddCustomerPage addCustomerPage = new AddCustomerPage(driver);

    CustomersListPage customersListPage = new CustomersListPage(driver);

    private String firstName;
    private String lastName;
    private String postCode;

    @BeforeMethod
    public void setup() {
        this.firstName = faker.name().firstName() + "test";
        this.lastName = faker.name().lastName();
        this.postCode = faker.address().zipCode();

        mainPage
                .open()
                .clickAddCustomerButton();

        addCustomerPage
                .fillFirstNameField(this.firstName)
                .fillLastNameField(this.lastName)
                .fillPostCodeField(this.postCode)
                .clickAddCustomerButton();
        driver.switchTo().alert().accept();

        mainPage.clickCustomersButton();
    }

    @Test()
    public void searchCustomerByFirstName() {

        List<String> customersInfoList = customersListPage
                .addTextToSearchField(this.firstName)
                .getCustomersInfoRows();

        Assert.assertTrue(
                customersInfoList.get(0)
                        .contains(this.firstName + " " + this.lastName + " " + this.postCode)
        );
    }

    @Test()
    public void searchCustomerByLastName() {

        List<String> customersInfoList = customersListPage
                .addTextToSearchField(this.lastName)
                .getCustomersInfoRows();

        Assert.assertTrue(
                customersInfoList.get(0)
                        .contains(this.firstName + " " + this.lastName + " " + this.postCode)
        );
    }

    @Test()
    public void searchCustomerByFullName() {

        List<String> customersInfoList = customersListPage
                .addTextToSearchField(this.postCode)
                .getCustomersInfoRows();

        Assert.assertTrue(
                customersInfoList.get(0)
                        .contains(this.firstName + " " + this.lastName + " " + this.postCode)
        );
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
