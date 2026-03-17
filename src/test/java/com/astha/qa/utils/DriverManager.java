package com.astha.qa.utils;

import com.astha.qa.config.FrameworkConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * DriverManager — manages WebDriver lifecycle using ThreadLocal for parallel-safe execution.
 *
 * Thread-local ensures each test thread gets its own WebDriver instance,
 * making parallel test runs possible without conflicts.
 *
 * Usage:
 *   DriverManager.initDriver();          // in @Before hook
 *   WebDriver driver = DriverManager.getDriver();
 *   DriverManager.quitDriver();          // in @After hook
 */
public class DriverManager {

    private static final Logger log = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

    private DriverManager() {
        // Utility class — prevent instantiation
    }

    /**
     * Initializes WebDriver based on config or system property.
     * Supports: chrome, firefox, edge
     */
    public static void initDriver() {
        String browser = config.browser().trim().toLowerCase();
        boolean headless = config.headless();
        log.info("Initializing {} driver | headless={}", browser, headless);

        WebDriver driver;

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                if (headless) ffOptions.addArguments("--headless");
                driver = new FirefoxDriver(ffOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                }
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--disable-extensions");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.implicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.pageLoadTimeout()));

        driverThreadLocal.set(driver);
        log.info("Driver initialized successfully: {}", driver.getClass().getSimpleName());
    }

    /**
     * Returns the WebDriver instance for the current thread.
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Call DriverManager.initDriver() first.");
        }
        return driver;
    }

    /**
     * Quits the WebDriver and removes it from thread-local storage.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            log.info("Quitting WebDriver...");
            driver.quit();
            driverThreadLocal.remove();
            log.info("WebDriver quit successfully.");
        }
    }
}
