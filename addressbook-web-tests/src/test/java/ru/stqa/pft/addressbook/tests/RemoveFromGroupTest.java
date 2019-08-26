package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveFromGroupTest extends TestBase{

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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test1").withHeader("header1").withFooter("footer1"));
        }
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < 1) {
                GroupData chosenGroup = app.db().groups().iterator().next();
                app.contact().addToGroup(contact, chosenGroup);
            }
        }
        app.goTo().homePage();
    }

    @Test
    public void testRemoveFromGroup() {
        Contacts contactsBefore = app.db().contacts();
        for (ContactData contactBefore : contactsBefore) {
            if (contactBefore.getGroups() != null) {
                Groups groupsBefore = contactBefore.getGroups();
                app.contact().removeFromGroup(contactBefore);
                Contacts contactsAfter = app.db().contacts();
                for (ContactData contactAfter : contactsAfter) {
                    if (contactAfter.getId() == contactBefore.getId()) {
                        Groups groupsAfter = contactAfter.getGroups();
                        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
                        groupsBefore.removeAll(groupsAfter);
                        assertThat(groupsAfter, equalTo(
                                contactBefore.getGroups().without(groupsBefore.iterator().next())));
                    }
                }
            }
        }
    }
}
