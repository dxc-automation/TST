package com.sap.test_scripts.desktop.opt_vim;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.desktop.opt_vim.VIM_VA2_Obj;
import com.sap.objects.desktop.opt_vim.VIM_WP_Obj;
import com.sap.properties.TestData;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import com.sap.test_scripts.desktop.commonly_used.Dialogs;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.TestData.testDescription;
import static com.sap.properties.TestData.testName;
import static com.sap.properties.TestDataProperties.*;

@Listeners(TestNGListener.class)
public class VIM_WP extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestData     testData     = new TestData();
    private VIM_WP_Obj   vim_wp_obj   = new VIM_WP_Obj();
    private Dialogs      dialogs      = new Dialogs();

    private String document_id = "1202414";


    public void openTransaction(int testCase) throws Exception {
        String testCaseString = testData.getTestCase(testCase);

        // Declare what will be information printed in the report
        testName = "<b>VIM Workplace</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Call transaction code using command field.<br>" +
                        "[2] Open system status dialog and verify transaction details.<br>" +
                        "[3] Search for specific document data entry.<br>" +
                        "[4] Validate document details.<br>" +
                        "[5] Close transaction.";

        // Start report listener
        startTestReport(testName, testDescription, testCaseString);

        commandField.searchForTransaction(vim_wp_obj.transactionCodeSearch);
        commandField.checkSearchResult(vim_wp_obj.transactionCode, vim_wp_obj.menuBarSystemStatus);
    }

    // Change work view mode
    public void setWorkViewMode(int testCase) throws Exception {
        String workView = testData.getTestPropertyValue_1(testCase);
        vim_wp_obj.switchWorkView(workView);
        vim_wp_obj.validateWorkView(workView);

        test.createNode("<b>[ Verify Work View Modes ]</b>")
                .pass("Successfully changed work view to <b>" + workView + "</b>");
    }

    public void searchDocumentId() throws Exception {
        vim_wp_obj.findDocument(document_id);
        vim_wp_obj.openSearchResultDocument();

        test.createNode("<b>[ Verify Document Search Functionality ]</b>")
                .pass("Successfully found specific document with ID <b>" + document_id + "</b>");
    }

    public void submit(int testCase) throws Exception {
        String fileName = testData.getTestPropertyValue_2(testCase);
        dialogs.saveDownloadFile(fileName);
        dialogs.closePDF();

        String currency = vim_wp_obj.getBasicData("currency");
        String companyCode = vim_wp_obj.getBasicData("companyCode");

        String gl = vim_wp_obj.getLineItems("accountGL");
        String amount = vim_wp_obj.getLineItems("amount");
        String costCenter = vim_wp_obj.getLineItems("costCenter");

        String documentID = vim_wp_obj.getProcess("documentId");
        String documentType = vim_wp_obj.getProcess("documentType");

        String indexUser = vim_wp_obj.getOtherData("indexUser");
        String approver = vim_wp_obj.getOtherData("approver");
        String serviceFrom = vim_wp_obj.getOtherData("serviceFrom");
        String serviceTo = vim_wp_obj.getOtherData("serviceTo");
        String requestType = vim_wp_obj.getOtherData("requestType");
        String cardLastDigits = vim_wp_obj.getOtherData("cardLastDigits");
        String ttlUPI = vim_wp_obj.getOtherData("ttlUPI");
        String cardHolderUPI = vim_wp_obj.getOtherData("cardHolderUPI");

        vim_wp_obj.reviewCompleted();

        test.createNode("<b>[ Validate Document Details ]</b>")
                .pass("<pre>Document ID &Tab; &Tab; <b>" + documentID     + "</b><br>"
                + "Document Type &Tab; <b>"        + documentType   + "</b><br>"
                + "Index User &Tab; &Tab; <b>"           + indexUser      + "</b><br>"
                + "Approver &Tab; &Tab; <b>"       + approver       + "</b><br>"
                + "Service From &Tab; &Tab; <b>"         + serviceFrom    + "</b><br>"
                + "Service To &Tab; &Tab; <b>"           + serviceTo      + "</b><br>"
                + "Request Type &Tab; &Tab; <b>"         + requestType    + "</b><br>"
                + "UPI of TTL &Tab; &Tab; <b>"     + ttlUPI         + "</b><br>"
                + "Card Holder UPI &Tab; <b>"      + cardHolderUPI  + "</b><br>"
                + "Card Last Digits &Tab; <b>"     + cardLastDigits + "</b><br>"
                + "G/L Account &Tab; &Tab; <b>"    + gl             + "</b><br>"
                + "Amount &Tab; &Tab; &Tab; <b>"         + amount + " "   + currency + "</b><br>"
                + "Cost Center &Tab; &Tab; <b>"          + costCenter     + "</b><br>"
                + "Company Code &Tab; <b>"         + companyCode    + "</b></pre>")
                .pass("Successfully submit");
    }




    public void closeTransaction() throws IOException, InterruptedException {
        commandField.goToRootSAPMenu();

        test.createNode("<b>[ Close Transaction ]</b>")
            .pass("Transaction is closed properly");
    }
}