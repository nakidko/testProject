package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomersListPage{
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    WebElement firstNameLink;

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    WebElement searchField;

    private By customersNamesLocator = By.xpath("//tr[@class='ng-scope']/td[1]");

    private By customersRows = By.xpath("//tr[@class='ng-scope']");

    public CustomersListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CustomersListPage clickSortByNameLink() {
        firstNameLink.click();

        return this;
    }

    public CustomersListPage clickTwiceSortByNameLink() {
        firstNameLink.click();
        firstNameLink.click();

        return this;
    }

    public List<String> getCustomersNames() {
        List<String> customersNames = new ArrayList<>();
        for (WebElement name : driver.findElements(customersNamesLocator)) {
            customersNames.add(name.getText());
        }

        return customersNames;
    }

    public CustomersListPage addTextToSearchField(String text) {
        searchField.sendKeys(text);

        return this;
    }

    public List<String> getCustomersInfoRows() {
        List<String> customerData = new ArrayList<>();
        for (WebElement element : driver.findElements(customersRows)) {
            customerData.add(element.getText());
        }

        return customerData;
    }
}
