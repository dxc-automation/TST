package com.sap.properties;

import java.io.*;

import static com.sap.properties.TestDataReader.getTestData;

public class TestData {

    public static String emailOnOff;
    public static String username;
    public static String password;
    public static String appURL;
    private String testStatus;

    //  * * * *    TEST INFO
    public static String testName;
    public static String testCase = "";
    public static int    testCaseNumber;
    public static String testDescription;
    public static String transactionPackage;
    public static String transactionProgram;
    public static String transactionCode;
    public static String transactionName;

    public static String accountingReviewerUser;
    public static String accountingReviewerPass;
    public static String managerUser;
    public static String managerPass;
    public static String requesterUser;
    public static String requesterPass;


    //***   SAP ACCOUNTS   ***//

    public String getAccountingReviewerUser() throws Exception {
        accountingReviewerUser = getTestData("Account", "User", 1);
        return accountingReviewerUser;
    }

    public String getAccountingReviewerPass() throws Exception {
        accountingReviewerPass = getTestData("Account", "Password", 1);
        return accountingReviewerPass;
    }

    public String getManagerUser() throws Exception {
        managerUser = getTestData("Account", "User", 2);
        return managerUser;
    }

    public String getManagerPass() throws Exception {
        managerPass = getTestData("Account", "Password", 2);
        return managerPass;
    }

    public String getRequesterUser() throws Exception {
        requesterUser = getTestData("Account", "User", 3);
        return requesterUser;
    }

    public String getRequesterPass() throws Exception {
        requesterPass = getTestData("Account", "Password", 3);
        return requesterPass;
    }




    //***   TEST GENERAL INFO   ***//

    public String getTestStatus(int testCase) throws Exception {
        testStatus = getTestData("Test Cases", "Enable/Disable", testCase);
        return testStatus;
    }

    public String getTestCase(int testCaseRow) throws Exception {
        testCase = getTestData("Test Cases", "Test Case ID", testCaseRow);
        return testCase;
    }



    //***   TRANSACTION INFORMATION   ***//

    public String getTransactionPackage(int testCase) throws Exception {
        transactionPackage = getTestData("Test Data", "Package", testCase);
        return transactionPackage;
    }

    public String getTransactionProgram(int testCase) throws Exception {
        transactionProgram = getTestData("Test Data", "Program", testCase);
        return transactionProgram;
    }

    public String getTransactionCode(int testCase) throws Exception {
        transactionCode = getTestData("Test Data", "Transaction Code", testCase);
        return transactionCode;
    }

    public String getTransactionName(int testCase) throws Exception {
        transactionName = getTestData("Test Data", "Transaction Text", testCase);
        return transactionName;
    }



    //***   TEST DATA   ***//

    public String getTestPropertyValue_1(int testCase) throws Exception {
        String value = getTestData("Test Data", "Property Value 1", testCase);
        return value;
    }

    public String getTestPropertyValue_2(int testCase) throws Exception {
        String value = getTestData("Test Data", "Property Value 2", testCase);
        return value;
    }

    public String getTestPropertyValue_3(int testCase) throws Exception {
        String value = getTestData("Test Data", "Property Value 3", testCase);
        return value;
    }

    public String getTestPropertyValue_4(int testCase) throws Exception {
        String value = getTestData("Test Data", "Property Value 4", testCase);
        return value;
    }

    public String getTestPropertyValue_5(int testCase) throws Exception {
        String value = getTestData("Test Data", "Property Value 5", testCase);
        return value;
    }

}
