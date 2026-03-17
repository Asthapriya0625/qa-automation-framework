package com.astha.qa.steps;

import com.astha.qa.pages.DropdownPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class DropdownSteps {

    private static final Logger log = LogManager.getLogger(DropdownSteps.class);
    private final DropdownPage dropdownPage = new DropdownPage();

    @Given("the user is on the dropdown page")
    public void theUserIsOnTheDropdownPage() {
        dropdownPage.open();
    }

    @When("the user selects {string} from the dropdown")
    public void theUserSelectsFromDropdown(String option) {
        log.info("Step: Select '{}' from dropdown", option);
        dropdownPage.selectByText(option);
    }

    @Then("the selected option should be {string}")
    public void theSelectedOptionShouldBe(String expected) {
        String actual = dropdownPage.getSelectedOption();
        Assert.assertEquals(actual, expected,
                "Expected selected option '" + expected + "' but got: '" + actual + "'");
    }

    @Then("the dropdown should contain {int} options")
    public void theDropdownShouldContainOptions(int expectedCount) {
        int actual = dropdownPage.getOptionCount();
        Assert.assertEquals(actual, expectedCount,
                "Expected " + expectedCount + " dropdown options but found: " + actual);
    }

    @Then("the dropdown should include option {string}")
    public void theDropdownShouldIncludeOption(String option) {
        Assert.assertTrue(dropdownPage.isOptionAvailable(option),
                "Option '" + option + "' was not found in the dropdown");
    }
}
