package com.ui;

import com.api.Token;
import com.core.models.enums.ScreenSize;
import com.core.providers.ScreenProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.core.providers.DriverProvider.createDriver;
import static com.core.utils.Constants.URL_UI;


public class TestBase {
    public static WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "screen"})
    public void setUp(@Optional("Chrome")String browser, @Optional("D_S")String screen ){
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
    @AfterClass(alwaysRun = true)
    public static void tearDown(){
        driver.quit();
    }

}
