package com.chrisguru.tests.flightreservation;

import com.chrisguru.tests.BaseTest;
import com.chrisguru.tests.flightreservation.model.FlightReservationTestData;
import com.chrisguru.util.Config;
import com.chrisguru.util.Constants;
import com.chrisguru.util.JsonUtil;
import com.christena.pages.flightreservation.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends BaseTest {
    //private String noOfPassengers;
    //private String expectedPrice;
    private FlightReservationTestData flightReservationTestData;
    @BeforeTest
    //@Parameters({"noOfPassengers", "expectedPrice"})
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){

        //this.noOfPassengers=noOfPassengers;
        //this.expectedPrice=expectedPrice;
        flightReservationTestData= JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);

    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo(Config.getKeyValue(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(flightReservationTestData.firstName(), flightReservationTestData.lastName());
        registrationPage.enterUserCredentials(flightReservationTestData.email(), flightReservationTestData.password());
        registrationPage.enterUserAddress(flightReservationTestData.street(), flightReservationTestData.city(), flightReservationTestData.state(), flightReservationTestData.zip());
        registrationPage.clickRegister();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage=new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getDisplayedUserName(),flightReservationTestData.firstName());
        registrationConfirmationPage.clickGoToFlightSearchBtn();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightSearchPage flightSearchPage=new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.enterFlightDetails(flightReservationTestData.noOfPassenger());
        flightSearchPage.clickSearchFlight();

    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        FlightSelectionPage flightSelectionPage=new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage= new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), flightReservationTestData.expectedPrice());
    }


}
