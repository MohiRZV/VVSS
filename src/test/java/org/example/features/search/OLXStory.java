package org.example.features.search;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.UserSteps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(SerenityRunner.class)
public class OLXStory {
    @Before
    public void init() {
        System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
    }

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public UserSteps user;

    @Test
    public void test_login() {
        webdriver.get("https://www.olx.ro/cont/?ref%5B0%5D%5Baction%5D=myaccount&ref%5B0%5D%5Bmethod%5D=pro");
        webdriver.manage().window().maximize();

    }
}
