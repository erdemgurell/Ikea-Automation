package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.RegisterPagePOM;
import utilities.ReusableMethods;

import java.util.List;

public class RegisterSteps extends ReusableMethods {

    RegisterPagePOM registerPagePOM = new RegisterPagePOM();


    @Given("user clicks on the registration button")
    public void userClicksOnRegistrationButton() {

        registerPagePOM.jsClick(registerPagePOM.login_registerButton);
        registerPagePOM.jsClick(registerPagePOM.registerButton);

    }

    @When("user enters valid registration details")
    public void userEntersValidRegistrationDetails(DataTable dt) {


        List<List<String>> strLinkList = dt.asLists(String.class);

        for (int i = 0; i < strLinkList.size(); i++) {
            // GET THE NECESSARY WEB ELEMENT
            WebElement txtBox = registerPagePOM.getWebElement(strLinkList.get(i).get(0));
            // SEND KEYS TO THAT ELEMENT
            registerPagePOM.sendKeysFunction(txtBox, strLinkList.get(i).get(1));
        }
    }

    @And("user accepts the terms and conditions")
    public void userAcceptsTheTermsAndConditions() {
        registerPagePOM.jsClick(registerPagePOM.accountTermsCheckBox);
        registerPagePOM.jsClick(registerPagePOM.profilePermitCheckBox);
    }

    @And("user accepts the commercial communication consent")
    public void UserAcceptsTheCommercialCommunicationConsent() {
        registerPagePOM.jsClick(registerPagePOM.communicationPermissionCheckBox);
    }

    @And("user selects SMS and E-Mail as the preferred communication method")
    public void userSelectsSMSAndEMailAsThePreferredCommunicationMethod() {
        registerPagePOM.jsClick(registerPagePOM.smsSubscriptionCheckbox);
        registerPagePOM.jsClick(registerPagePOM.emailSubscriptionCheckbox);
    }

    @And("user grants permission for data transfer abroad")
    public void userGrantsPermissionForDataTransferAbroad() {
        registerPagePOM.jsClick(registerPagePOM.dataPermissionCheckbox);

    }

    @And("user submits the registration form")
    public void userSubmitsTheRegistrationForm() {
        tabKeyMultiplePress(3);
        enterKeyMultiplePress(1);

        clickFunction(registerPagePOM.saveButton);

    }

    @Then("user should see a registration success message")
    public void userShouldSeeARegistrationSuccessMessage() {
        verifyContainsText(registerPagePOM.successMessage, "Aktivasyon e-postası adresinize gönderildi.");
    }


}
