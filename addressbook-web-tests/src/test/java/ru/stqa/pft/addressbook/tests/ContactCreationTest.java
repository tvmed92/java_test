package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData(
                "taty",
                "vikt",
                "molx",
                "molx2",
                "qwerty",
                "gazq",
                "kron",
                "ttt",
                "111",
                "111",
                "222",
                "tat@rrr.ru",
                "1992",
                "eeee",
                "www",
                "test1"),
                true);
    }
}
