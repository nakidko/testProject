package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {
    private WebDriver driver;

    private final String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    WebElement addCustomerButton;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    WebElement customersButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get(URL);
        return this;
    }

    public MainPage clickAddCustomerButton() {
        addCustomerButton.click();
        return this;
    }

    public MainPage clickCustomersButton() {
        customersButton.click();
        return this;
    }
}
