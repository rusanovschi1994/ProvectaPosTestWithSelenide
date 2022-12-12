package back_test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.SignInPage;
import pages.back.DashboardPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class DashboardPageTest {

    private DashboardPage dashboardPage;
    private SignInPage signInPage;

    @BeforeClass
    public static void setUp(){

        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\selenium\\chromedriver.exe");
        baseUrl = "https://provectapos.com/app/#/dashboard";
        browser = "chrome";
        timeout = 6000;
    }

    @Test
    public void checkDashboardHeader(){

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
                .getHeaderByText("Dashboard")
                .shouldBe(visible);
    }
}
