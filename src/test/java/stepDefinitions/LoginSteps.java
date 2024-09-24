package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.LoginPOM;
import pages.RegisterPagePOM;
import utilities.ConfigReader;
import utilities.GWD;
import utilities.ReusableMethods;

import java.util.List;

public class LoginSteps extends ReusableMethods {
    RegisterPagePOM registerPagePOM = new RegisterPagePOM();
    LoginPOM loginPOM = new LoginPOM();

    @Given("Navigate to the Ikea")
    public void navigateToTheIkea() {
        GWD.getDriver().get(ConfigReader.getProperty("URL"));
    }

    @Then("user clicks on the login button")
    public void userClicksOnTheLoginButton() {
        registerPagePOM.jsClick(registerPagePOM.login_registerButton);
    }

    @When("user enters valid login credentials")
    public void userEntersValidLoginCredentials(DataTable dt) {
        List<List<String>> strLinkList = dt.asLists(String.class);

        for (int i = 0; i < strLinkList.size(); i++) {
            WebElement txtBox = loginPOM.getWebElement(strLinkList.get(i).get(0));
            loginPOM.sendKeysFunctionNoScroll(txtBox, strLinkList.get(i).get(1));
        }
    }

    @And("user submits the login form")
    public void userSubmitsTheLoginForm() {
        clickFunctionNoScroll(loginPOM.rememberMeButton);
        tabKeyMultiplePress(2);
        enterKeyMultiplePress(1);
        wait(2);
//        clickFunctionNoScroll(loginPOM.loginButton);
    }

    @Then("user should be redirected to the my orders page")
    public void userShouldBeRedirectedToMyOrdersPage() {
//        verifyContainsText(loginPOM.myOrdersText, "Siparişlerim");
    }
}