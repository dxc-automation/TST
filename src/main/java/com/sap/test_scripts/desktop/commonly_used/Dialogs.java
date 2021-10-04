package com.sap.test_scripts.desktop.commonly_used;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.FilePaths.tempFolder;
import static com.sap.properties.TestData.*;

@Listeners(TestNGListener.class)
public class Dialogs extends GeneralTestConfig {


   public void cancelDownloadFile() {
       String dialogTitle = "[CLASS:#32770]";
       String dialogCancelBtn = "2";

       autoItX.winWait(dialogTitle, "", 3);
       autoItX.winActivate(dialogTitle);
       autoItX.controlFocus(dialogTitle, "", dialogCancelBtn);
       autoItX.sleep(delay);
       autoItX.controlClick(dialogTitle, "", dialogCancelBtn);
   }

   public void saveDownloadFile(String tempFileName) throws IOException, InterruptedException {
       getSession();
       String dialogTitle = "[CLASS:#32770]";
       String dialogSaveBtn = "4427";
       String dialogConfirmSaveBtn = "Button2";
       String dialogAddressBarTxtField = "1001";
       String dialogFileNameField = "Edit1";
       String file = tempFolder + tempFileName;

       // Click save button

       autoItX.winWait("File Download");
       autoItX.winActivate("File Download");
       autoItX.controlFocus("File Download", "", dialogSaveBtn);
       autoItX.sleep(delay);
       autoItX.controlClick("File Download", "", dialogSaveBtn);

       //autoItX.send("{ALTDOWN}s{s}");

       // Add file path and name
       autoItX.winWait("Save As");
       autoItX.winActivate("Save As");
       autoItX.controlFocus("Save As", "", dialogFileNameField);
       autoItX.sleep(delay);
       autoItX.controlSend("Save As", "", dialogFileNameField, file);

       // Confirm save
       autoItX.controlFocus("Save As", "", dialogConfirmSaveBtn);
       autoItX.sleep(delay);
       autoItX.controlClick("Save As", "", dialogConfirmSaveBtn);
   }


   public void closePDF() {
       autoItX.sleep(2000);
       autoItX.winActivate("[CLASS:AcrobatSDIWindow]");
       autoItX.controlClick("[CLASS:AcrobatSDIWindow]", "", "Close");
   }


}

