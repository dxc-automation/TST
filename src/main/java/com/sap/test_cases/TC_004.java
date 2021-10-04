package com.sap.test_cases;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.transactions.ME23N;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TC_004 extends GeneralTestConfig {

    @Test
    public void Scenario_4() throws Exception {
        Login login = new Login();
        login.launchSAPLogonApp();


        ME23N transaction = new ME23N();
        transaction.openTransaction();
        transaction.otherPurchaseOrder(4);
        transaction.headerDeliveryInvoiceDetails();
        transaction.headerConditionDetails();
        transaction.headerAddressDetails();
        transaction.headerAdditionalDataDetails();
        transaction.headerOrganizationDataDetails();
        transaction.headerStatusDetails();
        transaction.headerReleaseStrategyDetails();
        transaction.headerVersionsDetails();
        transaction.itemOverview();
        transaction.item(4);
        transaction.closeTransaction();
    }
}
