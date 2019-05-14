package main;

import beru.HomePage;
import beru.LoginFormPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationTest extends BeruTest {
    private static final String PROFILE_BTN_TEXT = "Мой Профиль";

    @Test
    public void login() {
        HomePage homePage = new HomePage(ffDriver);
        authorize(homePage);
        assertThat(homePage.isLoggedIn()).isTrue();
        assertThat(homePage.getProfileBtnText()).isEqualToIgnoringCase(PROFILE_BTN_TEXT);
    }
}
