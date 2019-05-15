package beru;

import io.qameta.allure.Step;
import main.BeruPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CatalogSectionPage extends BeruPage {
    private static final int TIMEOUT_DEFAULT = 5;

    private By minPriceInput = By.id("glpricefrom");
    private By maxPriceInput = By.id("glpriceto");
    private By sectionContainer = By.className("n-snippet-list");
    private By showMoreBtn = By.linkText("ПОКАЗАТЬ ЕЩЁ");
    private By itemCard = By.className("grid-snippet");
    private By priceSpan = By.className("_1u3j_pk1db");
    private By addToCartBtn = By.className("_4qhIn2-ESi");
    private By disabledAddToCartBtn = By.linkText("В корзине");

    private Wait<WebDriver> wait;

    public CatalogSectionPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(instance, TIMEOUT_DEFAULT);
    }

    private void waitStalenessOf(WebElement container) {
        try {
            wait.until(ExpectedConditions.stalenessOf(container));
        } catch (TimeoutException ignored) {}
    }

    @Step("Scrolling section till the end")
    public void scrollToTheEnd() {
        try {
            while (true) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(showMoreBtn));
                instance.findElement(showMoreBtn).click();
            }
        } catch (TimeoutException ignored) {}
    }

    @Step("Setting price filter values")
    public void setPriceFilter(int minPrice, int maxPrice) {
        WebElement container = instance.findElement(sectionContainer);
        waitStalenessOf(container);
        instance.findElement(minPriceInput).sendKeys(String.valueOf(minPrice));
        instance.findElement(maxPriceInput).sendKeys(String.valueOf(maxPrice));
        instance.findElement(maxPriceInput).sendKeys(Keys.ENTER);
        waitStalenessOf(container);
    }

    @Step("Extracting items' prices")
    public ArrayList<Integer> getItemPrices() {
        ArrayList<Integer> prices = new ArrayList<Integer>();
        List<WebElement> items = instance.findElements(itemCard);
        for (WebElement item : items) {
            WebElement priceElement = item.findElement(priceSpan);
            prices.add(getPriceFromElement(priceElement));
        }
        return prices;
    }

    @Step("Buying toothbrush")
    public void buyToothbrush(int toothbrushId) {
        List<WebElement> items = instance.findElements(itemCard);
        WebElement expectedToothbrush = items.get(toothbrushId);
        expectedToothbrush.findElement(addToCartBtn).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(disabledAddToCartBtn));
        } catch (TimeoutException ignored) {}
        instance.findElement(disabledAddToCartBtn);
    }
}
