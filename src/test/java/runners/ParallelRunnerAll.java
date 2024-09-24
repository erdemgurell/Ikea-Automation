package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.GWD;

@CucumberOptions(
        features = {"src/test/java/featureFiles"},
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber1.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:TestOutput/failed_scenario.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false,
        monochrome = false
)

public class ParallelRunnerAll extends AbstractTestNGCucumberTests {
    @BeforeClass
    @Parameters("browserType")
    public void beforeClass(String browserName) {
        GWD.browserName.set(browserName);
    }
}
