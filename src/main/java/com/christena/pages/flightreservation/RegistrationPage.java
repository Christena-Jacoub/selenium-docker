package com.christena.pages.flightreservation;

import com.christena.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {
   // private WebDriver driver;
    @FindBy(id="firstName")
    private WebElement firstNameInput;

    @FindBy(id="lastName")
    private WebElement lastNameInput;

    @FindBy(id="email")
    private WebElement emailInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(id = "inputState")
    private WebElement stateSelection;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id = "register-btn")
    private WebElement registerBtn;

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return this.firstNameInput.isDisplayed();
    }

    public void goTo(String url){
        driver.get(url);
    }

    public void enterUserDetails(String firstName,String lastName){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
    }

    public void enterUserCredentials(String email,String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public void enterUserAddress(String street,String city, String stateSelectionOption, String zip){
        streetInput.sendKeys(street);
        cityInput.sendKeys(city);
        Select selectState=new Select(stateSelection);
        selectState.selectByValue(stateSelectionOption);
        zipInput.sendKeys(zip);
    }

    public void clickRegister(){
        registerBtn.click();
    }
}
