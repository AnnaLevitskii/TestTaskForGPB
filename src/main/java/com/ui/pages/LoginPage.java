package com.ui.pages;

import com.core.models.dto.AuthRequestDTO;
import org.openqa.selenium.*;

import java.security.Key;

import static com.core.utils.Constants.PASSWORD;
import static com.core.utils.Constants.USER_NAME;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage navigateToLoginPage(){
        try {
            driver.findElement(By.xpath("//a[normalize-space()='LOGIN']")).click();
        }catch (NoSuchElementException | TimeoutException e){
            driver.findElement(By.xpath("//body")).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER);
        }
        return this;
    }
    public ContactPage logIn(AuthRequestDTO authRequestDTO){
        try {
            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(authRequestDTO.getUsername());
        }catch (NoSuchElementException | TimeoutException e){
            try{
                driver.findElement(By.xpath("//div[@id='root']//form/input[1]")).sendKeys(authRequestDTO.getUsername());
            }catch (NoSuchElementException | TimeoutException ex){
                driver.findElement(By.xpath("//body")).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, authRequestDTO.getUsername());
            }
        }
        try {
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(authRequestDTO.getPassword());
        }catch (NoSuchElementException | TimeoutException e){
            try {
                driver.findElement(By.xpath("//input[2]")).sendKeys(authRequestDTO.getPassword());
            }catch (NoSuchElementException | TimeoutException ex){
                driver.findElement(By.xpath("//body")).sendKeys( Keys.TAB, authRequestDTO.getPassword());
            }
        }
        try {
            driver.findElement(By.xpath("//button[@name='login']")).click();
        }catch (NoSuchElementException | TimeoutException e){
           try {
                driver.findElement(By.xpath("//button[1]")).click();
           }catch (NoSuchElementException | TimeoutException ex){
               driver.findElement(By.xpath("//body")).sendKeys( Keys.TAB, Keys.ENTER);
           }
        }
        return new ContactPage(driver);
    }







}
