package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

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
        wD.findElement(locator).clear();
        wD.findElement(locator).sendKeys(text);
    }

    private boolean isElementPresent(By by) {
        try {
            wD.findElement(by);
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
