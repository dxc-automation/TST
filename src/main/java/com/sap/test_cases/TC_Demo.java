package com.sap.test_cases;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.business_workplace.SINWP;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.transactions.FB03;
import com.sap.test_scripts.desktop.transactions.ME23N;
import com.sap.test_scripts.desktop.transactions.ZFTPR_CBK_CONFIG_DS;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TC_Demo extends GeneralTestConfig {

    @Test(priority = 0, enabled = true)
    public void login() throws Exception {
        Login login = new Login();
        login.launchSAPLogonApp();
        login.logonServer();
    }


    @Test(priority = 1, enabled = true)
    public void scenario_1() throws Exception {
        ZFTPR_CBK_CONFIG_DS transaction = new ZFTPR_CBK_CONFIG_DS();
        transaction.openTransaction();
        transaction.setFilter();
        transaction.searchForEmailID();
        transaction.verifyErrorMessage();
        transaction.saveRecordChanges();
        transaction.closeTransaction();
    }

    @Test(priority = 2, enabled = true)
    public void scenario_2() throws Exception {
        SINWP transaction = new SINWP();
        int testCase = 2;

        transaction.openTransaction();
        transaction.openInbox();
        transaction.setFilter(testCase);
        transaction.searchRequest(testCase);
        transaction.acceptRequest();
        transaction.rejectRequest();
        transaction.closeTransaction();
    }

    @Test(priority = 3, enabled = true)
    public void scenario_3() throws Exception {
        FB03 transaction = new FB03();
        transaction.openTransaction();
        transaction.searchDataEntry();
        transaction.getDataEntry();
        transaction.getTableData();
        transaction.closeTransaction();
    }

    @Test(priority = 4, enabled = true)
    public void Scenario_4() throws Exception {
        ME23N transaction = new ME23N();
        int testCase = 4;

        transaction.openTransaction();
        transaction.otherPurchaseOrder(testCase);
        transaction.headerDeliveryInvoiceDetails();
        transaction.headerConditionDetails();
        transaction.headerAddressDetails();
        transaction.headerAdditionalDataDetails();
        transaction.headerOrganizationDataDetails();
        transaction.headerStatusDetails();
        transaction.headerReleaseStrategyDetails();
        transaction.headerVersionsDetails();
        transaction.itemOverview();
        transaction.item(testCase);
        transaction.closeTransaction();
    }

    @Test(priority = 5, enabled = true)
    public void Scenario_5() throws Exception {
        ME23N transaction = new ME23N();
        int testCase = 5;

        transaction.openTransaction();
        transaction.otherPurchaseOrder(testCase);
        transaction.headerDeliveryInvoiceDetails();
        transaction.headerConditionDetails();
        transaction.headerAddressDetails();
        transaction.headerAdditionalDataDetails();
        transaction.headerOrganizationDataDetails();
        transaction.headerStatusDetails();
        transaction.headerReleaseStrategyDetails();
        transaction.headerVersionsDetails();
        transaction.itemOverview();
        transaction.item(testCase);
        transaction.closeTransaction();
    }
}
