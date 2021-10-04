package com.sap.test_cases;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.business_workplace.SINWP;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.transactions.ME23N;
import com.sap.test_scripts.desktop.transactions.ZFTPR_CBK_CONFIG_DS;
import com.sap.test_scripts.web.WebLogin;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TC_005 extends GeneralTestConfig {

    private static String requestN;

    @BeforeClass
    public void initBrowser() throws Exception {
        browserManager.browserConfig("chrome");
    }

    @AfterClass
    public void tearDown() {
        browserManager.tearDown();
    }



    @Test
    public void Scenario_1() throws Exception {
       WebLogin webLogin = new WebLogin();
       webLogin.webLogin(6);
    }
}
