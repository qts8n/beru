package beru;

import io.qameta.allure.Step;
import main.BeruPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HomePage extends BeruPage {
    private static final int TIMEOUT_DEFAULT = 5;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getProfileBtnText() {
        return instance.findElement(profileBtn).getText();
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

    @Step("Locating profile menu item")
    private WebElement locateProfileMenuItem(By item) {
        Actions actions = new Actions(instance);
        actions.moveToElement(instance.findElement(profileBtn)); // triggering hover
        actions.click().build().perform();
        return (new WebDriverWait(instance, TIMEOUT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(item));
    }

    @Step("Logging out")
    public void logOutBtnClick() {
        WebElement appearedLogOutBtn = locateProfileMenuItem(logoutBtn);
        appearedLogOutBtn.click();
    }

    @Step("Region select form open")
    private void openRegionForm() {
        instance.findElement(regionFormOpener).click();
    }

    @Step("Typing and confirming new region")
    public void setCity(String cityName) {
        openRegionForm();
        WebElement form = instance.findElement(regionForm);
        WebElement input = form.findElement(By.tagName("input"));
        input.sendKeys(cityName);
        WebElement autocomplete = instance.findElement(regionFormAutocomplete);
        Actions actions = new Actions(instance);
        actions.moveToElement(autocomplete).click().build().perform();
        WebElement resumeBtn = form.findElement(By.tagName("button"));
        resumeBtn.click();
    }

    @Step("Opening settings menu")
    public void openSettingsPage() {
        locateProfileMenuItem(settingsBtn).click();
    }

    @Step("Opening catalog")
    public void openCatalog() {
        WebElement catalogOpener = (new WebDriverWait(instance, TIMEOUT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(catalogBtn));
        Actions actions = new Actions(instance);
        actions.moveToElement(catalogOpener).click().build().perform();
    }

    @Step("Opening catalog subsection")
    public void openCatalogSubsection(String section, String subsection) {
        WebElement categoryList = instance.findElement(catalogCategoryList);
        WebElement sectionLink = categoryList.findElement(By.linkText(section));
        Actions actions = new Actions(instance);
        actions.moveToElement(sectionLink).perform();
        WebElement subsectionLink = (new WebDriverWait(instance, TIMEOUT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(subsection)));
        actions.moveToElement(subsectionLink).click().build().perform();
    }

    @Step("Opening cart page")
    public void openCartPage() {
        instance.findElement(cartBtn).click();
    }
}
