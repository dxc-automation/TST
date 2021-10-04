package com.sap.test_cases;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.opt_vim.VIM_WP;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TC_001 extends GeneralTestConfig {

    @Test
    public void Scenario_1() throws Exception {
        Login login = new Login();
        login.setAccount("reviewer");
        login.launchSAPLogonApp();
        login.logonServer();

        VIM_WP vim_wp = new VIM_WP();
        vim_wp.openTransaction(6);
        vim_wp.setWorkViewMode(6);
        vim_wp.searchDocumentId();
        vim_wp.submit(6);

    }
}
