package com.christena.pages.flightreservation;

import com.christena.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends BasePage {
    //private WebDriver driver;
    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchBtn;
    @FindBy(css = "p.mt-3 b")
    private WebElement actualUserName;

    public RegistrationConfirmationPage(WebDriver driver){
        //this.driver=driver;
        //PageFactory.initElements(driver,this);
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(goToFlightSearchBtn));
        return this.goToFlightSearchBtn.isDisplayed();
    }

    public String getDisplayedUserName(){
        return actualUserName.getText();
    }
    public void clickGoToFlightSearchBtn(){
        goToFlightSearchBtn.click();
    }
}
