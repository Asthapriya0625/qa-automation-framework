package com.astha.qa.steps;

import com.astha.qa.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * LoginSteps — Cucumber step definitions for login feature scenarios.
 *
 * Each method maps to a Gherkin step in login.feature.
 * Steps are reusable across multiple scenarios/features.
 */
public class LoginSteps {

    private static final Logger log = LogManager.getLogger(LoginSteps.class);
    private final LoginPage loginPage = new LoginPage();

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        log.info("Step: User navigates to login page");
        loginPage.open();
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Expected to be on login page but current URL is: " + loginPage.getCurrentUrl());
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersCredentials(String username, String password) {
        log.info("Step: User enters credentials for username: {}", username);
        loginPage.enterUsername(username).enterPassword(password);
    }

    @And("the user clicks the login button")
    public void theUserClicksLoginButton() {
        log.info("Step: User clicks login button");
        loginPage.clickLogin();
    }

    @Then("the user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        log.info("Step: Verifying successful login");
        Assert.assertTrue(loginPage.isSuccessMessageDisplayed(),
                "Success message was not displayed after login");
        Assert.assertTrue(loginPage.isLogoutLinkVisible(),
                "Logout link not visible — user may not be logged in");
    }

    @Then("the user should see a login error message")
    public void theUserShouldSeeAnErrorMessage() {
        log.info("Step: Verifying error message is displayed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message was not displayed for invalid credentials");
    }

    @Then("the error message should contain {string}")
    public void theErrorMessageShouldContain(String expectedText) {
        log.info("Step: Verifying error message contains '{}'", expectedText);
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedText),
                "Expected error message to contain '" + expectedText + "' but got: " + actualError);
    }

    @Then("the success message should contain {string}")
    public void theSuccessMessageShouldContain(String expectedText) {
        log.info("Step: Verifying success message contains '{}'", expectedText);
        String actualMessage = loginPage.getSuccessMessage();
        Assert.assertTrue(actualMessage.contains(expectedText),
                "Expected success message to contain '" + expectedText + "' but got: " + actualMessage);
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        log.info("Step: User clicks logout");
        loginPage.logout();
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToLoginPage() {
        log.info("Step: Verifying redirect to login page after logout");
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Expected to be redirected to login page after logout");
    }
}
