package com.sap.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.Optional;

import static com.sap.properties.FilePaths.firefox_driver_file;

public class BrowserManager extends GeneralTestConfig {

    public void browserConfig(@Optional("chrome") String browser) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("scripts-type");
            options.addArguments("start-maximized");
            options.addArguments("--disable-search-geolocation-disclosure");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
            LOG.info("| Chrome browser launched successfully |");

        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefox_driver_file);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);

            FirefoxOptions options = new FirefoxOptions();
            options.setLogLevel(FirefoxDriverLogLevel.TRACE);
            driver = new FirefoxDriver();
            LOG.info("| Firefox browser launched successfully |");

        } else if (browser.equalsIgnoreCase("safari")) {
            capability.setCapability("browserstack.safari.driver", "3.141.59");
            capability.setCapability("browserstack.safari.enablePopups", false);
            capability.setCapability("browserstack.debug", true);
            capability.setCapability("browserstack.console", "debug");
            capability.setCapability("browserstack.networkLogs", true);

            SafariOptions sOptions = new SafariOptions();
            SafariOptions.fromCapabilities(capability);
            capability.setCapability(SafariOptions.CAPABILITY, sOptions);
            driver = new SafariDriver();
            LOG.info("| Safari browser launched successfully |");
        }
    }

    // Minimize browser window
    public void minimizeBrowserWindow() {
        Point p = driver.manage().window().getPosition();
        Dimension d = driver.manage().window().getSize();
        driver.manage().window().setPosition(new Point((d.getHeight()-p.getX()), (d.getWidth()-p.getY())));
    }

    // Maximize browser window
    public void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }


    public void tearDownDriver() {
        driver.quit();
    }
}
