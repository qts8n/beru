package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class BeruPage {
    protected static WebDriver instance;

    protected By body = By.tagName("body");
    protected By loginBtn = By.cssSelector("a[href*='login']");
    protected By profileBtn = By.className("header2-nav__user");
    protected By logoutBtn = By.cssSelector("a[href*='logout']");
    protected By regionFormOpener = By.cssSelector(".region-form-opener>.link>.link__inner");
    protected By regionForm = By.className("header2-region-popup");
    protected By regionFormAutocomplete = By.className("region-suggest__list-item");
    protected By settingsBtn = By.className("header2-user-menu__item_type_settings");
    protected By catalogBtn = By.className("header2__navigation");
    protected By catalogCategoryList = By.className("n-navigation-vertical-category");
    protected By cartBtn = By.cssSelector("a[href*='cart']");

    public BeruPage(WebDriver driver) {
        instance = driver;
    }

    static WebDriver getInstance() {
        return instance;
    }

    protected String getRegion() {
        return instance.findElement(regionFormOpener).getText();
    }

    protected int getPriceFromElement(WebElement webElement){
        String price = webElement.getText().replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException error) {
            return 0;
        }
    }
}
