package beru;

import io.qameta.allure.Step;
import main.BeruPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage extends BeruPage {
    private By regionDiv = By.id("region");

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Getting region value in settings page")
    public String getSettingsCity() {
        return instance.findElement(regionDiv).findElement(By.className("link__inner")).getText();
    }
}
