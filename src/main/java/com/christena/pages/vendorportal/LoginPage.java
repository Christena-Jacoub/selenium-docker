package com.christena.pages.vendorportal;

import com.christena.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "login")
    private WebElement loginBtn;
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.userNameInput));
        return this.userNameInput.isDisplayed();
    }

    public void gotoUrl(String url){
        driver.get(url);
    }

    public void login(String username, String password){
        this.userNameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.loginBtn.click();
    }
}
