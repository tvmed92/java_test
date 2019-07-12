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
        click(By.linkText("groups"));
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void goToAddNewPage() {
        click(By.linkText("add new"));
    }

    public void acceptAlert() {
        wD.switchTo().alert().accept();
    }
}
