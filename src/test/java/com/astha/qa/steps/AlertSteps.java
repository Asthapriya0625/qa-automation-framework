package com.astha.qa.steps;

import com.astha.qa.pages.AlertPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class AlertSteps {

    private static final Logger log = LogManager.getLogger(AlertSteps.class);
    private final AlertPage alertPage = new AlertPage();

    @Given("the user is on the JavaScript alerts page")
    public void theUserIsOnAlertsPage() {
        alertPage.open();
    }

    @When("the user triggers a JS alert")
    public void theUserTriggersAlert() {
        log.info("Step: Trigger JS alert");
        alertPage.clickAlertButton();
    }

    @When("the user triggers a JS confirm")
    public void theUserTriggersConfirm() {
        log.info("Step: Trigger JS confirm");
        alertPage.clickConfirmButton();
    }

    @When("the user triggers a JS prompt")
    public void theUserTriggersPrompt() {
        log.info("Step: Trigger JS prompt");
        alertPage.clickPromptButton();
    }

    @When("the user accepts the alert")
    public void theUserAcceptsAlert() {
        log.info("Step: Accept alert");
        alertPage.clickAcceptAlert();
    }

    @When("the user dismisses the alert")
    public void theUserDismissesAlert() {
        log.info("Step: Dismiss alert");
        alertPage.clickDismissAlert();
    }

    @When("the user types {string} in the prompt and accepts")
    public void theUserTypesInPrompt(String text) {
        log.info("Step: Type '{}' in prompt", text);
        alertPage.typeInPromptAndAccept(text);
    }

    @Then("the result should show {string}")
    public void theResultShouldShow(String expectedText) {
        String actual = alertPage.getResultText();
        Assert.assertTrue(actual.contains(expectedText),
                "Expected result to contain '" + expectedText + "' but got: '" + actual + "'");
    }
}
