package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("james")
                    .withLastname("bond").withMiddlename("little")
                    .withNickname("littleBond").withEmail("www").withEmail2("wwww").withEmail3("wwwww")
                    .withAddress("aaa").withHomePh("111").withMobilePh("222").withWorkPh("333")
                    .withCompany("dd").withTitle("sss").withFax("ggg").withTitle("cbb")
                    .withExtraAddress("ex1").withExtraPhone("ex2"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("name").withLastname("name2").withMiddlename("name3")
                .withNickname("fff").withEmail("www").withEmail2("wwww").withEmail3("wwwww")
                .withAddress("aaa").withHomePh("111").withMobilePh("222").withWorkPh("333")
                .withCompany("dd").withTitle("sss").withFax("ggg").withTitle("cbb")
                .withExtraAddress("ex1").withExtraPhone("ex2");
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
