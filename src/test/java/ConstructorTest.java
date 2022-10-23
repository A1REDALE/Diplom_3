import org.openqa.selenium.chrome.ChromeDriver;
import page_object_models.HomePage;
import org.junit.*;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    private HomePage homePage;


    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        driver.manage().window().maximize();
        homePage.waitForSignIn();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверяем что работает переход к разделу «Булки»")
    public void checkSwitchToBunsTab() {
        homePage.clickBuns();
        assertEquals("Булки", homePage.getTextActiveTab());
    }

    @Test
    @DisplayName("Проверяем что работает переход к разделу «Соусы»")
    public void checkSwitchToSaucesTab() {
        homePage.clickSauces();
        assertEquals("Соусы", homePage.getTextActiveTab());
    }

    @Test
    @DisplayName("Проверяем что работает переход к разделу «Начинки»")
    public void checkSwitchToFillingsTab() {
        homePage.clickFillings();
        assertEquals("Начинки", homePage.getTextActiveTab());
    }
}