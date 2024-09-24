package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

public class LoginPOM extends ReusableMethods {

    public LoginPOM() {
        PageFactory.initElements(GWD.getDriver(), this);
    }


    @FindBy(id = "txtEmail")
    public WebElement emailInputField;

    @FindBy(id = "txtPassword")
    public WebElement passwordInputField;

    @FindBy(css = "input[type='button']")
    public WebElement loginButton;

    @FindBy(css = "label[for='cbRememberMe']")
    public WebElement rememberMeButton;

    @FindBy(xpath = "//div[@class='page-header clearfix']//h1[1]")
    public WebElement myOrdersText;


    public WebElement getWebElement(String strElement) {
        switch (strElement) {
            case "Email":
                return this.emailInputField;
            case "Password":
                return this.passwordInputField;
            case "Login Button":
                return this.loginButton;
        }
        return null;
    }

}
