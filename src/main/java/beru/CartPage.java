package beru;

import io.qameta.allure.Step;
import main.BeruPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BeruPage {
    private static final int TIMEOUT_DEFAULT = 10;
    private static final String FREE_DELIVERY_MARK = "бесплатно";

    private By addToCartBtn = By.className("_3hWhO4rvmA");
    private By spinner = By.className("A2ZAPkIo1a");
    private By itemsPrice = By.cssSelector("*[data-auto='total-items'] > span:nth-child(2)");
    private By deliveryPrice = By.cssSelector("*[data-auto='total-delivery'] > span:nth-child(2)");
    private By discount = By.cssSelector("*[data-auto='total-discount'] > span:nth-child(2)");
    private By total = By.cssSelector("*[data-auto='total-price'] > span:nth-child(2)");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getAfterVisible(By by) {
        return (new WebDriverWait(instance, TIMEOUT_DEFAULT))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Step("Getting items' price")
    private int getItemsPrice() {
        return getPriceFromElement(getAfterVisible(itemsPrice));
    }

    @Step("Adding more toothbrushes")
    public void addTillExpectedPrice(int expectedPrice) {
        int startingPrice = getItemsPrice();
        Wait<WebDriver> wait = new WebDriverWait(instance, TIMEOUT_DEFAULT);
        while (startingPrice <= expectedPrice) {
            instance.findElement(addToCartBtn).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(spinner));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
            startingPrice = getItemsPrice();
        }
    }

    @Step("Getting delivery price")
    private int getDeliveryPrice() {
        return getPriceFromElement(getAfterVisible(deliveryPrice));
    }

    @Step("Getting discount")
    private int getDiscount() {
        try {
            return getPriceFromElement(getAfterVisible(discount));
        } catch (Exception error) {
            return 0;
        }
    }

    @Step("Checking if delivery is free")
    public boolean checkForFreeDelivery() {
        return getAfterVisible(deliveryPrice).getText().equalsIgnoreCase(FREE_DELIVERY_MARK);
    }

    @Step("Getting real total price")
    public int getTotal() {
        return getPriceFromElement(getAfterVisible(total));
    }

    @Step("Getting current total price")
    public int getCurrent() {
        return getItemsPrice() + getDeliveryPrice() - getDiscount();
    }
}
