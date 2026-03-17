package com.astha.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage — Page Object representing the login screen.
 *
 * Uses @FindBy annotations (PageFactory pattern) for element location.
 * Extends BasePage for common Selenium interactions.
 *
 * Target: https://the-internet.herokuapp.com/login
 */
public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    // ─── Locators ───────────────────────────────────────
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".flash.success")
    private WebElement successMessage;

    @FindBy(css = ".flash.error")
    private WebElement errorMessage;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    // ─── Actions ────────────────────────────────────────

    public LoginPage open() {
        String url = config.baseUrl() + "/login";
        log.info("Opening Login page: {}", url);
        navigateTo(url);
        return this;
    }

    public LoginPage enterUsername(String username) {
        log.info("Entering username: {}", username);
        type(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        log.info("Entering password: [PROTECTED]");
        type(passwordField, password);
        return this;
    }

    public LoginPage clickLogin() {
        log.info("Clicking login button");
        click(loginButton);
        return this;
    }

    /**
     * Full login action — combines username, password, and submit.
     */
    public LoginPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    public void logout() {
        log.info("Logging out");
        click(logoutLink);
    }

    // ─── Assertions / Verifications ─────────────────────

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isLogoutLinkVisible() {
        return isDisplayed(logoutLink);
    }

    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("/login");
    }
}
