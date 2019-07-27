package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        app.goTo().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("sany","don","smith",
                    null,null,null,null,null,null,null,
                    null,null,null,null,null,"test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
//        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
                "grant",
                "little",
                "simpson",
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
                "www1",
                "test1");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.goTo().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
