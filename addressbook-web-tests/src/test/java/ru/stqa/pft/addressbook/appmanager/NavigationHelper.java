package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper {
    private ChromeDriver wD;

    public NavigationHelper(ChromeDriver wD) {
        this.wD = wD;
    }

    public void returnToGroupPage() {
        wD.findElement(By.linkText("group page")).click();
    }

    public void goToGroupPage() {
        wD.findElement(By.linkText("groups")).click();
    }

    public void goToHomePage() {
        wD.findElement(By.linkText("home page")).click();
    }

    public void goToAddNewPage() {
        wD.findElement(By.linkText("add new")).click();
    }
}
