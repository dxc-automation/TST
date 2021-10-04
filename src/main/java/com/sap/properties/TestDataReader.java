package com.sap.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import static com.sap.config.ConsoleRunner.testXml;
import static com.sap.properties.FilePaths.*;
import static com.sap.properties.TestData.*;

public class TestDataReader {

    private static DataFormatter dataFormatter = new DataFormatter();
    private static File file = new File(testDataFile);
    private static FileInputStream inputStream;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow   row;
    private static XSSFCell  cell;
    private static FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

    private static String cellValue;



    public static String getTestData(String spreadsheet, String column, int rowNumber) throws Exception {
        inputStream = new FileInputStream(file);
        workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
        sheet = workbook.getSheet(spreadsheet);
        row = sheet.getRow(0);

        int columns = 0;

        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().equalsIgnoreCase(column)) {
                columns = i;
            }
        }

        row = sheet.getRow(rowNumber);
        cell = row.getCell(columns);
        cellValue = dataFormatter.formatCellValue(cell, formulaEvaluator);
        return cellValue;
    }



    //***   Returns value from config.properties
    public static void getProperties() throws IOException {
        InputStream inputStream = new FileInputStream(config_properties_file);
        Properties properties = new Properties();
        properties.load(inputStream);

        // Get value of the property
        testXml    = properties.getProperty("xml");
        username   = properties.getProperty("SAP_Username_1");
        password   = properties.getProperty("SAP_Password_1");
        emailOnOff = properties.getProperty("email");
        appURL     = properties.getProperty("appUrl");
        inputStream.close();
    }
}
