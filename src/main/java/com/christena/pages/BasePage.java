package com.christena.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger log= LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);

    }

    protected abstract boolean isAt();
}
