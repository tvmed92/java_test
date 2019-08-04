package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
    protected WebDriver wD;

    public HelperBase(WebDriver wD) {
        this.wD = wD;
    }

    protected void click(By locator) {
        wD.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wD.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wD.findElement(locator).clear();
                wD.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) {
                wD.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wD.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wD.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
