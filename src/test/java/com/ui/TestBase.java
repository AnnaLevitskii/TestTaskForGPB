package com.ui;

import com.api.Token;
import com.core.models.enums.ScreenSize;
import com.core.providers.ScreenProvider;
import com.core.providers.ScreenshotProvider;
import com.core.utils.Constants;
import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.core.providers.DriverProvider.createDriver;
import static com.core.providers.DriverProvider.createRemoteWebDriver;
import static com.core.utils.Constants.*;


public class TestBase {
    public static WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "screen"})
    public void setUp(@Optional("Chrome")String browser, @Optional("D_S")String screen ){
        BROWSER = browser;
        if(browser.contains("selenoid")){
            driver = createRemoteWebDriver(browser);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.navigate().to(URL_UI);
        }else {
            driver = createDriver(browser);
            try {
                ScreenSize screenSize = ScreenProvider.getScreenSize(screen);
                driver.manage().window().setSize(new Dimension(screenSize.getWidth(), screenSize.getHeight()));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.navigate().to(URL_UI);
            if(!this.getClass().getSimpleName().contains("Login") &&
                    !this.getClass().getSimpleName().contains("Registr") && !browser.equalsIgnoreCase("safari")){
                LocalStorage local = ((WebStorage) driver).getLocalStorage();
                local.setItem("token", Token.getCurrentToken());
                driver.navigate().refresh();
            }
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                break;
            case ITestResult.FAILURE:
                Allure.getLifecycle().addAttachment(
                    "screenshot", "image/png", "png",
                     new ScreenshotProvider(driver).takeScreenshotAsBytes());
                break;
            case ITestResult.SKIP:
                break;
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        //allureAddAttachment();
        try {
            if (driver != null) {
                driver.quit();
            }
        }catch (WebDriverException e){
            driver.quit();
        }
    }



}
