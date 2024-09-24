package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

public class RegisterPagePOM extends ReusableMethods {

    public RegisterPagePOM() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(xpath = "//a[@id='login']//span[1]")
    public WebElement login_registerButton;

    @FindBy(xpath = "(//a[contains(@class,'btn btn-primary')])[2]")
    public WebElement registerButton;

    @FindBy(id = "ctl00_ContentPlaceHolder1_txtFirstName")
    public WebElement firstNamePlaceholder;

    @FindBy(id = "ctl00_ContentPlaceHolder1_txtLastName")
    public WebElement lastNamePlaceholder;

    @FindBy(id = "ctl00_ContentPlaceHolder1_txtEmail")
    public WebElement emailPlaceholder;

    @FindBy(id = "ctl00_ContentPlaceHolder1_txtPhone")
    public WebElement phoneNumberPlaceholder;

    @FindBy(id = "ctl00_ContentPlaceHolder1_txtPassword")
    public WebElement passwordPlaceholder;

    @FindBy(css = "input[name='ctl00$ContentPlaceHolder1$txtPasswordAgain']")
    public WebElement confirmPasswordPlaceholder;

    @FindBy(xpath = "(//input[@class='required']/following-sibling::label)[1]")
    public WebElement accountTermsCheckBox;

    @FindBy(xpath = "//label[@for=\"ctl00_ContentPlaceHolder1_cbProfilePermit\"]")
    public WebElement profilePermitCheckBox;

    @FindBy(xpath = "//label[@for='ctl00_ContentPlaceHolder1_cbCommunicationPermission']")
    public WebElement communicationPermissionCheckBox;

    @FindBy(xpath = "//label[normalize-space(text())='SMS']")
    public WebElement smsSubscriptionCheckbox;

    @FindBy(xpath = "//label[normalize-space(text())='E-posta']")
    public WebElement emailSubscriptionCheckbox;

    @FindBy(css = "label[for='ctl00_ContentPlaceHolder1_cbPermissionToTransferAbroad']")
    public WebElement dataPermissionCheckbox;

    @FindBy(id = "ctl00_ContentPlaceHolder1_btnSubmitRegister")
    public WebElement saveButton;

    @FindBy(id = "//h1[@class='page-title text-center']")
    public WebElement successMessage;


    public WebElement getWebElement(String strElement) {
        switch (strElement) {
            case "First Name":
                return this.firstNamePlaceholder;
            case "Last Name":
                return this.lastNamePlaceholder;
            case "Email":
                return this.emailPlaceholder;
            case "Phone Number":
                return this.phoneNumberPlaceholder;
            case "Password":
                return this.passwordPlaceholder;
            case "Confirm Password":
                return this.confirmPasswordPlaceholder;
        }

        return null;
    }


}
