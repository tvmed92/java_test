package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePassByAdminTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassByAdmin() throws IOException {
        final String adminLogin = app.getProperty("web.adminLogin");
        final String adminPassword = app.getProperty("web.adminPassword");
        String userPassword = app.getProperty("web.userPassword");
        Users users = app.db().users();
        for (UserData user : users) {
            if (!user.getUsername().equals("administrator")) {
                user = new UserData().setId(users.iterator().next().getId());
                app.admin().start(adminLogin, adminPassword);
                app.admin().initPasswordChange(user);
                final List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
                final String changePasswordLink = findConfirmationLink(mailMessages, user.getMail());
                app.registration().finish(changePasswordLink, userPassword);
                app.newSession().login(user.getUsername(), userPassword);
                assertTrue(app.newSession().login(user.getUsername(), userPassword));
            }
        }
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        final MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
