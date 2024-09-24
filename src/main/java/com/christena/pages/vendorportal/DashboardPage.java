package com.christena.pages.vendorportal;

import com.christena.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    @FindBy(id="monthly-earning")
    private WebElement monthlyEarning;
    @FindBy(id="annual-earning")
    private WebElement annualEarning;
    @FindBy(id="profit-margin")
    private WebElement profitMargin;
    @FindBy(id = "available-inventory")
    private WebElement availableInventory;


    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;
    @FindBy (id = "dataTable_info")
    private WebElement recordCount;

    @FindBy(css = "#userDropdown img")
    private WebElement userLogoutImg;
    @FindBy(partialLinkText = "Logout")
    private WebElement logoutLink;
    @FindBy(css = ".modal-footer a")
    private WebElement confirmLogoutbtn;
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
        return this.monthlyEarning.isDisplayed();
    }

    public String returnMonthlEarning(){
        return this.monthlyEarning.getText();
    }
    public String returnAnnualEarning(){
        return this.annualEarning.getText();
    }
    public String returnProfitMargin(){
        return this.profitMargin.getText();
    }
    public String returnInventory(){
        return this.availableInventory.getText();
    }

    public void search(String entry){
        this.searchInput.sendKeys(entry);
    }

    public int returnRecordsCount(){
        String[] splitArr=this.recordCount.getText().split(" ");
        int recordsCount=Integer.parseInt(splitArr[5]);
        log.info("The records count is: {}", recordsCount);
        return recordsCount;
    }

    public void logout(){
        this.userLogoutImg.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmLogoutbtn));
        this.confirmLogoutbtn.click();
    }


}
