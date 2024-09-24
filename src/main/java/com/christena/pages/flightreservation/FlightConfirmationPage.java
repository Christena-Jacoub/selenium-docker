package com.christena.pages.flightreservation;

import com.christena.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends BasePage {

    //private static final Logger log= LoggerFactory.getLogger(FlightSearchPage.class);>>> already intialized in the baseclass in order to use in all page when required
    @FindBy(css = "div.card:nth-child(1) .row:nth-child(3) .col:nth-child(2)")
    private  WebElement totalPricetxt;

    @FindBy(css = "div.card:nth-child(1) .row:nth-child(1) .col:nth-child(2)")
    private WebElement confirmationNumbertxt;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(totalPricetxt));
        return this.totalPricetxt.isDisplayed();
    }

    public String getPrice(){
        String price=this.totalPricetxt.getText();
        String confirmationNo=this.confirmationNumbertxt.getText();

        log.info("The confirmation# is: {} and the price is: {}", confirmationNo,price);
        return price;
    }
}
