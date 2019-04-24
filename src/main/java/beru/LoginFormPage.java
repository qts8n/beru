package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFormPage {
    private By loginTextInput = By.cssSelector("input#passp-field-login");
    private By formSubmitBtn = By.cssSelector("div.passp-sign-in-button > button.passp-form-button");
    private By passwdTextInput = By.cssSelector("input#passp-field-passwd");

    private WebDriver instance;

    private static final String YALOGIN = "efishtest";
    private static final String YAPSSWD = "12qwasZX";

    public LoginFormPage(WebDriver driver) {
        instance = driver;
    }

    @Step("Typing into text input")
    private void typeIntoTextInput(By inputSelector, String text) {
        WebElement textInput = (new WebDriverWait(instance, 5))
                .until(ExpectedConditions.presenceOfElementLocated(inputSelector));
        textInput.sendKeys(text);
    }

    @Step("Waiting to login field to appear to start typing login")
    private void typeIntoLogin() {
        typeIntoTextInput(loginTextInput, YALOGIN);
    }

    @Step("Waiting to password field to appear to start typing password")
    private void typeIntoPasswd() {
        typeIntoTextInput(passwdTextInput, YAPSSWD);
    }

    @Step("Submitting current form")
    private void inputSubmit() {
        instance.findElement(formSubmitBtn).click();
    }

    @Step("Filling form and submitting it")
    public void login() {
        typeIntoLogin();
        inputSubmit();
        typeIntoPasswd();
        inputSubmit();
    }
}
