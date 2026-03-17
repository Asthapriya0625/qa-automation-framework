package com.astha.qa.pages;

import com.astha.qa.config.FrameworkConfig;
import com.astha.qa.utils.DriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage — parent class for all Page Objects.
 *
 * Centralizes reusable Selenium interactions (click, type, wait, etc.)
 * so page classes stay clean and focused on page-specific behavior.
 *
 * All page classes extend this class via: super(driver)
 */
public abstract class BasePage {

    private static final Logger log = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected FrameworkConfig config;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.config = ConfigFactory.create(FrameworkConfig.class);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.explicitWait()));
        PageFactory.initElements(driver, this);
    }

    // ─────────────────────────────────────────────
    // NAVIGATION
    // ─────────────────────────────────────────────

    public void navigateTo(String url) {
        log.info("Navigating to: {}", url);
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // ─────────────────────────────────────────────
    // ELEMENT INTERACTIONS
    // ─────────────────────────────────────────────

    public void click(WebElement element) {
        waitForClickable(element);
        log.debug("Clicking element: {}", element);
        element.click();
    }

    public void click(By locator) {
        click(waitForVisibility(locator));
    }

    public void type(WebElement element, String text) {
        waitForVisibility(element);
        log.debug("Typing '{}' into element", text);
        element.clear();
        element.sendKeys(text);
    }

    public void type(By locator, String text) {
        type(driver.findElement(locator), text);
    }

    public String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText().trim();
    }

    public String getText(By locator) {
        return getText(driver.findElement(locator));
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void selectByVisibleText(WebElement dropdown, String text) {
        new Select(dropdown).selectByVisibleText(text);
    }

    public void selectByValue(WebElement dropdown, String value) {
        new Select(dropdown).selectByValue(value);
    }

    // ─────────────────────────────────────────────
    // WAIT HELPERS
    // ─────────────────────────────────────────────

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForTextToBePresent(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean waitForUrlToContain(String partialUrl) {
        return wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    public void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    // ─────────────────────────────────────────────
    // JAVASCRIPT HELPERS
    // ─────────────────────────────────────────────

    public void jsClick(WebElement element) {
        log.debug("JS click on element");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // ─────────────────────────────────────────────
    // ALERT HANDLING
    // ─────────────────────────────────────────────

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
}
