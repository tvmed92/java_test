package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wD) {
        super(wD);
    }

    public void submitContactForm() {
        click(By.xpath("//input[@name='submit'][2]"));
    }

    public void fillContactForm(ContactData contactData, boolean isCreation) {
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
        selectFromList("bday", "1");
        selectFromList("bmonth", "January");
        type(By.name("byear"), contactData.getYearOfBirth());
        type(By.name("address2"), contactData.getExtraAddress());
        type(By.name("phone2"), contactData.getExtraPhone());

        if (isCreation) {
            new Select(wD.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    private void selectFromList(String locator, String text) {
        new Select(wD.findElement(By.name(locator))).selectByVisibleText(text);
    }

    public void selectContact(int index) {
        wD.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int index) {
        wD.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void updateContact() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void createContact(ContactData contact, boolean isCreation) {
        new NavigationHelper(wD).goToAddNewPage();
        fillContactForm(contact, isCreation);
        submitContactForm();
        returnToHomePage();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wD.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("//tr/td[3]")).getText();
            String lastname = element.findElement(By.xpath("//tr/td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, null, lastname, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}