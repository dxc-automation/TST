package com.sap.test_scripts.desktop.opt_vim;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.desktop.documents.ProcessNonPOAccuralRequest;
import com.sap.objects.desktop.opt_vim.VIM_VA2_Obj;
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

@Listeners(TestNGListener.class)
public class VIM_VA2 extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private Dialogs dialogs = new Dialogs();
    private ProcessNonPOAccuralRequest processNonPOAccuralRequest = new ProcessNonPOAccuralRequest();
    private TestData testData = new TestData();
    private VIM_VA2_Obj vim_va2_obj = new VIM_VA2_Obj();

    private String transactionWindowTitle = "";
    private String transactionCodeSearch  = "/N/OPT/VIM_VA2";
    private String transactionCode        = "/OPT/VIM_VA2";
    private String transactionProgram     = "/OPT/VIM_ANALYTICS_750";

    private String menuBarSystemStatus = "wnd[0]/mbar/menu[3]/menu[11]";



    public void openTransaction() throws Exception {
        String testCaseString = testData.getTestCase(3);

        // Declare what will be information printed in the report
        testName = "<b>VIM Analytics</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Call transaction code using command field.<br>" +
                        "[2] Open system status dialog and verify transaction details.<br>" +
                        "[3] Search for specific document data entry.<br>" +
                        "[4] Validate document details.<br>" +
                        "[5] Close transaction.";

        // Start report listener
        startTestReport(testName, testDescription, testCaseString);

        commandField.searchForTransaction(transactionCodeSearch);
        commandField.checkSearchResult(transactionCode, menuBarSystemStatus);
    }


    public void searchByDocumentProcessingNumber() throws Exception {
        getSession();

        String documentProcessingNumber = "1202414";
        vim_va2_obj.enterDocumentProcessingNumber(documentProcessingNumber);
        vim_va2_obj.clickExecute();

        boolean elementExist = autoItX.controlCommandIsVisible(transactionWindowTitle, "", "128");
        Assert.assertTrue(elementExist);

        vim_va2_obj.openListOutputView();

        String numberOfResults = vim_va2_obj.getRequestValue("result");
        Assert.assertFalse(numberOfResults.equals("0"), "No search results for specific Document ID");

        String documentId = vim_va2_obj.getRequestValue("documentId");
        String businessArea = vim_va2_obj.getRequestValue("businessArea");
        String companyCode = vim_va2_obj.getRequestValue("companyCode");
        String status = vim_va2_obj.getRequestValue("status");
        String processStatus = vim_va2_obj.getRequestValue("processStatus");
        String dpDocumentType = vim_va2_obj.getRequestValue("dpDocumentType");
        String processType = vim_va2_obj.getRequestValue("processType");
        String exceptionReason = vim_va2_obj.getRequestValue("exceptionReason");
        String grossAmount = vim_va2_obj.getRequestValue("grossAmount");
        String currency = vim_va2_obj.getRequestValue("currency");
        String amount = vim_va2_obj.getRequestValue("amount");
        String enterOn = vim_va2_obj.getRequestValue("enterOn");
        String postingDate = vim_va2_obj.getRequestValue("postingDate");

        test.createNode("<b>[ Validate Document Details ]</b>")
                .pass("<pre>Document ID &Tab; &Tab; <b>" + documentId     + "</b><br>"
                        + "Document Type &Tab; <b>"        + dpDocumentType   + "</b><br>"
                        + "Business Area &Tab; &Tab; <b>"           + businessArea      + "</b><br>"
                        + "Status &Tab; &Tab; <b>"       + status       + "</b><br>"
                        + "Enter On &Tab; &Tab; <b>"         + enterOn    + "</b><br>"
                        + "Posting Date &Tab; &Tab; <b>"           + postingDate      + "</b><br>"
                        + "Process Type &Tab; &Tab; <b>"         + processType    + "</b><br>"
                        + "Process Status &Tab; &Tab; <b>"     + processStatus         + "</b><br>"
                        + "Exception Reason &Tab; <b>"      + exceptionReason  + "</b><br>"
                        + "Gross Amount &Tab; <b>"     + grossAmount + "</b><br>"
                        + "Amount &Tab; &Tab; &Tab; <b>"         + amount + " "   + currency + "</b><br>"
                        + "Company Code &Tab; <b>"         + companyCode    + "</b></pre>");

        vim_va2_obj.clickBackButton();
    }

    public void executeProcessDocument() {
        vim_va2_obj.selectFirstProcessRow();
        vim_va2_obj.clickDisplayWorkflowLog();
        vim_va2_obj.clickProcessDocument();
        vim_va2_obj.clickExecute();
    }

    public void submit(int testCase) throws Exception {
        String fileName = testData.getTestPropertyValue_2(testCase);
        dialogs.saveDownloadFile(fileName);
        dialogs.closePDF();

        String currency = processNonPOAccuralRequest.getBasicData("currency");
        String companyCode = processNonPOAccuralRequest.getBasicData("companyCode");

        String gl = processNonPOAccuralRequest.getLineItems("accountGL");
        String amount = processNonPOAccuralRequest.getLineItems("amount");
        String costCenter = processNonPOAccuralRequest.getLineItems("costCenter");

        String documentID = processNonPOAccuralRequest.getProcess("documentId");
        String documentType = processNonPOAccuralRequest.getProcess("documentType");

        String indexUser = processNonPOAccuralRequest.getOtherData("indexUser");
        String approver = processNonPOAccuralRequest.getOtherData("approver");
        String serviceFrom = processNonPOAccuralRequest.getOtherData("serviceFrom");
        String serviceTo = processNonPOAccuralRequest.getOtherData("serviceTo");
        String requestType = processNonPOAccuralRequest.getOtherData("requestType");
        String cardLastDigits = processNonPOAccuralRequest.getOtherData("cardLastDigits");
        String ttlUPI = processNonPOAccuralRequest.getOtherData("ttlUPI");
        String cardHolderUPI = processNonPOAccuralRequest.getOtherData("cardHolderUPI");

        processNonPOAccuralRequest.reviewCompleted();

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