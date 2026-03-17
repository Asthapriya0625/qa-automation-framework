package com.astha.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * CheckboxPage — Page Object for checkbox interaction testing.
 *
 * Demonstrates multi-element handling, state validation,
 * and data-driven test patterns in the framework.
 *
 * Target: https://the-internet.herokuapp.com/checkboxes
 */
public class CheckboxPage extends BasePage {

    private static final Logger log = LogManager.getLogger(CheckboxPage.class);

    @FindBy(css = "#checkboxes input[type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(css = "#checkboxes")
    private WebElement checkboxContainer;

    public CheckboxPage open() {
        String url = config.baseUrl() + "/checkboxes";
        log.info("Opening Checkboxes page: {}", url);
        navigateTo(url);
        return this;
    }

    public int getTotalCheckboxCount() {
        return checkboxes.size();
    }

    public boolean isCheckboxChecked(int index) {
        validateIndex(index);
        return checkboxes.get(index).isSelected();
    }

    public CheckboxPage checkCheckbox(int index) {
        validateIndex(index);
        WebElement checkbox = checkboxes.get(index);
        if (!checkbox.isSelected()) {
            log.info("Checking checkbox at index {}", index);
            click(checkbox);
        } else {
            log.info("Checkbox at index {} is already checked", index);
        }
        return this;
    }

    public CheckboxPage uncheckCheckbox(int index) {
        validateIndex(index);
        WebElement checkbox = checkboxes.get(index);
        if (checkbox.isSelected()) {
            log.info("Unchecking checkbox at index {}", index);
            click(checkbox);
        } else {
            log.info("Checkbox at index {} is already unchecked", index);
        }
        return this;
    }

    public CheckboxPage checkAll() {
        log.info("Checking all checkboxes");
        checkboxes.forEach(cb -> { if (!cb.isSelected()) cb.click(); });
        return this;
    }

    public CheckboxPage uncheckAll() {
        log.info("Unchecking all checkboxes");
        checkboxes.forEach(cb -> { if (cb.isSelected()) cb.click(); });
        return this;
    }

    public long countChecked() {
        return checkboxes.stream().filter(WebElement::isSelected).count();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= checkboxes.size()) {
            throw new IndexOutOfBoundsException(
                "Checkbox index " + index + " is out of bounds. Total checkboxes: " + checkboxes.size());
        }
    }
}
