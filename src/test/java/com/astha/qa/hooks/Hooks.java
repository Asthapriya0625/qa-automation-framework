package com.astha.qa.hooks;

import com.astha.qa.utils.DriverManager;
import com.astha.qa.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hooks — Cucumber lifecycle hooks for browser setup/teardown and reporting.
 *
 * @Before  → runs before each scenario  (browser init)
 * @After   → runs after each scenario   (screenshot on fail, browser quit)
 *
 * Order matters when multiple hook classes exist — use 'order' param to control sequence.
 */
public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);

    /**
     * Initialize WebDriver before each scenario.
     * Tags can be used to skip browser for non-UI scenarios.
     */
    @Before(order = 1)
    public void setUp(Scenario scenario) {
        log.info("═══════════════════════════════════════════════════");
        log.info("Starting Scenario: [{}]", scenario.getName());
        log.info("Tags: {}", scenario.getSourceTagNames());
        log.info("═══════════════════════════════════════════════════");
        DriverManager.initDriver();
    }

    /**
     * After each scenario:
     *  1. Capture screenshot on failure and embed in report
     *  2. Quit WebDriver
     */
    @After(order = 1)
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                log.warn("Scenario FAILED: [{}] — capturing screenshot", scenario.getName());
                byte[] screenshot = ScreenshotUtils.takeScreenshotAsBytes();
                if (screenshot != null) {
                    scenario.attach(screenshot, "image/png", "Failure Screenshot - " + scenario.getName());
                }
            }
        } catch (Exception e) {
            log.error("Error during screenshot capture: {}", e.getMessage());
        } finally {
            DriverManager.quitDriver();
            log.info("Scenario [{}] — Status: {}", scenario.getName(), scenario.getStatus());
            log.info("═══════════════════════════════════════════════════");
        }
    }
}
