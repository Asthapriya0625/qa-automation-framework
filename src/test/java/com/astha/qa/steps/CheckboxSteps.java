package com.astha.qa.steps;

import com.astha.qa.pages.CheckboxPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * CheckboxSteps — step definitions for checkbox feature scenarios.
 * Demonstrates multi-element interaction patterns.
 */
public class CheckboxSteps {

    private static final Logger log = LogManager.getLogger(CheckboxSteps.class);
    private final CheckboxPage checkboxPage = new CheckboxPage();

    @Given("the user is on the checkboxes page")
    public void theUserIsOnCheckboxesPage() {
        log.info("Step: Navigate to checkboxes page");
        checkboxPage.open();
    }

    @Then("the page should display {int} checkboxes")
    public void thePageShouldDisplayCheckboxes(int expectedCount) {
        log.info("Step: Verify checkbox count is {}", expectedCount);
        int actualCount = checkboxPage.getTotalCheckboxCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Expected " + expectedCount + " checkboxes but found: " + actualCount);
    }

    @When("the user checks checkbox number {int}")
    public void theUserChecksCheckbox(int index) {
        log.info("Step: Check checkbox at index {}", index - 1);
        checkboxPage.checkCheckbox(index - 1); // 1-based in Gherkin, 0-based in code
    }

    @When("the user unchecks checkbox number {int}")
    public void theUserUnchecksCheckbox(int index) {
        log.info("Step: Uncheck checkbox at index {}", index - 1);
        checkboxPage.uncheckCheckbox(index - 1);
    }

    @Then("checkbox number {int} should be checked")
    public void checkboxShouldBeChecked(int index) {
        log.info("Step: Verify checkbox {} is checked", index);
        Assert.assertTrue(checkboxPage.isCheckboxChecked(index - 1),
                "Checkbox " + index + " should be checked but it is not");
    }

    @Then("checkbox number {int} should be unchecked")
    public void checkboxShouldBeUnchecked(int index) {
        log.info("Step: Verify checkbox {} is unchecked", index);
        Assert.assertFalse(checkboxPage.isCheckboxChecked(index - 1),
                "Checkbox " + index + " should be unchecked but it is checked");
    }

    @When("the user checks all checkboxes")
    public void theUserChecksAllCheckboxes() {
        log.info("Step: Check all checkboxes");
        checkboxPage.checkAll();
    }

    @Then("all checkboxes should be checked")
    public void allCheckboxesShouldBeChecked() {
        log.info("Step: Verify all checkboxes are checked");
        long uncheckedCount = checkboxPage.countChecked();
        Assert.assertEquals(uncheckedCount, (long) checkboxPage.getTotalCheckboxCount(),
                "Not all checkboxes are checked");
    }
}
