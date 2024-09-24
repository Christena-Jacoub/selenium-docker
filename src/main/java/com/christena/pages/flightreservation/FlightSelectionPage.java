package com.christena.pages.flightreservation;

import com.christena.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends BasePage {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightsSelections;

    @FindBy(name= "arrival-flight")
    private List<WebElement> arrivalFlightsSelections;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsBtn;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsBtn));
        return this.confirmFlightsBtn.isDisplayed();
    }

    public void selectFlights(){
        int random= ThreadLocalRandom.current().nextInt(0,departureFlightsSelections.size());
        this.arrivalFlightsSelections.get(random).click();
        this.departureFlightsSelections.get(random).click();
    }

    public void confirmFlight(){
        this.confirmFlightsBtn.click();
    }
}
