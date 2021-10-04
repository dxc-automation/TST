package com.sap.utilities;

import static com.sap.config.GeneralTestConfig.*;
import static com.sap.config.ExtentReport.*;
import static com.sap.properties.FilePaths.config_properties_file;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.Assert;
import org.testng.SkipException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import static com.sap.properties.TestData.*;
import static com.sap.config.ConsoleRunner.*;
import static com.sap.properties.TestDataReader.getProperties;


public class Commons extends GeneralTestConfig {

    //***   Element Highlighting
    public void elementHighlighting(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'');", element);
    }


    //***   Mask user password
    public String getMasketPassword(String password) throws IOException {
        String maskedPassword =
                password.replaceAll("[A-Z]", "*")
                        .replaceAll("[a-z]", "*")
                        .replaceAll("[0-9]", "*")
                        .replace("!", "*")
                        .replace("?", "*")
                        .replace("@", "*")
                        .replace("#", "*")
                        .replace("$", "*");

        return maskedPassword;
    }


    //***   Switch tabs
    public void getBrowserTabs(int tabNumber) {
        String mainWindowHandle = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber));
    }


    //***   Click on text field
    public void clickOnTextField(String objectLocator) {
        Obj = new ActiveXComponent(Session.invoke("FindById", objectLocator).toDispatch());
        Obj.invoke("setFocus");

        Obj = new ActiveXComponent(Session.invoke("FindById", objectLocator).toDispatch());
        Obj.invoke("caretPosition", "0");
    }


    //***   Wait for specific element to be displayed
    private void waitForControlActive(String title, String text, String control, long maxWaitTime) throws InterruptedException {
        long timeWaited = 0;
        while (true) {
            if (timeWaited >= maxWaitTime) {
                Assert.fail("waited " + timeWaited + " milliseconds but control with id " + control + " is not active");
            } else if (autoItX.controlCommandIsEnabled(title, text, control)) {
                break;
            } else {
                Thread.sleep(2000L);
                timeWaited += 2000L;
            }
        }
    }





    //***   Returns timestamp as string
    public String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = dateFormat.format(timestamp);
        return time;
    }

    //***   Used for check which tests are enabled and disabled
    public void checkTestStatus(int testCase) throws Exception {
        if (testData.getTestStatus(testCase).equalsIgnoreCase("No")) {
            throw new SkipException("Test is disabled");
        } else {
            System.out.println("Test status check completed");
        }
    }


    //***   Returns current date as string
    public String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = date.format(formatter);
        return currentDate;
    }


    //***   Returns current date plus one day
    public String getCurrentDatePlusDays(int days) {
        LocalDate date = LocalDate.now().plusDays(days);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String datePlus = date.format(dateFormatter);
        return datePlus;
    }


    //***   Returns current time minus hours
    public String getCurrentTimeMinusHours(int hours) {
        LocalTime time = LocalTime.now().minusHours(hours);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);
        return formattedTime;
    }


    //***   Returns randomly generated string
    public String getRandomGeneratedString() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
