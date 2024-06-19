package com.ui;

import com.core.listeners.RetryAnalyzer;
import com.ui.pages.ContactPage;
import org.testng.annotations.Test;


public class RemoveContactTest extends TestBase{

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void removeOneContact(){
        new ContactPage(driver).removeContact();
    }
}
