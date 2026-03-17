package com.astha.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * AlertPage — Page Object for JavaScript alert/confirm/prompt testing.
 * Target: https://the-internet.herokuapp.com/javascript_alerts
 */
public class AlertPage extends BasePage {

    private static final Logger log = LogManager.getLogger(AlertPage.class);

    @FindBy(css = "button[onclick='jsAlert()']")
    private WebElement triggerAlertButton;

    @FindBy(css = "button[onclick='jsConfirm()']")
    private WebElement triggerConfirmButton;

    @FindBy(css = "button[onclick='jsPrompt()']")
    private WebElement triggerPromptButton;

    @FindBy(id = "result")
    private WebElement resultText;

    public AlertPage open() {
        navigateTo(config.baseUrl() + "/javascript_alerts");
        log.info("Opened JavaScript Alerts page");
        return this;
    }

    public AlertPage clickAlertButton() {
        click(triggerAlertButton);
        return this;
    }

    public AlertPage clickConfirmButton() {
        click(triggerConfirmButton);
        return this;
    }

    public AlertPage clickPromptButton() {
        click(triggerPromptButton);
        return this;
    }

    public AlertPage acceptAlert() {
        log.info("Accepting alert");
        acceptAlert();
        return this;
    }

    public AlertPage dismissAlert() {
        log.info("Dismissing alert/confirm");
        super.dismissAlert();
        return this;
    }

    public AlertPage typeInPromptAndAccept(String text) {
        log.info("Typing '{}' in prompt and accepting", text);
        var alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        return this;
    }

    public String getAlertText() {
        return super.getAlertText();
    }

    public String getResultText() {
        return getText(resultText);
    }
}
