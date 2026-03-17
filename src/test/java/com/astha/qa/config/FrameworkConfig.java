package com.astha.qa.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

/**
 * Framework configuration interface.
 * Values are loaded from config/framework.properties
 * and can be overridden by system properties (e.g., -Dbrowser=firefox).
 *
 * Usage:
 *   FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
 *   String browser = config.browser();
 */
@Sources({
    "system:properties",
    "classpath:config/framework.properties"
})
public interface FrameworkConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("headless")
    @DefaultValue("false")
    boolean headless();

    @Key("env")
    @DefaultValue("staging")
    String env();

    @Key("base.url")
    @DefaultValue("https://the-internet.herokuapp.com")
    String baseUrl();

    @Key("implicit.wait")
    @DefaultValue("10")
    int implicitWait();

    @Key("explicit.wait")
    @DefaultValue("20")
    int explicitWait();

    @Key("page.load.timeout")
    @DefaultValue("30")
    int pageLoadTimeout();

    @Key("screenshot.on.failure")
    @DefaultValue("true")
    boolean screenshotOnFailure();

    @Key("retry.count")
    @DefaultValue("1")
    int retryCount();

    @Key("reports.dir")
    @DefaultValue("reports/extent")
    String reportsDir();
}
