package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData((before.get(before.size() - 1).getId()),
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
                "www1",
                "test1");
        app.getContactHelper().createContact(contact, true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
