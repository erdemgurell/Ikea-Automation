package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/featureFiles"},
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber1.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:TestOutput/failed_scenario.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false,
        monochrome = false
)

public class TestRunnerAll extends AbstractTestNGCucumberTests {
}
