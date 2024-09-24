package com.chrisguru.tests.vendorPortal;

import com.chrisguru.tests.BaseTest;
import com.chrisguru.tests.vendorPortal.model.VendorPortalTestData;
import com.chrisguru.util.Config;
import com.chrisguru.util.Constants;
import com.chrisguru.util.JsonUtil;
import com.christena.pages.vendorportal.DashboardPage;
import com.christena.pages.vendorportal.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private VendorPortalTestData vendorTestData;
    @BeforeTest
    @Parameters("testDataPath")
    public void initializePageObjects(String testDataPath){
        this.loginPage=new LoginPage(driver);
        this.dashboardPage=new DashboardPage(driver);
        vendorTestData = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
    }


    @Test
    public void loginTest(){
        loginPage.gotoUrl(Config.getKeyValue(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(vendorTestData.userName(), vendorTestData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void checkDashboardMetricsTest(){
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.returnMonthlEarning(), vendorTestData.monthlyEarning());
        Assert.assertEquals(dashboardPage.returnAnnualEarning(), vendorTestData.annualEarning());
        Assert.assertEquals(dashboardPage.returnProfitMargin(), vendorTestData.profitMargin());
        Assert.assertEquals(dashboardPage.returnInventory(),vendorTestData.availableInventory());
    }

    @Test(dependsOnMethods = "checkDashboardMetricsTest")
    public void searchTest(){
        Assert.assertTrue(dashboardPage.isAt());
        dashboardPage.search(vendorTestData.searchKeyword());
        Assert.assertEquals(dashboardPage.returnRecordsCount(),vendorTestData.searchResultCount());
    }

    @Test(dependsOnMethods = "searchTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }




}
