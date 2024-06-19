package com.ui.pages;

import com.api.ContactController;
import com.core.models.dto.AuthRequestDTO;
import com.core.models.dto.ContactDTO;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import static com.core.providers.TestDataGenerator.*;
import static com.core.providers.TestDataGenerator.randomText;
import static com.core.utils.Constants.PASSWORD;
import static com.core.utils.Constants.USER_NAME;

public class ContactPage extends ContactController {
    WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public ContactPage removeContact(){
        if(((RemoteWebDriver) driver).getCapabilities().getBrowserName().equalsIgnoreCase("safari")){
            AuthRequestDTO auth = AuthRequestDTO.builder().username(USER_NAME).password(PASSWORD).build();
            new LoginPage(driver).navigateToLoginPage().logIn(auth).assertContactPage();
        }
        driver.findElement(By.xpath("//a[normalize-space()='CONTACTS']")).click();
        int countContactsBefore = countContacts()-1;
        if(countContacts()==0){
            ContactDTO contactDTO = ContactDTO.builder().name(randomName()).lastName(randomLastName()).phone(randomPhone()).email(randomEmail()).address(randomLocation()).description(randomText()).build();
            new ContactPage(driver).navigateToAddPage().addContact(contactDTO).assertLastContact(contactDTO);
            countContactsBefore = countContacts()-1;
        }
        driver.findElement(By.xpath("//div[1][@class='contact-item_card__2SOIM']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Remove']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(1));
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("contact-item-detailed_card__50dTS")));
        int countContactsAfter = countContacts();
        Assert.assertEquals(countContactsBefore, countContactsAfter);

        return this;
    }

    public ContactPage assertContactPage(){
        WebElement buttonHome = driver.findElement(By.xpath("//a[normalize-space()='HOME']"));
        Assert.assertTrue(buttonHome.isDisplayed());
        return this;
    }

    public AddPage navigateToAddPage(){
        if(((RemoteWebDriver) driver).getCapabilities().getBrowserName().equalsIgnoreCase("safari")){
            AuthRequestDTO auth = AuthRequestDTO.builder().username(USER_NAME).password(PASSWORD).build();
            new LoginPage(driver).navigateToLoginPage().logIn(auth).assertContactPage();
        }
        try {
            driver.findElement(By.xpath("//a[normalize-space()='ADD']")).click();
        }catch (ElementNotInteractableException e){
            driver.manage().window().setSize(new Dimension(896, 414));
            driver.findElement(By.xpath("//a[normalize-space()='ADD']")).click();
        }
        return new AddPage(driver);
    }
    public int countContacts(){
        List<WebElement> contactsList = driver.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        return contactsList.size();
    }

    public ContactPage assertLastContact(ContactDTO contactDTO){
        WebElement lastContactName = driver.findElement(By.xpath("//h2[text()='"+contactDTO.getName()+"']"));
        WebElement lastContactPhone = driver.findElement(By.xpath("//h3[text()='"+contactDTO.getPhone()+"']"));

        Assert.assertTrue(lastContactName.isDisplayed());
        Assert.assertTrue(lastContactPhone.isDisplayed());
        return this;
    }


}
