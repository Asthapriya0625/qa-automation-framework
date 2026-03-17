package com.astha.qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner — main Cucumber + TestNG entry point.
 *
 * CucumberOptions breakdown:
 *   features   → path to .feature files
 *   glue       → packages where step definitions and hooks are found
 *   tags       → filter scenarios by tag (overridable via CLI: -Dcucumber.filter.tags="@smoke")
 *   plugin     → report formatters
 *   monochrome → cleaner console output
 *
 * Run options:
 *   All tests:         mvn test
 *   Smoke only:        mvn test -Dcucumber.filter.tags="@smoke"
 *   Regression only:   mvn test -Dcucumber.filter.tags="@regression"
 *   Specific feature:  mvn test -Dcucumber.filter.tags="@login"
 *   Headless Chrome:   mvn test -Dheadless=true
 *   Firefox:           mvn test -Dbrowser=firefox
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.astha.qa.steps",
                "com.astha.qa.hooks"
        },
        tags = "@regression or @smoke",
        plugin = {
                "pretty",
                "html:reports/cucumber/cucumber-report.html",
                "json:reports/cucumber/cucumber-report.json",
                "junit:reports/cucumber/cucumber-report.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Override DataProvider to enable parallel scenario execution.
     * Set parallel=true and adjust thread count in testng.xml.
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
