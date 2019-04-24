package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BeruTest {
    private static final int TIMEOUT_DEFAULT = 10;
    private static final String GECKO_PATH = "/webdrivers/geckodriver/geckodriver.exe";
    private static final String HOSTNAME = "https://beru.ru";

    WebDriver ffDriver;

    @BeforeMethod
    protected void testInit() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver", projectPath + GECKO_PATH);
        ffDriver = new FirefoxDriver();
        ffDriver.get(HOSTNAME);
        ffDriver.manage().timeouts().implicitlyWait(TIMEOUT_DEFAULT, TimeUnit.SECONDS);
    }

    @AfterMethod
    protected void testFinalize() {
        ffDriver.close();
    }
}
