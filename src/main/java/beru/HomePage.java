package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class HomePage {
    private By body = By.tagName("body");
    private By loginBtn = By.cssSelector("a[href*='login']");

    private WebDriver instance;

    public HomePage(WebDriver driver) {
        instance = driver;
    }

    @Step("Click on login button")
    private void loginBtnClick() {
        instance.findElement(loginBtn).click();
    }

    @Step("Checking for profile button changes")
    public boolean isLoggedIn() {
        try {
            return instance.findElement(loginBtn) == null;
        } catch (NoSuchElementException err) {
            return true;
        }
    }

    @Step("Close modal window")
    private void closeModalByEscaping() {
        instance.findElement(body).sendKeys(Keys.ESCAPE);
    }

    @Step("Opening login form page")
    public void openLoginForm() {
        try {
            loginBtnClick();
        } catch (ElementClickInterceptedException err) {
            closeModalByEscaping();
            loginBtnClick();
        }
    }
}
