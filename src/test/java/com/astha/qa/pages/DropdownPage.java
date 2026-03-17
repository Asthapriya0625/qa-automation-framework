package com.astha.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DropdownPage — Page Object for dropdown/select element testing.
 * Target: https://the-internet.herokuapp.com/dropdown
 */
public class DropdownPage extends BasePage {

    private static final Logger log = LogManager.getLogger(DropdownPage.class);

    @FindBy(id = "dropdown")
    private WebElement dropdown;

    public DropdownPage open() {
        navigateTo(config.baseUrl() + "/dropdown");
        log.info("Opened Dropdown page");
        return this;
    }

    public DropdownPage selectByText(String visibleText) {
        log.info("Selecting option: '{}'", visibleText);
        new Select(dropdown).selectByVisibleText(visibleText);
        return this;
    }

    public DropdownPage selectByValue(String value) {
        log.info("Selecting by value: '{}'", value);
        new Select(dropdown).selectByValue(value);
        return this;
    }

    public String getSelectedOption() {
        return new Select(dropdown).getFirstSelectedOption().getText().trim();
    }

    public List<String> getAllOptions() {
        return new Select(dropdown).getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getOptionCount() {
        return new Select(dropdown).getOptions().size();
    }

    public boolean isOptionAvailable(String text) {
        return getAllOptions().stream().anyMatch(opt -> opt.equalsIgnoreCase(text));
    }
}
