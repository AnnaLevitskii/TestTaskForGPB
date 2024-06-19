package com.ui;

import com.core.listeners.RetryAnalyzer;
import com.ui.pages.ContactPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;


public class RemoveContactTest extends TestBase{
    @Severity(SeverityLevel.NORMAL)
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void removeOneContact(){
        new ContactPage(driver).removeContact();
    }
}
