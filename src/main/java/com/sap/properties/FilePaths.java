package com.sap.properties;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePaths {

    //  Get project main dir
    public static Path path = Paths.get(new File(System.getProperty("user.dir")).toPath().toString());


    //  * * * *    F I L E   P A T H S

    public final static String appPath             = "C:\\Program Files (x86)\\SAP\\FrontEnd\\SAPgui\\saplogon.exe";

    public final static String attachment          = path + "/src/main/resources/TestAttachment.xlsx";
    public final static String testDataFile        = path + "/src/main/resources/TestData.xlsx";
    public final static String reportJsonFolder    = path + "/report/JSON/";
    public final static String reportFolder        = path + "/report/";
    public final static String reportHtmlFile      = path + "/report/TestReport.html";
    public final static String reportConfigXmlFile = path + "/src/main/resources/extent-config.xml";
    public final static String xmlFilesFolder      = path + "/src/main/resources/";
    public final static String reportZipFile       = path.toString();

    public final static String screenshotsFailedFolder   = path + "\\report\\Screenshots\\Failed\\";
    public final static String screenshotsActualFolder   = path + "\\report\\Screenshots\\Actual\\";
    public final static String screenshotsBufferFolder   = path + "\\report\\Screenshots\\Buffer\\";
    public final static String screenshotsExpectedFolder = path + "\\report\\Screenshots\\Expected\\";
    public final static String videoFiles = path + "\\report\\video\\";

    public final static String config_properties_file      = path + "\\src\\main\\resources\\config.properties";
    public final static String winium_driver_file          = path + "\\src\\main\\resources\\Winium.Desktop.Driver.exe";
    public final static String firefox_driver_file         = path + "\\src\\main\\resources\\drivers\\geckodriver.exe";

    public final static String tempFolder                  = path + "\\report\\temp\\";


}


