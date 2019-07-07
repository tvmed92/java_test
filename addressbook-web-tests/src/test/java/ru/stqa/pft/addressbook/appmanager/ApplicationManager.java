package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private ChromeDriver wD;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
        wD = new ChromeDriver();
        wD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wD.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wD);
        contactHelper = new ContactHelper(wD);
        navigationHelper = new NavigationHelper(wD);
        sessionHelper = new SessionHelper(wD);
        sessionHelper.login("admin", "secret");
    }

    public void logout() {
        wD.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        wD.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
