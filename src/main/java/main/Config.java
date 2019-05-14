package main;

import org.testng.annotations.DataProvider;

public class Config {
    static final String GECKO_PATH = "/webdrivers/geckodriver/geckodriver";
    static final String HOSTNAME = "https://beru.ru";

    public static final String YALOGIN = "efishtest";
    public static final String YAPSSWD = "12qwasZX";

    @DataProvider(name = "city-name")
    public static Object[][] cityNames() {
        return new Object[][] {
                { "Хвалынск" },
                { "Москва" },
                { "Санкт-Петербург" }
        };
    }
}
