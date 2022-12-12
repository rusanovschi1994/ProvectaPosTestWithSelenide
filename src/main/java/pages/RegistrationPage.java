package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.*;

public class RegistrationPage {

    private final By companyNameField = By.xpath("//input[@name=\"companyname\"]");
    private final By emailField = By.xpath("//input[@name=\"email\"]");
    private final By passwordField = By.cssSelector("input#password");
    private final By confirmPasswordField = By.xpath("//input[@name=\"passwordConfirm\"]");
    private final By promoCodeField = By.cssSelector("input#promocode");
    private final By registrationButton = By.xpath("//button[@type=\"submit\"]");
    private final By languageDropDown = By.xpath("//a[@class=\"dropdown-toggle\"]/parent::li");
    private String languageOption =
            "//a[@class=\"dropdown-toggle\"]/following-sibling::ul//a[normalize-space()=\"%s\"]";
    private String errorByText = "//em[text()=\"%s\"]";
    private final By errors = By.xpath("//em[@for]");

    public RegistrationPage open(){

        Selenide.open("/");
        return this;
    }

    public RegistrationPage typeCompanyName(String name){

        $(companyNameField)
                .shouldBe(visible)
                .setValue(name);
        return this;
    }

    public RegistrationPage typeEmailAddress(String email){

        $(emailField).setValue(email);
        return this;
    }

    public RegistrationPage typePassword(String password){

        $(passwordField).setValue(password);
        return this;
    }

    public RegistrationPage typeConfirmPassword(String password){

        $(confirmPasswordField).setValue(password);
        return this;
    }

    public RegistrationPage typePromoCode(String code){

        $(promoCodeField).setValue(code);
        return this;
    }

    public RegistrationPage selectLanguage(String option){

        $(languageDropDown).click();
        $(By.xpath(format(languageOption, option))).click();
        return this;
    }

    public void clickRegistration(){

        $(registrationButton).click();
    }

    public ElementsCollection getErrors(){

        return $$(errors);
    }

    public SelenideElement getErrorByNumber(int number){

        return $$(errors).get(number - 1);
    }

    public SelenideElement getError(String text){

        return $(By.xpath(format(errorByText, text)));
    }
}
