package home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    private static final String GECKO_PATH = "/webdrivers/geckodriver/geckodriver.exe";

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver", projectPath + GECKO_PATH);
        WebDriver ffDriver = new FirefoxDriver();
        ffDriver.get("https://google.com");
    }
}
