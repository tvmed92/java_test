package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SessionHelper {
    private ChromeDriver wD;

    public SessionHelper(ChromeDriver wD) {
        this.wD = wD;
    }

    public void login(String username, String password) {
        wD.findElement(By.name("user")).sendKeys(username);
        wD.findElement(By.name("pass")).clear();
        wD.findElement(By.name("user")).clear();
        wD.findElement(By.name("pass")).sendKeys(password);
        wD.findElement(By.xpath("//input[@value='Login']")).click();
    }
}
