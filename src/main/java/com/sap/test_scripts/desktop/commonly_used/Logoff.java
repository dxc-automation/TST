package com.sap.test_scripts.desktop.commonly_used;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.TestData.*;

@Listeners(TestNGListener.class)
public class Logoff extends GeneralTestConfig {

    private CommandField commandField = new CommandField();


    public void logOff() throws Exception {
        String testCaseString = "SAP GUI Logon";

        testName = "<b>SAP GUI Logoff</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                "[1] Launch SAP GUI Logon application.<br>" +
                "[2] Validate login to Test [TST] server.<br>";

        startTestReport(testName, testDescription, testCaseString);

        getSession();
        commandField.goToRootSAPMenu();

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[0]/btn[15]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");

        autoItX.winWait("Log Off", "", 3);
        autoItX.winActivate("Log Off");
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/btnSPOP-OPTION1").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");

        test.pass("Successfully log off");
    }
}

