package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Locale;

public class GWD {

    public static Logger logger = LogManager.getLogger();
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static ThreadLocal<String> browserName = new ThreadLocal<>();

    public static WebDriver getDriver() {

        Locale.setDefault(new Locale("EN"));
        System.setProperty("user.language", "EN");

        if (browserName.get() == null) {
            browserName.set(ConfigReader.getProperty("browser"));
        }
        if (threadDriver.get() == null) {
            switch (browserName.get()) {
                case "firefox":
                    threadDriver.set(new FirefoxDriver());
                    break;
                case "edge":
                    threadDriver.set(new EdgeDriver());
                    break;
                case "safari":
                    threadDriver.set(new SafariDriver());
                    break;
                default:
                    threadDriver.set(new ChromeDriver());
            }
            // threadDriver.get().manage().window().setPosition(new Point(-1000, 0));
            threadDriver.get().manage().window().maximize();
            threadDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getIntProperty("pageLoadTimeout")));
        }
        return threadDriver.get();
    }

    public static void closeDriver() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (threadDriver.get() != null) {
            threadDriver.get().close();
            WebDriver driver = null;
            threadDriver.set(driver);
        }
    }

    public static void quitDriver() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            WebDriver driver = null;
            threadDriver.set(driver);
        }
    }
}
