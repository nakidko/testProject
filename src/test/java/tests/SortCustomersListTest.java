package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersListPage;
import pages.MainPage;
import utils.Webdriver;

import java.util.Collections;
import java.util.List;

public class SortCustomersListTest {
    WebDriver driver = Webdriver.getChromeDriver();

    MainPage mainPage = new MainPage(driver);

    CustomersListPage customersListPage = new CustomersListPage(driver);

    @BeforeMethod
    public void setup() {
        mainPage
                .open()
                .clickCustomersButton();
    }

    @Test
    @Description("Проверка сортировки списка пользователей в обратном алфавиту порядке")
    public void reverseSortCustomersList() {
        List<String> sortedByDefaultNamesList = customersListPage.getCustomersNames();
        List<String> reversedNamesList = sortedByDefaultNamesList.stream().sorted(Collections.reverseOrder()).toList();

        customersListPage.clickSortByNameLink();

        Assert.assertEquals(reversedNamesList, customersListPage.getCustomersNames(),
                "Список пользователей отсортирован не в обратном алфавиту порядке");
    }

    @Test
    @Description("Проверка сортировки списка пользователей в алфавитном порядке")
    public void sortCustomersList() {
        List<String> sortedByDefaultNamesList = customersListPage.getCustomersNames();
        List<String> sortedNamesList = sortedByDefaultNamesList.stream().sorted().toList();

        customersListPage.clickTwiceSortByNameLink();

        Assert.assertEquals(sortedNamesList, customersListPage.getCustomersNames(),
                "Список пользователей отсортирован не по алфавиту");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
