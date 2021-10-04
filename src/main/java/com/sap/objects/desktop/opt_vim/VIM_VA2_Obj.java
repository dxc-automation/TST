package com.sap.objects.desktop.opt_vim;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestData;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.TestData.testDescription;
import static com.sap.properties.TestData.testName;

@Listeners(TestNGListener.class)
public class VIM_VA2_Obj extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestData testData = new TestData();

    private String transactionWindowTitle = "VIM Analytics";
    private String transactionCodeSearch  = "/N/OPT/VIM_VA2";
    private String transactionCode        = "/OPT/VIM_VA2";
    private String transactionProgram     = "/OPT/VIM_ANALYTICS_750";

    private String path;
    private String value;
    private String menuBarSystemStatus = "wnd[0]/mbar/menu[3]/menu[11]";
    private String executeBtnId = "119";



    // Add request id into document processing number field
    public void enterDocumentProcessingNumber(String documentProcessingNumber) throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtS_DOCID-LOW").toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("text", documentProcessingNumber);
        autoItX.sleep(delay);
    }

    // Open table list view
    public void openListOutputView() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[0]/shell").toDispatch());
        Obj.invoke("pressToolbarContextButton", "&MB_VIEW");
        autoItX.sleep(500);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[0]/shell").toDispatch());
        Obj.invoke("selectContextMenuItem", "&PRINT_BACK_PREVIEW");

        autoItX.winActivate("[CLASS:AfxWnd140]");
        autoItX.controlClick("[CLASS:AfxWnd140]", "", "500");
    }

    // Get specific value from table
    public String getRequestValue(String property) {
        switch (property) {
            case "result":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/lbl[0,0]";
                break;
            case "documentId":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[1,4]";
                break;
            case "businessArea":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[9,4]";
                break;
            case "companyCode":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[14,4]";
                break;
            case "status":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[60,4]";
                break;
            case "processStatus":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[67,4]";
                break;
            case "dpDocumentType":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[78,4]";
                break;
            case "processType":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[89,4]";
                break;
            case "exceptionReason":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[100,4]";
                break;
            case "grossAmount":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[160,4]";
                break;
            case "currency":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr").toDispatch());
                Obj = new ActiveXComponent(Obj.getProperty("horizontalScrollbar").toDispatch());
                Obj.setProperty("position", 54);
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[117,4]";
                break;
            case "amount":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[127,4]";
                break;
            case "enterOn":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr").toDispatch());
                Obj = new ActiveXComponent(Obj.getProperty("horizontalScrollbar").toDispatch());
                Obj.setProperty("position", 82);
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[145,4]";
                break;
            case "postingDate":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[156,4]";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }

    public void clickBackButton() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[0]/btn[3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }


    // Select process row
    public void selectFirstProcessRow() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("selectedRows", "0");
    }

    // Process workflow
    public void clickDisplayWorkflowLog() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("pressToolbarButton", "WFLOG");
    }

    public void clickProcessDocument() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/tabsVIEW_STRIP/tabpSSCV/ssubSUBSCREEN01:SAPLSWL7:0110/cntlMAIN_CONT/shellcont/shell/shellcont[0]/shell").toDispatch());
        Variant[] arg = new Variant[2];
        arg[0] = new Variant("000104954587");
        arg[1] = new Variant("WI_TEXT");
        Obj.invoke("selectItem", arg);
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("clickLink", arg);
    }

    // Click on the execute button
    public void clickExecute() {
        autoItX.winWait("[CLASS:SAP_FRONTEND_SESSION]", "", 3);
        autoItX.winActivate("[CLASS:SAP_FRONTEND_SESSION]");
        autoItX.controlFocus("[CLASS:SAP_FRONTEND_SESSION]", "", executeBtnId);
        autoItX.sleep(delay);
        autoItX.controlClick("[CLASS:SAP_FRONTEND_SESSION]", "", executeBtnId);
    }
}