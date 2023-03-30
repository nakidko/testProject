package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCodeField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCustomerButton;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddCustomerPage fillFirstNameField(String text) {
        firstNameField.sendKeys(text);

        return this;
    }

    public AddCustomerPage fillLastNameField(String text) {
        lastNameField.sendKeys(text);

        return this;
    }

    public AddCustomerPage fillPostCodeField(String text) {
        postCodeField.sendKeys(text);

        return this;
    }

    public AddCustomerPage clickAddCustomerButton() {
        addCustomerButton.click();

        return this;
    }

    public String getSuccessAlertText() {
        return driver.switchTo().alert().getText();
    }
}
