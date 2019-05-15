package main;

import beru.HomePage;
import beru.SettingsPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CitySelectTest extends BeruTest {
    @Test(dataProviderClass = Config.class, dataProvider = "city-name")
    public void selectCity(String cityName) {
        HomePage homePage = new HomePage(ffDriver);
        homePage.setCity(cityName);
        assertThat(homePage.getRegion()).isEqualToIgnoringCase(cityName);
        authorize(homePage);
        homePage.openSettingsPage();
        SettingsPage settingsPage = new SettingsPage(ffDriver);
        assertThat(settingsPage.getSettingsCity()).isEqualToIgnoringCase(homePage.getRegion());
    }
}
