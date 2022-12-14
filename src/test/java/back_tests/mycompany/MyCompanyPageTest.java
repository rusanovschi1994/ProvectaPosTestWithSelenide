package back_tests.mycompany;

import org.junit.BeforeClass;
import org.junit.Test;
import pages.SignInPage;
import pages.back.mycompany.MyCompanyPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class MyCompanyPageTest {

    private MyCompanyPage myCompanyPage;
    private SignInPage signInPage;

    @BeforeClass
    public static void setUp(){

        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\selenium\\chromedriver.exe");
        baseUrl = "https://provectapos.com/app/#/login";
        browser = "chrome";
        timeout = 8000;
    }

    @Test
    public void checkMyCompanyInPresenceOfOptions(){

        signInPage = new SignInPage();
        myCompanyPage = new MyCompanyPage();

        signInPage
                .open()
                .typeUsername("rusanovschi1994@mail.ru")
                .typePassword("Micr0invest")
                .clickSignInButton();
        myCompanyPage
                .clickMyCompany();
        myCompanyPage
                .getMyCompanyOptions().shouldHave(size(5));
        myCompanyPage
                .getMyCompanyOptionByNumber(3).shouldHave(text("Settings"));
        myCompanyPage
                .getMyCompanyOptionByText("Branches")
                .shouldBe(visible);
    }
}
