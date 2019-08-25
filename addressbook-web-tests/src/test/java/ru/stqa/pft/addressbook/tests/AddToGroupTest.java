package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddToGroupTest extends TestBase {

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
    }

    @Test
    public void testAddToGroup() {
        ContactData chosenContact = app.db().contacts().iterator().next();
        GroupData chosenGroup = app.db().groups().iterator().next();
        Groups groupsBefore = chosenContact.getGroups();
        if (!groupsBefore.contains(chosenGroup)) {
            app.contact().addToGroup(chosenContact, chosenGroup);
            Contacts contactsAfter = app.db().contacts();
            for (ContactData contactAfter : contactsAfter) {
                if (contactAfter.getId() == chosenContact.getId()) {
                    Groups groupsAfter = contactAfter.getGroups();
                    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
                    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(chosenGroup)));
                }
            }
        } else {
            app.goTo().groupPage();
            GroupData newGroup = new GroupData().withName("newName")
                    .withHeader("newHead").withFooter("newFoot");
            app.group().create(newGroup);
            app.goTo().homePage();
            app.contact().addToGroup(chosenContact, newGroup);
            Contacts contactsAfter = app.db().contacts();
            for (ContactData contactAfter : contactsAfter) {
                if (contactAfter.getId() == chosenContact.getId()) {
                    Groups groupsAfter = contactAfter.getGroups();
                    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
                    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(newGroup)));
                }
            }
        }
        app.contact().returnToHomePage();
    }
}

