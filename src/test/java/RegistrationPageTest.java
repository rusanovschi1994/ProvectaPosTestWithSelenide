import org.junit.BeforeClass;
import org.junit.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class RegistrationPageTest {

    private RegistrationPage page;

    @BeforeClass
    public static void setUp(){

        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\selenium\\chromedriver.exe");
        baseUrl = "https://provectapos.com/app/#/register";
        browser = "chrome";
    }

    @Test
    public void registerWithEmptyCompanyField(){

        page = new RegistrationPage();
        page
                .open()
                .selectLanguage("English")
                .typeEmailAddress("test@test.test")
                .typePassword("test")
                .typeConfirmPassword("test")
                .clickRegistration();
        page.getError("Please enter your business name").shouldBe(visible);
        page.getErrors().shouldHave(size(1));
    }

    @Test
    public void typeInvalidEmail(){

        page = new RegistrationPage();
        page
                .open()
                .selectLanguage("English")
                .typeCompanyName("Test")
                .typeEmailAddress("test")
                .typePassword("test")
                .typeConfirmPassword("test")
                .typePromoCode("test")
                .clickRegistration();
        page.getError("Please enter a VALID email address").shouldBe(visible);
        page.getError("Please enter the same password as above").shouldNotBe(visible);
    }

    @Test
    public void typeInvalidConfirmPassword(){

        page = new RegistrationPage();
        page
                .open()
                .typeCompanyName("Test")
                .typeEmailAddress("test@gmail.com")
                .typePassword("1111")
                .typeConfirmPassword("1123")
                .clickRegistration();
        page.getError("Please enter the same password as above").shouldBe(visible);
    }

    @Test
    public void registerWithEmptyFields(){

        page = new RegistrationPage();
        page
                .open()
                .selectLanguage("English")
                .clickRegistration();
        page.getErrors().shouldHave(size(4));
        page.getErrorByNumber(3).shouldHave(text("Please enter your password"));
    }
}
