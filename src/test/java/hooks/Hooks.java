package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.ExcelUtility;
import utilities.GWD;

import java.time.Duration;

import static utilities.GWD.logger;

public class Hooks {
    @Before
    public void setUp() {
        GWD.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getIntProperty("implicit.wait")));
        GWD.getDriver().manage().window().maximize();
        logger.info("Scenario is running...");
    }

    @After
    public void tearDown(Scenario scenario) {
        ExcelUtility.writeToExcel("src/test/java/apachePOI/resource/CucumberTestResults.xlsx",
                scenario.getName() + " " + (scenario.isFailed() ? "Failed" : "Passed"));

        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) GWD.getDriver();
            final byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(bytes, "image/png", "screenshot name");
        }
        logger.info("Scenario has completed.");
        logger.info("Driver has been quit.");
        GWD.quitDriver();
    }
}