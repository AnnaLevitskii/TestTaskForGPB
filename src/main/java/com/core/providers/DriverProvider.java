package com.core.providers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DriverProvider {
    public static ChromeOptions options;

    public static WebDriver createDriver(String browser){
        if (browser.equals(Browser.EDGE.browserName())) {
            return new EdgeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (browser.equals(Browser.SAFARI.browserName())) {
            WebDriverManager.safaridriver().setup();
            return new SafariDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            options = new ChromeOptions();
            return new ChromeDriver(options);
        }
    }
    public static RemoteWebDriver createRemoteWebDriver(String browser){
        if (browser.equals("firefox_selenoid")){
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("browserVersion", "125.0");
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("name", "Test badge...");
                put("sessionTimeout", "15m");
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});
                put("enableVideo", true);
                put("enableVNC", true);

            }});
            URL url;
            try {
                url = new URL("http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            return new RemoteWebDriver(url, options);
        } else if (browser.equals("edge_selenoid")) {
            EdgeOptions options = new EdgeOptions();
            options.setCapability("browserVersion", "124.0");
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("name", "Test badge...");
                put("sessionTimeout", "15m");
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});
                put("enableVideo", true);
                put("enableVNC", true);
            }});
            URL url;
            try {
                url = new URL("http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return new RemoteWebDriver(url, options);

        } else {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browserVersion", "125.0");
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("name", "Test badge...");
                put("sessionTimeout", "15m");
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});
                put("enableVideo", true);
                put("enableVNC", true);
            }});
            URL url;
            try {
                url = new URL("http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return new RemoteWebDriver(url, options);
        }
    }
}
