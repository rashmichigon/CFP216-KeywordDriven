package com.bridgelabz.selenium.test;

import com.bridgelabz.selenium.base.BaseClass;
import com.bridgelabz.selenium.keyword.KeywordEngine;
import org.testng.annotations.Test;

public class FacebookTest extends BaseClass {

    @Test
    public void keywordDrivenLogin()
    {
        KeywordEngine keywordEngine = new KeywordEngine();
        keywordEngine.startExecution();
    }
}
