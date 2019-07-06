package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    ChromeDriver wD;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
        wD = new ChromeDriver();
        wD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wD.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wD);
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

    public void submitContactForm() {
        wD.findElement(By.xpath("xpath=(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        wD.findElement(By.name("firstname")).click();
        wD.findElement(By.name("firstname")).clear();
        wD.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wD.findElement(By.name("middlename")).click();
        wD.findElement(By.name("middlename")).clear();
        wD.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wD.findElement(By.name("lastname")).click();
        wD.findElement(By.name("lastname")).clear();
        wD.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wD.findElement(By.name("nickname")).click();
        wD.findElement(By.name("nickname")).clear();
        wD.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        wD.findElement(By.name("title")).click();
        wD.findElement(By.name("title")).clear();
        wD.findElement(By.name("title")).sendKeys(contactData.getTitle());
        wD.findElement(By.name("company")).click();
        wD.findElement(By.name("company")).clear();
        wD.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wD.findElement(By.name("address")).click();
        wD.findElement(By.name("address")).click();
        wD.findElement(By.name("address")).clear();
        wD.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wD.findElement(By.name("home")).click();
        wD.findElement(By.name("home")).clear();
        wD.findElement(By.name("home")).sendKeys(contactData.getHome());
        wD.findElement(By.name("mobile")).click();
        wD.findElement(By.name("mobile")).clear();
        wD.findElement(By.name("mobile")).sendKeys(contactData.getMobilePh());
        wD.findElement(By.name("work")).click();
        wD.findElement(By.name("work")).clear();
        wD.findElement(By.name("work")).sendKeys(contactData.getWorkPh());
        wD.findElement(By.name("fax")).click();
        wD.findElement(By.name("fax")).clear();
        wD.findElement(By.name("fax")).sendKeys(contactData.getFax());
        wD.findElement(By.name("email")).click();
        wD.findElement(By.name("email")).clear();
        wD.findElement(By.name("email")).sendKeys(contactData.getEmail());
        wD.findElement(By.name("bday")).click();
        new Select(wD.findElement(By.name("bday"))).selectByVisibleText("1");
        wD.findElement(By.name("bday")).click();
        wD.findElement(By.name("bmonth")).click();
        new Select(wD.findElement(By.name("bmonth"))).selectByVisibleText("January");
        wD.findElement(By.name("bmonth")).click();
        wD.findElement(By.name("byear")).click();
        wD.findElement(By.name("byear")).clear();
        wD.findElement(By.name("byear")).sendKeys(contactData.getYearOfBirth());
        wD.findElement(By.name("new_group")).click();
        new Select(wD.findElement(By.name("new_group"))).selectByVisibleText("test_group");
        wD.findElement(By.name("new_group")).click();
        wD.findElement(By.name("address2")).click();
        wD.findElement(By.name("address2")).click();
        wD.findElement(By.name("address2")).clear();
        wD.findElement(By.name("address2")).sendKeys(contactData.getExtraAddress());
        wD.findElement(By.name("phone2")).click();
        wD.findElement(By.name("phone2")).clear();
        wD.findElement(By.name("phone2")).sendKeys(contactData.getExtraPhone());
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

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
