package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {

    private ChromeDriver wD;

    public GroupHelper(ChromeDriver wD) {
        this.wD = wD;
    }

    public void submitGroupCreation() {
        wD.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        wD.findElement(By.name("group_name")).click();
        wD.findElement(By.name("group_name")).clear();
        wD.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
        wD.findElement(By.name("group_header")).clear();
        wD.findElement(By.name("group_header")).sendKeys(groupData.getGroupHeader());
        wD.findElement(By.name("group_footer")).click();
        wD.findElement(By.name("group_footer")).clear();
        wD.findElement(By.name("group_footer")).sendKeys(groupData.getGroupFooter());
    }

    public void initGroupCreation() {
        wD.findElement(By.name("new")).click();
    }

    public void deleteSelectedGroups() {
        wD.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
        wD.findElement(By.name("selected[]")).click();
    }
}
