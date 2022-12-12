import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.RegistrationPage;
import pages.SignInPage;
import pages.back.DashboardPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class SignInPageTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;

    @BeforeClass
    public static void setUp(){

        Configuration.timeout = 6000;

        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\selenium\\chromedriver.exe");
        baseUrl = "https://provectapos.com/app/#/login";
        browser = "chrome";
    }

    @Test
    public void createAccountWithEmptyEmailAndPassword(){

        signInPage = new SignInPage();
        registrationPage = new RegistrationPage();
        signInPage
                .open()
                .clickCreateAccountButton()
                .typeCompanyName("Test")
                .clickRegistration();
        registrationPage
                .getErrors()
                .shouldHave(size(3));
    }

    @Test
    public void signInWithEmptyUsername(){

        signInPage = new SignInPage();
        signInPage
                .open()
                .selectLanguage("English")
                .typePassword("test")
                .setRememberMe(true)
                .clickSignInButton();
        signInPage.getError("Please enter username").shouldBe(visible);
        signInPage.getError("Please enter your password").shouldNotBe(visible);
    }

    @Test
    public void signInWithValidCreds(){

        signInPage = new SignInPage();
        dashboardPage = new DashboardPage();
        signInPage
                .open()
                .selectLanguage("English")
                .typeUsername("rusanovschi1994@mail.ru")
                .typePassword("Micr0invest")
                .setRememberMe(true)
                .clickSignInButton();
        dashboardPage
                .getHeaderList().shouldHave(size(2));
        dashboardPage
                .getHeaderByText("Dashboard")
                .shouldBe(visible);
        dashboardPage.clickLogout();
    }
}
