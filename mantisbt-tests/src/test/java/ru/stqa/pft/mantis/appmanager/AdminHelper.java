package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

//    public void start(String username, String password) {
//        wD.get(app.getProperty("web.baseUrl") + "/login_page.php");
//        type(By.name("username"), username);
//        click(By.cssSelector("input[value='Войти']"));
//        type(By.name("password"), password);
//        click(By.cssSelector("input[value='Войти']"));
//    }

    public void initPasswordChange(UserData user) {
        click(By.className("menu-text"));
        click(By.linkText("Управление пользователями"));
        selectUserById(user.getId());
        click(By.cssSelector("input[@value='Сбросить пароль']"));
    }

    private void selectUserById(long id) {
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
    }

//    private Users userCache = null;
//
//    public Users all() {
//        if (userCache != null) {
//            return new Users(userCache);
//        }
//        userCache = new Users();
//        List<WebElement> elements = wD.findElements(By.cssSelector("span.group"));
//        for (WebElement element : elements) {
//            String username = element.getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            userCache.add(new UserData().setId(id).setUsername(username));
//        }
//        return new Users(userCache);
//    }
}
