package extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver getBrowser() {
        String browserName = System.getProperty("browser");
        switch (browserName) {
            case "yandex":
                WebDriverManager.chromedriver().driverVersion("104.0.5112.20").setup();
                return new ChromeDriver(new ChromeOptions().setBinary("C:\\Users\\AIREDALE\\AppData\\Local" +
                        "\\Yandex\\YandexBrowser\\Application\\browser.exe"));
            default:
                return new ChromeDriver();
        }
    }
}
