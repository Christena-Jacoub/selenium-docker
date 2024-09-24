package com.christena.pages.flightreservation;

import com.christena.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Locale;

public class FlightSearchPage extends BasePage {


    @FindBy(id = "oneway")
    public WebElement oneWayRadioBtn;
    @FindBy(id = "twoway")
    public WebElement twoWayRadioBtn;

    @FindBy(id = "passengers")
    public WebElement passengersCountSelection;

    @FindBy(id="search-flights")
    public WebElement searchFlightBtn;

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(searchFlightBtn));
        return this.searchFlightBtn.isDisplayed();
    }

    public void enterFlightDetails(String passengerCount){
        log.info("the passengercount ={}", passengerCount);
        Select select=new Select(passengersCountSelection);
        select.selectByValue(passengerCount);
    }

    public void clickSearchFlight(){
        this.searchFlightBtn.click();
    }
}
