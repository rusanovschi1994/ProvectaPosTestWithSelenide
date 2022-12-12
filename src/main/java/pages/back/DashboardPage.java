package pages.back;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import pages.SignInPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.*;

public class DashboardPage {

    private String headerText ="//ol[@class=\"breadcrumb ng-scope\"]/li[text()=\"%s\"]";
    private final By headerList = By.xpath("//ol[@class=\"breadcrumb ng-scope\"]//li");
    private final By logoutButton = By.xpath("//a[@ng-click=\"logOut()\"]");

    public DashboardPage open(){

        Selenide.open();
        return this;
    }

    public SignInPage clickLogout(){

        $(logoutButton)
                .shouldBe(visible)
                .click();
        return new SignInPage();
    }

    public ElementsCollection getHeaderList(){

       return $$(headerList);
    }

    public SelenideElement getHeaderByText(String text){

        return $(By.xpath(format(headerText, text)));
    }
}
