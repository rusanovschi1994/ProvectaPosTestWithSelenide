import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.RegistrationPage;
import pages.SignInPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class SignInPageTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;

    @BeforeClass
    public static void setUp(){

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
        registrationPage.getErrors().shouldHave(size(3));
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
}
