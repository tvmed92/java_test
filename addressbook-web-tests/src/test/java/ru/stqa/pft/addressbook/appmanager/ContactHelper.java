package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wD) {
        super(wD);
    }

    public void submitForm() {
        click(By.xpath("//input[@name='submit'][2]"));
    }

    public void fillForm(ContactData contactData, boolean isCreation) {
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

    public void selectContactById(int id) {
        wD.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initModification() { click(By.xpath("//img[@alt='Edit']")); }

    public void submitModification() {
        click(By.xpath("(//input[@name='update'])"));
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void create(ContactData contact, boolean isCreation) {
        new NavigationHelper(wD).newContactPage();
        fillForm(contact, isCreation);
        submitForm();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initModification();
        fillForm(contact, false);
        submitModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        new NavigationHelper(wD).acceptAlert();
        returnToHomePage();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wD.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}