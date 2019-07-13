package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wD) {
        super(wD);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void goToGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wD.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void goToAddNewPage() {
        click(By.linkText("add new"));
    }

    public void acceptAlert() {
        wD.switchTo().alert().accept();
    }
}
