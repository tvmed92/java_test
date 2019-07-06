package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData(
                "test_name",
                "test_header",
                "test_footer"));
        app.submitGroupCreation();
        app.returnToGroupPage();
        app.logout();
    }

}
