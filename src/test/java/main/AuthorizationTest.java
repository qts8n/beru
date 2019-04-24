package main;

import beru.HomePage;
import beru.LoginFormPage;
import org.testng.annotations.Test;

public class AuthorizationTest extends BeruTest {
    @Test
    public void login() {
        HomePage homePage = new HomePage(ffDriver);
        homePage.openLoginForm();
        LoginFormPage loginPage = new LoginFormPage(ffDriver);
        loginPage.login();
        homePage.isLoggedIn();
    }
}
