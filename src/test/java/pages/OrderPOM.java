package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

public class OrderPOM extends ReusableMethods {
    public OrderPOM() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(css = "input[type='search']")
    public WebElement searchBar;

    @FindBy(xpath = "(//div[@class='product-item-buttons']//span)[1]")
    public WebElement firstProductButton;

    @FindBy(className = "shopping-basket")
    public WebElement shoppingCartButton;

    @FindBy(xpath = "//a[normalize-space(text())='Sepete Git']")
    public WebElement goToCartButton;

    @FindBy(xpath = "//a[normalize-space(text())='Göster']")
    public WebElement showCartButton;

    @FindBy(xpath = "(//div[contains(@class,'box big')]//a)[1]")
    public WebElement addAddressButton;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ctrlAddressEditForm_txtEditEmail")
    public WebElement emailInput;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ctrlAddressEditForm_txtEditFirstName")
    public WebElement firstNameInput;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ctrlAddressEditForm_txtEditLastName")
    public WebElement lastNameInput;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ctrlAddressEditForm_txtEditPhone")
    public WebElement phoneNumberInput;

    @FindBy(xpath = "(//div[@class='form-group']//select)[1]")
    public WebElement province;

    @FindBy(name = "ctl00$ContentPlaceHolder1$ctrlAddressEditForm$ddlTowns")
    public WebElement district;

    @FindBy(name = "ctl00$ContentPlaceHolder1$ctrlAddressEditForm$ddlDistricts")
    public WebElement neighborhood;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ctrlAddressEditForm_txtEditDetail")
    public WebElement addressTextBox;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ctrlAddressEditForm_txtEditTCKNo")
    public WebElement socialIDInput;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ctrlAddressEditForm_btnSubmit")
    public WebElement addButton;

    @FindBy(xpath = "//label[@for='ctl00_ContentPlaceHolder1_homeDelivery']")
    public WebElement toAddressButton;

    @FindBy(id = "ctl00_ContentPlaceHolder1_btnContinue")
    public WebElement checkoutButton;

    @FindBy(css = "input[name='name']")
    public WebElement cardNameInput;

    @FindBy(xpath = "(//input[@type='tel'])[1]")
    public WebElement cardNumberInput;

    @FindBy(xpath = "(//select[contains(@class,'select2 required')])[1]")
    public WebElement cardMonth;

    @FindBy(xpath = "//select[@data-select2-id='expiryDate02']")
    public WebElement cardYear;

    @FindBy(id = "cvc")
    public WebElement cvcInput;

    @FindBy(xpath = "//label[@for='instalement0']")
    public WebElement singlePaymentOption;

    @FindBy(xpath = "(//label[@for='readall'])[1]")
    public WebElement agreementCheckbox;

    @FindBy(xpath = "(//a[contains(@class,'btn btn-primary')])[3]")
    public WebElement buyButton;

    public WebElement getWebElement(String strElement) {
        switch (strElement) {
            case "First Name":
                return this.firstNameInput;
            case "Last Name":
                return this.lastNameInput;
            case "Email":
                return this.emailInput;
            case "Phone Number":
                return this.phoneNumberInput;
        }
        return null;
    }
}