package com.sap.test_cases;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.transactions.FB03;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TC_003 extends GeneralTestConfig {

    @Test
    public void Scenario_3() throws Exception {
        Login login = new Login();
        login.launchSAPLogonApp();

        FB03 transaction = new FB03();
        transaction.openTransaction();
        transaction.searchDataEntry();
        transaction.getDataEntry();
        transaction.getTableData();
        transaction.closeTransaction();

    }
}
