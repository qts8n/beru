package main;

import beru.HomePage;
import beru.LoginFormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Listeners(StepScreenshoter.class)
public class BeruTest {
    private static final int TIMEOUT_DEFAULT = 10;

    WebDriver ffDriver;

    @BeforeMethod
    protected void testInit() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver", projectPath + Config.GECKO_PATH);
        ffDriver = new FirefoxDriver();
        ffDriver.get(Config.HOSTNAME);
        ffDriver.manage().timeouts().implicitlyWait(TIMEOUT_DEFAULT, TimeUnit.SECONDS);
    }

    @AfterMethod
    protected void testFinalize(ITestResult testResult) {
        HomePage homePage = new HomePage(ffDriver);
        homePage.logOutBtnClick();
        ffDriver.close();
    }

    void authorize(HomePage homePage) {
        homePage.openLoginForm();
        LoginFormPage loginPage = new LoginFormPage(ffDriver);
        loginPage.login();
    }
}
