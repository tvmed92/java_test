package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(ChromeDriver wD) {
        super(wD);
    }

    public void submitContactForm() {
        click(By.xpath("//input[@name='submit'][2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobilePh());
        type(By.name("work"), contactData.getWorkPh());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        click(By.name("bday"));
        new Select(wD.findElement(By.name("bday"))).selectByVisibleText("1");
        click(By.name("bday"));
        click(By.name("bmonth"));
        new Select(wD.findElement(By.name("bmonth"))).selectByVisibleText("January");
        click(By.name("bmonth"));
        type(By.name("byear"), contactData.getYearOfBirth());
        type(By.name("address2"), contactData.getExtraAddress());
        type(By.name("phone2"), contactData.getExtraPhone());
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void updateContact() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }
}