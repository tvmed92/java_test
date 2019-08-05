package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
        type(By.name("home"), contactData.getHomePh());
        type(By.name("mobile"), contactData.getMobilePh());
        type(By.name("work"), contactData.getWorkPh());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        selectFromList("bday", "1");
        selectFromList("bmonth", "January");
        type(By.name("byear"), contactData.getYearOfBirth());
        type(By.name("address2"), contactData.getExtraAddress());
        type(By.name("phone2"), contactData.getExtraPhone());
        attach(By.name("photo"), contactData.getPhoto());

        if (isCreation) {
            if (contactData.getGroup() != null) {
                new Select(wD.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
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

    public void initModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

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
        List<WebElement> rows = wD.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initModificationByID(contact.getId());
        String firstname = wD.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wD.findElement(By.name("lastname")).getAttribute("value");
        String homePh = wD.findElement(By.name("home")).getAttribute("value");
        String mobilePh = wD.findElement(By.name("mobile")).getAttribute("value");
        String workPh = wD.findElement(By.name("work")).getAttribute("value");
        String email = wD.findElement(By.name("email")).getAttribute("value");
        String email2 = wD.findElement(By.name("email2")).getAttribute("value");
        String email3 = wD.findElement(By.name("email3")).getAttribute("value");
        String address = wD.findElement(By.name("address")).getText();
        wD.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePh(homePh).withMobilePh(mobilePh).withWorkPh(workPh)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    private void initModificationByID(int id) {
        wD.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public int count() {
        return wD.findElements(By.name("selected[]")).size();
    }
}