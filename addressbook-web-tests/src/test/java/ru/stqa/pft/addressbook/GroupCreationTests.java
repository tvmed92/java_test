package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class GroupCreationTests {
  private WebDriver wD;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wD = new ChromeDriver();
    wD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testGroupCreation() throws Exception {
    wD.get("http://localhost/addressbook/");
    wD.findElement(By.name("user")).clear();
    wD.findElement(By.name("user")).sendKeys("admin");
    wD.findElement(By.name("pass")).clear();
    wD.findElement(By.name("pass")).sendKeys("secret");
    wD.findElement(By.xpath("//input[@value='Login']")).click();
    wD.findElement(By.linkText("groups")).click();
    wD.findElement(By.name("new")).click();
    wD.findElement(By.name("group_name")).click();
    wD.findElement(By.name("group_name")).clear();
    wD.findElement(By.name("group_name")).sendKeys("test_group");
    wD.findElement(By.name("group_header")).clear();
    wD.findElement(By.name("group_header")).sendKeys("test_header");
    wD.findElement(By.name("group_footer")).click();
    wD.findElement(By.name("group_footer")).clear();
    wD.findElement(By.name("group_footer")).sendKeys("test_footer");
    wD.findElement(By.name("submit")).click();
    wD.findElement(By.linkText("group page")).click();
    wD.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod(alwaysRun = true)
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
