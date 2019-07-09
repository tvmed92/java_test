package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData(
                "taty1",
                "vik1t",
                "molx1",
                "molx21",
                "qwty1",
                "gaz1q",
                "kron1",
                "ttt1",
                "1111",
                "1111",
                "2221",
                "ta1t@rrr.ru",
                "112",
                "e1eee",
                "www1"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
    }
}