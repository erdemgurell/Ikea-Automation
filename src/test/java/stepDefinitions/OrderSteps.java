package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.OrderPOM;
import utilities.ReusableMethods;

import java.util.List;

public class OrderSteps extends ReusableMethods {
    OrderPOM orderPOM = new OrderPOM();

    @Given("user searches for a product {string}")
    public void userSearchesForAProduct(String product) {
        refreshPage();
        sendKeysFunction(orderPOM.searchBar, product);
        enterKeyMultiplePress(1);
    }

    @When("user selects a product from the search results")
    public void userSelectsAProductFromTheSearchResults() {
        jsClick(orderPOM.firstProductButton);
    }

    @And("user adds the product to the cart")
    public void userAddsTheProductToTheCart() {
        jsClick(orderPOM.showCartButton);
    }

    @And("user adds an address for delivery")
    public void userAddsAnAddressForDelivery(DataTable dt) {
        jsClick(orderPOM.addAddressButton);

        List<List<String>> strLinkList = dt.asLists(String.class);

        for (int i = 0; i < strLinkList.size(); i++) {
            WebElement txtBox = orderPOM.getWebElement(strLinkList.get(i).get(0));
            orderPOM.sendKeysFunctionNoScroll(txtBox, strLinkList.get(i).get(1));
        }
        selectByIndex(orderPOM.province, 1);
        selectByIndex(orderPOM.district, 2);
        selectByIndex(orderPOM.neighborhood, 4);
        sendKeysFunction(orderPOM.addressTextBox, "Istanbul");
        sendKeysFunctionNoScroll(orderPOM.socialIDInput, "-----");
        jsClick(orderPOM.addButton);
    }

    @And("user proceeds to the checkout page and completes the purchase")
    public void userProceedsToTheCheckoutPageAndCompletesThePurchase() {
        jsClick(orderPOM.toAddressButton);
        jsClick(orderPOM.checkoutButton);
        sendKeysFunction(orderPOM.cardNameInput, "John Doe");
        sendKeysFunction(orderPOM.cardNumberInput, "5436453456876456");
        selectByIndex(orderPOM.cardMonth, 2);
        selectByIndex(orderPOM.cardYear, 3);
        sendKeysFunction(orderPOM.cvcInput, "327");
        jsClick(orderPOM.singlePaymentOption);
        jsClick(orderPOM.agreementCheckbox);
        jsClick(orderPOM.buyButton);
    }
}
