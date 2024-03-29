package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

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
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        selectFromList("bday", "1");
        selectFromList("bmonth", "January");
        type(By.name("byear"), contactData.getYearOfBirth());
        type(By.name("address2"), contactData.getExtraAddress());
        type(By.name("phone2"), contactData.getExtraPhone());
        attach(By.name("photo"), contactData.getPhoto());

        if (isCreation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                selectGroup("new_group", contactData.getGroups().iterator().next().getName());
            }
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

    public void initModification(int id) {
        wD.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
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
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initModification(contact.getId());
        fillForm(contact, false);
        submitModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        new NavigationHelper(wD).acceptAlert();
        returnToHomePage();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wD.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contactCache;
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

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupToAdd(group.getId());
        click(By.name("add"));
        returnToHomePage();
    }

    private void selectGroupToAdd(int id) {
        new Select(wD.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
    }

    private void selectGroup(String listName, String groupName) {
        new Select(wD.findElement(By.name(listName))).selectByVisibleText(groupName);
    }

    public void removeFromGroup(ContactData contact) {
        filterContactsByGroup(contact);
        selectContactById(contact.getId());
        click(By.name("remove"));
        returnToHomePage();
        selectAllGroupsContacts();
    }

    private void filterContactsByGroup(ContactData contact) {
        new Select(wD.findElement(By.name("group")))
                .selectByVisibleText(contact.getGroups().iterator().next().getName());
    }

    private void selectAllGroupsContacts() {
        new Select(wD.findElement(By.name("group"))).selectByVisibleText("[all]");
    }
}