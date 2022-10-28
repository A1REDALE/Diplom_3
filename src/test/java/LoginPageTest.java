import extensions.DriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page_object_models.*;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
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
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void homePageSignInButtonCheck() {
        loginPage = new LoginPage(driver);
        homePage.clickSignInButton();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitForOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void accountButtonSighInCheck() {
        loginPage = new LoginPage(driver);
        homePage.clickAccountButton();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitForOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void regPageSignInLinkCheck() {
        loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(registrationPage.getUrl());
        registrationPage.waitRegButton();
        registrationPage.clickSignInLink();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitForOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void forgotPageSignInLinkCheck() {
        loginPage = new LoginPage(driver);
        PasswordPage forgotPasswordPage = new PasswordPage(driver);
        driver.get(forgotPasswordPage.getUrl());
        forgotPasswordPage.waitRestorePasswordButton();
        forgotPasswordPage.clickSignInLink();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitForOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }
}