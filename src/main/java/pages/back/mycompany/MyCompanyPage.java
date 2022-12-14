package pages.back.mycompany;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.*;

public class MyCompanyPage {

    private final By myCompanyDropDown = By.xpath("//li[@lititle=\"My company\"]");
    private final By myCompanyOptions = By.xpath("//li[@lititle=\"My company\"]/ul/li");
    private final By branchesOption = By.xpath("//li[@lititle=\"My company\"]/ul/li[@lititle=\"Branches\"]");
    private String myCompanyOption = "//li[@lititle=\"My company\"]/ul/li[@lititle=\"%s\"]";

    public MyCompanyPage open(){

        Selenide.open("/");
        return this;
    }

    public MyCompanyPage clickMyCompany(){

        $(myCompanyDropDown)
                .shouldBe(visible)
                .click();
        return this;
    }

    public BranchesPage clickBranches(){

        $(branchesOption)
                .shouldBe(visible)
                .click();
        return new BranchesPage();
    }

    public ElementsCollection getMyCompanyOptions(){

       return  $$(myCompanyOptions);
    }

    public SelenideElement getMyCompanyOptionByNumber(int number){

        return $$(myCompanyOptions).get(number - 1);
    }

    public SelenideElement getMyCompanyOptionByText(String name){

        return $(By.xpath(format(myCompanyOption, name)));
    }
}
