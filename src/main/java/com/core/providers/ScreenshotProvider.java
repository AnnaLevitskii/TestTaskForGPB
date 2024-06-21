package com.core.providers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotProvider{
    WebDriver driver;
    public ScreenshotProvider(WebDriver driver) {
        this.driver = driver;
    }
    public void takeScreenshot() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd_HH:mm");
        String dateStr = simpleDateFormat.format(date);
        String link = "src/test/screenshots/"+dateStr+".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver ;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp.toPath(), new File(link).toPath());
        }catch (Exception e){
            System.out.println("getScreenshot error");
        }
    }
    public byte[] takeScreenshotAsBytes() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
