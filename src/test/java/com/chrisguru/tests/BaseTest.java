package com.chrisguru.tests;

import com.chrisguru.listener.TestListener;
import com.chrisguru.util.Config;
import com.chrisguru.util.Constants;
import com.christena.pages.vendorportal.DashboardPage;
import com.christena.pages.vendorportal.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
@Listeners({TestListener.class})
public abstract class BaseTest {
    private static final Logger log= LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
    ///  if I want that each test should have it's own type of browser, so I pass the value as a param. in the testng xml file
   // @Parameters("browser")
    ///commented previous step since I set the browser in the default.properties file and to read I used Config file
    public void setDriver(ITestContext context) {
   // public void setDriver() {
        ///if I want to define the browser in the POM so the same browser will be applied across all the tests,
       //String browser=System.getProperty("browser"); /// this will get the value of browser node in the POM file
        /// I commented the prev step since if I define the browser in the POM so the same browser will be applied across all the tests,
        log.info( "The BrowserStack enabled? "+Boolean.parseBoolean(Config.getKeyValue(Constants.BROWSERSTACK_ENABLED)));
        //if(Boolean.getBoolean(Config.getKeyValue(Constants.GRID_ENABLED))) { /// if I used System.property, it will return string but I need it as a boolean so I used t like that
        if(Boolean.parseBoolean(Config.getKeyValue(Constants.GRID_ENABLED))||
                (Boolean.parseBoolean(Config.getKeyValue(Constants.BROWSERSTACK_ENABLED)))) {
            this.driver=getRemoteWebDriver();
        } else{
            this.driver=getLocalWebDriver();
        }
        context.setAttribute(Constants.DRIVER,driver);
    }
    private WebDriver getLocalWebDriver(){
        log.info("The browser type we got is: {}", Config.getKeyValue(Constants.BROWSER));
        return switch (Config.getKeyValue(Constants.BROWSER)) {
            case Constants.CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case Constants.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
           /* case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }*/
            default -> null;
        };
    }
    private WebDriver getRemoteWebDriver()  {
        log.info("The browser type we got is: {}", Config.getKeyValue(Constants.BROWSER));
        Capabilities capabilities;
        switch (Config.getKeyValue(Constants.BROWSER)){
            case Constants.FIREFOX:
                capabilities=new FirefoxOptions();
                break;
            case  Constants.EDGE:
                capabilities=new EdgeOptions();
                break;
            default:
                capabilities=new ChromeOptions();
                break;
        }
        //String urlFormat= Config.getKeyValue(Constants.GRID_URL_FORMAT);
        //String urlHost=Config.getKeyValue(Constants.GRID_HUB_HOST);
        //String url=String.format(urlFormat,urlHost);
        log.info("The remote url is: {}", getRemoteURL());
        try {
            return new RemoteWebDriver(new URL(getRemoteURL()),capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRemoteURL(){
        String url="";
        if(Boolean.parseBoolean(Config.getKeyValue(Constants.GRID_ENABLED)))
        {
            String urlFormat= Config.getKeyValue(Constants.GRID_URL_FORMAT);
            String urlHost=Config.getKeyValue(Constants.GRID_HUB_HOST);
            url=String.format(urlFormat,urlHost);
        }
        else if(Boolean.parseBoolean(Config.getKeyValue(Constants.BROWSERSTACK_ENABLED))){
            String urlFormat= Config.getKeyValue(Constants.BROWSERSTACK_URL);
            String userID= System.getenv("BROWSERSTACK_USERNAME");
            String userKey=System.getenv("BROWSERSTACK_ACCESS_KEY");
            log.info("The userID is, "+ userID+" The userKey is: "+ userKey);
            url = String.format(urlFormat,userID,userKey);
        }
        return url;
    }
    @AfterTest
    public void quiteDriver(){
        driver.quit();
    }
}
