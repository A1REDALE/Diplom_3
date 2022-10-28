import extensions.DriverFactory;
import page_object_models.*;
import org.junit.*;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class TransitionsTest {
    private WebDriver driver;
    private HomePage homePage;
    private final String email = "EStorchak@yandex.ru";
    private final String password = "123456";

    @Before
    public void setup() {
        driver = DriverFactory.getBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        driver.manage().window().maximize();
        homePage.waitForSignIn();
        homePage.clickSignInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitForOrder();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверяем переход в «Личный кабинет»")
    public void checkSwitchingToProfile() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        assertEquals(profilePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяем переход в «Конструктор»")
    public void checkSwitchToConstructor() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        homePage.clickConstructorLink();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяем переход на домашнюю страницу по клику на логотип Stellar Burgers")
    public void checkSwitchToHomePage() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        homePage.clickLogo();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяем кнопку «Выйти» в личном кабинете")
    public void checkExitButton() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        profilePage.clickExitLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitSignInButton();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }
}