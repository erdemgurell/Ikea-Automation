package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ReusableMethods {

    public WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(ConfigReader.getIntProperty("explicit.wait")));

    /**
     * This function is used to perform a click action on a given web element.
     * It waits until the element is clickable, scrolls to the element, and then clicks it.
     *
     * @param element The web element on which the click action will be performed.
     *                This element should be visible and clickable.
     */
    public void clickFunction(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        element.click();
    }
    public void clickFunctionNoScroll(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * This function is used to perform a click action on a given web element.
     * If the element is initially not clickable, it will attempt to scroll to the element and click it.
     * If the initial click attempt fails, it will use JavaScript to click the element.
     *
     * @param element The web element on which the click action will be performed.
     *                This element should be visible and clickable.
     * @throws Exception If the element is not clickable after both attempts.
     */
    public void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            scrollToElement(element);
            element.click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * This function is used to send keys to a given web element.
     * It waits until the element is visible, scrolls to the element, clears any existing text,
     * and then sends the specified text to the element.
     *
     * @param element The web element to which the keys will be sent.
     *                This element should be visible.
     * @param text    The text to be sent to the element.
     */
    public void sendKeysFunction(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }
    public void sendKeysFunctionNoScroll(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * This function is used to send keys to a given web element using JavaScript.
     * It executes a JavaScript snippet that sets the value of the element to the specified text.
     *
     * @param element The web element to which the keys will be sent.
     *                This element should be visible.
     * @param text    The text to be sent to the element.
     * @return void This function does not return any value.
     */
    public void sendKeysJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].value='" + text + "'", element);
    }

    /**
     * This function is used to scroll the web page to the specified web element.
     * It uses JavaScript execution to achieve this.
     *
     * @param element The web element to which the page should be scrolled.
     *                This element should be visible on the page.
     * @return void This function does not return any value.
     */
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * This function is used to scroll the web page to the top.
     * It uses JavaScript execution to achieve this.
     * The function does not take any parameters and does not return any value.
     * It executes a JavaScript snippet that scrolls the page to the top.
     */
    public static void scrollToHome() {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    /**
     * This function is used to scroll the web page to the bottom.
     * It uses JavaScript execution to achieve this.
     * The function does not take any parameters and does not return any value.
     * It executes a JavaScript snippet that scrolls the page to the bottom of the document body.
     */
    public static void scrollToEnd() {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    /**
     * This function is used to retrieve the text content of a given web element using JavaScript.
     * It waits until the element is visible, executes a JavaScript snippet to retrieve the text content,
     * and then returns the retrieved text.
     *
     * @param element The web element from which the text content will be retrieved.
     *                This element should be visible.
     * @return A string representing the text content of the given web element.
     */
    public String jsGetText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        return (String) (js.executeScript("arguments[0].textContent;", element));
    }

    /**
     * This function is used to perform a click action on a given web element using JavaScript.
     * It waits until the element is clickable, then executes a JavaScript snippet to click the element.
     *
     * @param element The web element on which the click action will be performed.
     *                This element should be visible and clickable.
     * @return void This function does not return any value.
     */
    public void jsClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * This function is used to set the value of a given web element's attribute using JavaScript.
     * It executes a JavaScript snippet that sets the 'value' attribute of the specified web element to the given text.
     *
     * @param element The web element for which the attribute value will be set.
     *                This element should be visible.
     * @param text    The text value to be set for the 'value' attribute of the given web element.
     * @return void This function does not return any value.
     */
    public static void sendAttributeJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
    }

    /**
     * Retrieves the value of a specified attribute for a given HTML element identified by its ID.
     *
     * @param id            The unique identifier of the HTML element.
     * @param attributeName The name of the attribute whose value needs to be retrieved.
     * @return A string representing the value of the specified attribute.
     * @throws NullPointerException If the specified ID or attribute name is null.
     * @throws ClassCastException   If the retrieved value is not a string.
     */
    public static void getValueByJS(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        String attributeValue = (String) js.executeScript("return document.getElementById('" + id + "')." + attributeName);
        System.out.println("Attribute Value: " + attributeValue);
    }

    /**
     * This function is used to clear the value of a given web element using JavaScript.
     *
     * @param element The web element whose value needs to be cleared.
     *                This element should be visible.
     * @return void This function does not return any value.
     */
    public static void clearByJs(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) GWD.getDriver();
        jse.executeScript("arguments[0].value = '';", element);
    }

    /**
     * Highlights the specified web element by adding a red border around it.
     * This function uses JavaScript execution to achieve the highlighting effect.
     *
     * @param element The web element to be highlighted.
     *                This element should be visible on the page.
     * @return void This function does not return any value.
     */
    public static void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**
     * Verifies if the given web element contains the specified text.
     *
     * @param element The web element to be checked.
     *                This element should be visible.
     * @param value   The text to be verified.
     * @return void This function does not return any value.
     * @throws AssertionError If the element's text does not contain the specified value.
     */
    public void verifyContainsText(WebElement element, String value) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, value));
        Assert.assertTrue(element.getText().toLowerCase().contains(value.toLowerCase()));

        new Actions(GWD.getDriver()).sendKeys(Keys.ESCAPE).build().perform();
    }

    /**
     * Verifies if the given web element contains the specified text.
     *
     * @param element The web element to be checked.
     *                This element should be visible on the page.
     * @param text    The text to be verified.
     * @throws AssertionError If the element's text does not contain the specified value.
     *                        The error message will include the text not found and the actual element text.
     */
    public static void checkTextContains(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(ConfigReader.getIntProperty("explicit.wait")));
        WebElement elementName = wait.until(ExpectedConditions.visibilityOf(element));
        if (elementName.getText().trim().toLowerCase().contains(text.trim().toLowerCase())) {
            Assert.assertTrue(true);
        } else
            Assert.fail(text + " not found! Element text :" + elementName.getText());
    }

    /**
     * Selects an option from a dropdown menu by its index.
     *
     * @param element The dropdown menu web element.
     *                This element should be visible on the page.
     * @param num     The index of the option to be selected.
     *                The index is zero-based, meaning the first option has an index of 0.
     * @return void This function does not return any value.
     */
    public void selectByIndex(WebElement element, int num) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select menu = new Select(element);
        menu.selectByIndex(num);
    }

    /**
     * Selects an option from a dropdown menu by its visible text.
     *
     * @param element The dropdown menu web element.
     *                This element should be visible on the page.
     * @param text    The visible text of the option to be selected.
     * @return void This function does not return any value.
     */
    public void selectByText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * Selects an option from a dropdown menu by its value.
     *
     * @param element The dropdown menu web element.
     *                This element should be visible on the page.
     * @param value   The value of the option to be selected.
     * @return void This function does not return any value.
     */
    public void selectByValue(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByValue(value);
    }

    /**
     * Performs a mouse hover action over the specified web element.
     *
     * @param element The web element over which the mouse hover action will be performed.
     *                This element should be visible on the page.
     * @return void This function does not return any value.
     */
    public void hoverOver(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Actions(GWD.getDriver()).moveToElement(element).build().perform();
    }

    /**
     * This function simulates the process of uploading a file to a web page.
     * It uses a Robot object to simulate keyboard and mouse actions to mimic the file upload process.
     *
     * @param pathFile The absolute or relative path to the file that needs to be uploaded.
     * @throws RuntimeException If an AWTException occurs while creating a Robot object.
     */
    public void uploadFileFunction(String pathFile) {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        // Create a StringSelection object with the path of the file to be uploaded
        StringSelection createPathFile = new StringSelection(pathFile);

        // Set the contents of the system clipboard to the path of the file
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(createPathFile, null);

        wait(1);

        // Simulate pressing the Control key and the V key to paste the file path into the file upload field
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Release the Control and V keys
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        wait(1);

        // Simulate pressing the Enter key to confirm the file upload
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        wait(1);
    }

    /**
     * Simulates pressing the Tab key multiple times using a Robot object.
     *
     * @param quantity The number of times the Tab key should be pressed.
     * @throws RuntimeException If an AWTException occurs while creating a Robot object.
     */
    public static void tabKeyMultiplePress(int quantity) {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < quantity; i++) {
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

    /**
     * Simulates pressing the Enter key multiple times using a Robot object.
     *
     * @param quantity The number of times the Enter key should be pressed.
     * @throws RuntimeException If an AWTException occurs while creating a Robot object.
     */
    public static void enterKeyMultiplePress(int quantity) {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < quantity; i++) {
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }

    /**
     * Simulates pressing the left mouse button multiple times using a Robot object.
     *
     * @param quantity The number of times the left mouse button should be pressed.
     * @throws RuntimeException If an AWTException occurs while creating a Robot object.
     */
    public static void leftClickMultiplePress(int quantity) {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < quantity; i++) {
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(1000);
        }
    }

    /**
     * Generates a random integer within the specified range.
     *
     * @param range The upper limit (exclusive) of the range.
     * @return An integer randomly selected within the range [0, range).
     */
    public static int randomGenerator(int range) {
        return (int) (Math.random() * range);
    }

    /**
     * Waits for the specified web element to become visible on the web page.
     *
     * @param element The web element to wait for.
     * @return void This function does not return any value.
     */
    public void waitUntilVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the specified web element to become clickable on the web page.
     *
     * @param element The web element to wait for.
     * @return void This function does not return any value.
     */
    public void waitUntilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the page title to match the specified title.
     *
     * @param title The expected title of the page.
     * @throws TimeoutException If the expected title does not match the actual title within the specified wait time.
     */
    public static void waitForPageTitle(String title) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(ConfigReader.getIntProperty("explicit.wait")));
        wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * Retrieves the value of a specified attribute from a given web element.
     *
     * @param element   The web element from which to retrieve the attribute value.
     * @param attribute The name of the attribute whose value needs to be retrieved.
     * @return A string representing the value of the specified attribute.
     * If the attribute does not exist or is not set, the method returns null.
     */
    public static String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * Switches the WebDriver's focus to the parent frame of the current frame.
     * This method is useful when working with frames in a web page.
     *
     * @return void This function does not return any value.
     */
    public static void switchToParentFrame() {
        GWD.getDriver().switchTo().parentFrame();
    }

    /**
     * Switches the WebDriver's focus to a specific frame based on the given index.
     *
     * @param index The index of the frame to switch to. The index is zero-based.
     * @return void This function does not return any value.
     * @throws TimeoutException If the frame with the specified index is not available within the specified wait time.
     */
    public void iframeSwitchByIndex(int index) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    /**
     * Switches the WebDriver's focus to a frame based on the given name or ID.
     *
     * @param nameOrId The name or ID of the frame to switch to.
     * @return void This function does not return any value.
     * @throws TimeoutException If the frame with the specified name or ID is not available within the specified wait time.
     */
    public void iframeSwitchByNameOrId(String nameOrId) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
    }

    /**
     * Switches the WebDriver's focus to a frame based on the given WebElement.
     *
     * @param iframeElement The WebElement representing the frame to switch to.
     * @return void This function does not return any value.
     * @throws TimeoutException If the frame with the specified WebElement is not available within the specified wait time.
     */
    public void iframeSwitchByElement(WebElement iframeElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeElement));
    }

    /**
     * Switches the WebDriver's focus to the default content, which is the main document of the web page.
     *
     * @return void This function does not return any value.
     */
    public static void switchToDefaultContent() {
        GWD.getDriver().switchTo().defaultContent();
    }

    /**
     * Clears all the cookies stored in the current WebDriver's session.
     *
     * @return void This function does not return any value.
     */
    public static void clearCookies() {
        GWD.getDriver().manage().deleteAllCookies();
    }

    /**
     * This function is used to accept all cookies on a web page.
     * It finds all the buttons with the text 'Accept' and clicks the first one found.
     *
     * @return void This function does not return any value.
     */
    public static void acceptAllCookies() {
        List<WebElement> acceptButtons = GWD.getDriver().findElements(By.xpath("//button[contains(text(),'Accept')]"));
        if (!acceptButtons.isEmpty()) {
            acceptButtons.get(0).click();
        }
    }

    /**
     * This function checks if a list of WebElements contains a specific string.
     *
     * @param list   The list of WebElements to be checked.
     * @param search The string to be searched for in the list.
     * @return A boolean indicating whether the list contains the specified string.
     * The function returns true if the list contains the string, and false otherwise.
     */
    public static boolean listContainsString(List<WebElement> list, String search) {
        boolean isFound = false;
        for (WebElement e : list) {
            if (e.getText().equalsIgnoreCase(search))
                isFound = true;
        }
        return isFound;
    }

    /**
     * Compares two lists of WebElements and Strings.
     *
     * @param list1 The first list of WebElements to be compared.
     * @param list2 The second list of Strings to be compared.
     * @return A boolean indicating whether the lists are equal.
     * The function returns true if all corresponding elements in the lists are equal,
     * and false otherwise.
     * @throws IndexOutOfBoundsException If the lists are of different sizes.
     */
    public static boolean compareLists(List<WebElement> list1, List<String> list2) {
        // Check if the lists are of equal size.
        if (list1.size() != list2.size()) {
            return false;
        }

        // Iterate through the elements of both lists and compare them.
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).getText().equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Waits for a specified number of seconds.
     *
     * @param sn The number of seconds to wait.
     * @throws RuntimeException If the thread is interrupted while waiting.
     */
    public static void wait(int sn) {
        try {
            Thread.sleep(sn * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Captures a screenshot of the current web page and saves it to a specified file.
     *
     * @param fileName The name of the file where the screenshot will be saved.
     *                 The file will be saved in the "screenshots" directory.
     * @throws IOException If an error occurs while copying the screenshot file.
     */
    public static void captureScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) GWD.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects a radio button in a list of radio buttons based on the given value.
     *
     * @param radioButtons A list of WebElements representing the radio buttons.
     * @param value        The value of the radio button to be selected.
     * @return void This function does not return any value.
     */
    public static void selectRadioButtonByValue(List<WebElement> radioButtons, String value) {
        for (WebElement radioButton : radioButtons) {
            if (radioButton.getAttribute("value").equalsIgnoreCase(value)) {
                if (!radioButton.isSelected()) {
                    radioButton.click();
                }
                break;
            }
        }
    }

    /**
     * Accepts the currently displayed alert box.
     *
     * @param timeout The maximum time to wait for the alert to be present.
     * @return void This function does not return any value.
     */
    public static void alertAccept(int timeout) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.alertIsPresent());
        GWD.getDriver().switchTo().alert().accept();
    }

    /**
     * Dismisses the currently displayed alert box.
     *
     * @return void This function does not return any value.
     */
    public static void alertDismiss() {
        GWD.getDriver().switchTo().alert().dismiss();
    }

    /**
     * Waits for a specified number of seconds for an alert box to be present.
     *
     * @param timeout The maximum time to wait for the alert to be present.
     * @return void This function does not return any value.
     */
    public static void alertWait(int timeout) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Retrieves the text from the currently displayed alert box.
     *
     * @return A string containing the text from the alert box.
     * The method returns an empty string if no alert box is present.
     */
    public static void alertText() {
        GWD.getDriver().switchTo().alert().getText();
    }

    /**
     * Accepts the currently displayed alert box by sending the specified text.
     *
     * @param text The text to be sent to the alert box.
     *             This text will be displayed in the alert box when it is accepted.
     * @return void This function does not return any value.
     */
    public static void alertPromptBox(String text) {
        GWD.getDriver().switchTo().alert().sendKeys(text);
    }

    /**
     * Refreshes the current web page by reloading the content from the server.
     *
     * @return void This function does not return any value.
     */
    public static void refreshPage() {
        GWD.getDriver().navigate().refresh();
    }

    /**
     * Retrieves the current URL of the web page that the WebDriver is currently interacting with.
     *
     * @return A string representing the current URL of the web page.
     * The returned URL is the one that the WebDriver is currently viewing.
     */
    public static String getCurrentURL() {
        return GWD.getDriver().getCurrentUrl();
    }

    /**
     * Switches the focus to a specific window based on the given index.
     *
     * @param num The index of the window to switch to. The index is zero-based.
     * @return void This function does not return any value.
     */
    public static void switchToWindow(int num) {
        List<String> windowHandlesAll = new ArrayList<String>(GWD.getDriver().getWindowHandles());
        GWD.getDriver().switchTo().window(windowHandlesAll.get(num));
    }

    /**
     * Switches the focus to a specific window based on the given index.
     *
     * @param num The index of the window to switch to. The index is zero-based.
     * @return void This function does not return any value.
     */
    public static void switchToWindow2(int num) {
        GWD.getDriver().switchTo().window(GWD.getDriver().getWindowHandles().toArray()[num].toString());
    }

    /**
     * Waits for the number of open browser windows to equal the specified number.
     *
     * @param numberOfWindows The expected number of open browser windows.
     * @return void This function does not return any value.
     * @throws TimeoutException If the number of open windows does not match the expected number within the specified wait time.
     */
    public static void waitForNumberOfWindowsToEqual(int numberOfWindows) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(ConfigReader.getIntProperty("explicit.wait")));
        wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }
}
