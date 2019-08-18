package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wD;


    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (browser.equals(BrowserType.CHROME)) {
            wD = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wD = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wD = new InternetExplorerDriver();
        }
        wD.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wD.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wD.quit();
    }
}
