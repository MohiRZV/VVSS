package org.example.features;

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
    private final JsonDataProvider jsonDataProvider = new JsonDataProvider();
    @Before
    public void init() {
        System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
    }

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public UserSteps user;
    @Test
    public void test_login() throws InterruptedException {
        webdriver.get("https://www.olx.ro/cont/?ref%5B0%5D%5Baction%5D=myaccount&ref%5B0%5D%5Bmethod%5D=pro");
        webdriver.manage().window().maximize();
        WebElement username=webdriver.findElement(By.id("userEmail"));
        WebElement password=webdriver.findElement(By.id("userPass"));
        WebElement login=webdriver.findElement(By.id("se_userLogin"));
        WebElement acceptCookies=webdriver.findElement(By.id("onetrust-accept-btn-handler"));
        username.sendKeys(jsonDataProvider.getValue("email"));
        password.sendKeys(jsonDataProvider.getValue("password"));
        acceptCookies.click();
        // nu merge fara because yes
        Thread.sleep(100);
        login.click();
        String actualUrl="https://www.olx.ro/cont/?ref%5B0%5D%5Baction%5D=myaccount&ref%5B0%5D%5Bmethod%5D=pro";
        String expectedUrl= webdriver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void open_olx_main_page_select_contul_meu_fill_credentials_press_login_should_open_user_page() {
        user.is_the_home_page();
        user.acceptCookies();
        user.enter_login_page();
        user.fillUsername(jsonDataProvider.getValue("email"));
        user.fillPassword(jsonDataProvider.getValue("password"));
        user.pressLogin();
        String expectedUrl="https://www.olx.ro/cont/?ref%5B0%5D%5Baction%5D=myaccount&ref%5B0%5D%5Bmethod%5D=pro";
        String actualUrl= webdriver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        user.ziCaAiInteles();
        user.ignoraChestie();
        user.pressAdaugaAnunt();
    }
}
