package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddCustomerPage;
import pages.MainPage;
import utils.Webdriver;

public class AddCustomerTest {
    WebDriver driver = Webdriver.getChromeDriver();

    Faker faker = new Faker();

    MainPage mainPage = new MainPage(driver);

    AddCustomerPage addCustomerPage = new AddCustomerPage(driver);

    private final String EXPECTED_SUCCESS_ALERT_MESSAGE = "Customer added successfully with customer id :";

    @BeforeMethod
    public void setup() {
        mainPage
                .open()
                .clickAddCustomerButton();
    }

    @Test
    @Description("Проверка успешной регистрации нового пользователя")
    public void addNewCustomer() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String postCode = faker.address().zipCode();

        addCustomerPage
                .fillFirstNameField(firstName)
                .fillLastNameField(lastName)
                .fillPostCodeField(postCode)
                .clickAddCustomerButton();

        String alertMessage = addCustomerPage.getSuccessAlertText();

        Assert.assertTrue(isAlertPresent(), "Алерт не отображается на странице");

        Assert.assertTrue(alertMessage.contains(EXPECTED_SUCCESS_ALERT_MESSAGE),
                "Не отображается/отображается некорректное сообщение в алерте");
    }

    @DataProvider(name = "nonValidValuesDataProvider")
    private Object[][] nonValidValuesDataProvider() {
        return new Object[][] {
                {"", "", ""},
                {"", faker.name().firstName(), faker.address().zipCode()},
                {faker.name().firstName(), "", faker.address().zipCode()},
                {faker.name().firstName(), faker.name().lastName(), ""}
        };
    }

    @Test(dataProvider = "nonValidValuesDataProvider")
    @Description("Проверка добавления нового пользователя без заполнения обязательных полей формы")
    public void addNewCustomerWithInvalidData(String firstName, String lastName, String postCode) {
        addCustomerPage
                .fillFirstNameField(firstName)
                .fillLastNameField(lastName)
                .fillPostCodeField(postCode)
                .clickAddCustomerButton();

        Assert.assertFalse(isAlertPresent(), "Алерт отображается на странице");
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    @AfterMethod
    public void closeAlert() {
        if (isAlertPresent()) driver.switchTo().alert().accept();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
