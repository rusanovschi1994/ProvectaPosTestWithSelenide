package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.back.DashboardPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.*;

public class SignInPage {

    private final By usernameField = By.cssSelector("input#username");
    private final By passwordField = By.cssSelector("input#password");
    private final By signInButton = By.xpath("//button[@type=\"submit\"]");
    private final By registrationButton = By.xpath("//button[@type=\"submit\"]//preceding-sibling::div");
    private final By rememberMeCheckBox = By.xpath("//input[@type=\"checkbox\"]/parent::label");
    private final By forgotPasswordLink = By.xpath("//a[@href=\"#/forgotpassword\"]");
    private final By languageDropDown = By.xpath("//a[@class=\"dropdown-toggle\"]/parent::li");
    private String languageOption =
            "//a[@class=\"dropdown-toggle\"]/following-sibling::ul//a[normalize-space()=\"%s\"]";
    private String errorByText = "//em[text()=\"%s\"]";
    private final By errors = By.xpath("//em[@class=\"invalid ng-isolate-scope\"]");
    private final By headerText = By.xpath("//span[text()=\"Sign In\"]");

    public SignInPage open(){

        Selenide.open("/");
        return this;
    }

    public RegistrationPage clickCreateAccountButton(){

        $(registrationButton).click();
        return new RegistrationPage();
    }

    public SignInPage typeUsername(String username){

        $(usernameField)
                .shouldBe(visible)
                .setValue(username);
        return this;
    }

    public SignInPage typePassword(String password){

        $(passwordField)
                .setValue(password);
        return this;
    }

    public SignInPage setRememberMe(boolean value){

        SelenideElement element = $(rememberMeCheckBox);
        if(!element.isSelected() == value){
            element.click();
        }
        return this;
    }

    public SignInPage selectLanguage(String option){

        $(languageDropDown)
                .shouldBe(visible)
                .click();
        $(By.xpath(format(languageOption, option)))
                .click();
        return this;
    }

    public SignInPage getHeaderText(){

        $(headerText);
        return this;
    }

    public DashboardPage clickSignInButton(){

        $(signInButton).click();
        //temporary nothing return
        return new DashboardPage();
    }

    public ElementsCollection getErrors(){

        return $$(getErrors());
    }

    public SelenideElement getErrorByNumber(int number){

        return $$(errors).get(number - 1);
    }

    public SelenideElement getError(String message){

        return $(By.xpath(format(errorByText, message)));
    }
}
