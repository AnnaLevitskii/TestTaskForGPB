package com.core.listeners;

import com.core.providers.ScreenshotProvider;
import io.qameta.allure.Allure;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        //
        ITestListener.super.onTestFailure(result);
    }

}
