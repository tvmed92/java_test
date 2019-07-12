package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver wD;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.CHROME)) {
            wD = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wD = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wD = new InternetExplorerDriver();
        }
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
