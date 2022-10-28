import extensions.DriverFactory;
import page_object_models.*;
import org.junit.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationPageTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private final String name = "name";
    private String email, password;

    @Before
    public void setup() {
        driver = DriverFactory.getBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        driver.get(registrationPage.getUrl());
        driver.manage().window().maximize();
        registrationPage.waitRegButton();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @Description("Создаем аккаунт с паролем ,более 6 символов")
    public void checkRegistrationWithCorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(10);
        registrationPage.sendKeysName(name);
        registrationPage.sendKeysEmail(email);
        registrationPage.sendKeysPassword(password);
        registrationPage.clickRegButton();
        loginPage.waitSignInButton();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("Создаем аккаунт с паролем менее 6 символов")
    public void checkRegistrationWithIncorrectPassword() {
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(5);
        registrationPage.sendKeysName(name);
        registrationPage.sendKeysEmail(email);
        registrationPage.sendKeysPassword(password);
        registrationPage.clickRegButton();
        registrationPage.waitError();
        assertEquals("Некорректный пароль", registrationPage.getError());
    }
}