package main;

import beru.CartPage;
import beru.CatalogSectionPage;
import beru.HomePage;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogTest extends BeruTest {
    private static final int MIN_PRICE = 999;
    private static final int MAX_PRICE = 1999;
    private static final int EXPECTED_COST = 2999;

    private static final String SECTION = "Красота и гигиена";
    private static final String SUBSECTION = "Электрические зубные щетки";

    @Test
    public void pickToothbrush() {
        HomePage homePage = new HomePage(ffDriver);
        authorize(homePage);
        homePage.openCatalog();
        homePage.openCatalogSubsection(SECTION, SUBSECTION);
        CatalogSectionPage catalogSectionPage = new CatalogSectionPage(ffDriver);
        catalogSectionPage.setPriceFilter(MIN_PRICE, MAX_PRICE);
        catalogSectionPage.scrollToTheEnd();
        ArrayList<Integer> prices = catalogSectionPage.getItemPrices();
        for (Integer price : prices) {
            assertThat(price >= MIN_PRICE && price <= MAX_PRICE).isTrue();
        }
        int toothbrushId = prices.size() - 2;
        catalogSectionPage.buyToothbrush(toothbrushId);
        homePage.openCartPage();
        CartPage cartPage = new CartPage(ffDriver);
        int currentPrice = cartPage.getCurrent();
        int realPrice = cartPage.getTotal();
        assertThat(currentPrice).isEqualTo(realPrice);
        cartPage.addTillExpectedPrice(EXPECTED_COST);
        currentPrice = cartPage.getCurrent();
        realPrice = cartPage.getTotal();
        assertThat(currentPrice).isEqualTo(realPrice);
        assertThat(cartPage.checkForFreeDelivery()).isTrue();
    }
}
