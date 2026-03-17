package com.astha.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ScreenshotUtils — captures screenshots for failure reporting.
 *
 * Screenshots are:
 *   1. Embedded directly into Cucumber HTML report (as byte[])
 *   2. Optionally saved to disk under reports/screenshots/
 */
public class ScreenshotUtils {

    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOTS_DIR = "reports/screenshots";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {}

    /**
     * Returns screenshot as byte array — used for embedding in Cucumber report.
     */
    public static byte[] takeScreenshotAsBytes() {
        try {
            WebDriver driver = DriverManager.getDriver();
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Saves screenshot to disk and returns the file path.
     * Useful for CI artifact archiving.
     */
    public static String saveScreenshot(String scenarioName) {
        try {
            WebDriver driver = DriverManager.getDriver();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(FORMATTER);
            String safeName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_");
            String fileName = safeName + "_" + timestamp + ".png";

            Path dir = Paths.get(SCREENSHOTS_DIR);
            Files.createDirectories(dir);
            Path destination = dir.resolve(fileName);
            Files.copy(srcFile.toPath(), destination);

            log.info("Screenshot saved: {}", destination.toAbsolutePath());
            return destination.toString();
        } catch (IOException e) {
            log.error("Failed to save screenshot: {}", e.getMessage());
            return null;
        }
    }
}
