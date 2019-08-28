package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String password) {
        wD.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        tapSubmit();
        type(By.name("password"), password);
        tapSubmit();
    }

    private void tapSubmit() {
        click(By.cssSelector("input[type='Submit']"));
    }

    public void initPasswordChange(UserData user) {
        click(By.className("menu-text"));
        click(By.cssSelector("a[href='/mantisbt-2.21.1/manage_user_page.php']"));
        selectUserById(user.getId());
        tapSubmit();
    }

    private void selectUserById(long id) {
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
    }
}
