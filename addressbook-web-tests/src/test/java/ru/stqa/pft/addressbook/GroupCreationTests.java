package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GroupCreationTests {
    private ChromeDriver wD;

    @BeforeMethod
    public void setUp() throws Exception {
        wD = new ChromeDriver();
        wD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wD.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wD.findElement(By.name("user")).clear();
        wD.findElement(By.name("user")).sendKeys(username);
        wD.findElement(By.name("pass")).clear();
        wD.findElement(By.name("pass")).sendKeys(password);
        wD.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testGroupCreation() throws Exception {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData(
                "test_name",
                "test_header",
                "test_footer"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }

    private void logout() {
        wD.findElement(By.linkText("Logout")).click();
    }

    private void returnToGroupPage() {
        wD.findElement(By.linkText("group page")).click();
    }

    private void submitGroupCreation() {
        wD.findElement(By.name("submit")).click();
    }

    private void fillGroupForm(GroupData groupData) {
        wD.findElement(By.name("group_name")).click();
        wD.findElement(By.name("group_name")).clear();
        wD.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
        wD.findElement(By.name("group_header")).clear();
        wD.findElement(By.name("group_header")).sendKeys(groupData.getGroupHeader());
        wD.findElement(By.name("group_footer")).click();
        wD.findElement(By.name("group_footer")).clear();
        wD.findElement(By.name("group_footer")).sendKeys(groupData.getGroupFooter());
    }

    private void initGroupCreation() {
        wD.findElement(By.name("new")).click();
    }

    private void goToGroupPage() {
        wD.findElement(By.linkText("groups")).click();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        wD.quit();
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
