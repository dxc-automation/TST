package com.sap.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.sap.config.GeneralTestConfig.*;

public class Assertions {


    public static boolean elementExistsAssertion(WebElement element) {
        if (element.getSize() != null) {
            return true;
        } else {
            return false;
        }
    }


    public static ExpectedCondition<Boolean> textToBePresentInElement(By locator, String text) {

        return new ExpectedCondition<Boolean>() {
            WebElement element = driver.findElement(locator);
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }



    public boolean checkPageTitle(String expectedPageTitle) {
        String pageTitle   = driver.getTitle();
        if (pageTitle.equals(expectedPageTitle)) {
            return true;
        } else {
            return false;
        }
    }
}
