package com.sap.test_cases;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.business_workplace.SINWP;
import com.sap.test_scripts.desktop.opt_vim.VIM_VA2;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TC_002 extends GeneralTestConfig {

    @Test
    public void Scenario_2() throws Exception {
        Login login = new Login();
        login.setAccount("reviewer");
        login.launchSAPLogonApp();
        login.logonServer();

        VIM_VA2 vim_va2 = new VIM_VA2();
        vim_va2.openTransaction();
        vim_va2.searchByDocumentProcessingNumber();
        vim_va2.executeProcessDocument();
        vim_va2.submit(6);
    }
}
