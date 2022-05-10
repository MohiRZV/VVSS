package org.example.features;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.UserSteps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(SerenityRunner.class)
public class OLXStory extends PageObject {
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
        webdriver.get("https://www.olx.ro/cont/");
        webdriver.manage().window().maximize();
        WebElement username=webdriver.findElement(By.id("userEmail"));
        WebElement password=webdriver.findElement(By.id("userPass"));
        WebElement login=webdriver.findElement(By.id("se_userLogin"));
        WebElement acceptCookies=webdriver.findElement(By.id("onetrust-accept-btn-handler"));
        username.sendKeys(jsonDataProvider.getValue("email"));
        password.sendKeys(jsonDataProvider.getValue("password"));
        acceptCookies.click();
        WebElementFacade fade = $(By.className("onetrust-pc-dark-filter ot-fade-in"));
        fade.waitUntilNotVisible();
        login.click();
        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        try {
            wait.until(ExpectedConditions.titleIs("Contul meu • OLX.ro"));
            assert true;
        }catch (TimeoutException ex){
            assert false;
        }
    }

    @Test
    public void test_login_invalid() throws InterruptedException {
        webdriver.get("https://www.olx.ro/cont/");
        webdriver.manage().window().maximize();
        WebElement username=webdriver.findElement(By.id("userEmail"));
        WebElement password=webdriver.findElement(By.id("userPass"));
        WebElement login=webdriver.findElement(By.id("se_userLogin"));
        WebElement acceptCookies=webdriver.findElement(By.id("onetrust-accept-btn-handler"));
        username.sendKeys(jsonDataProvider.getValue("email"));
        password.sendKeys(jsonDataProvider.getValue("password_invalid"));
        acceptCookies.click();
        WebElementFacade fade = $(By.className("onetrust-pc-dark-filter ot-fade-in"));
        fade.waitUntilNotVisible();
        login.click();
        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        try {
            wait.until(ExpectedConditions.titleIs("Contul meu • OLX.ro"));
            assert false;
        }catch (TimeoutException ex){
            assert true;
        }
    }

    @Test
    public void open_olx_main_page_select_contul_meu_fill_credentials_press_login_should_open_user_page() {
        user.is_the_home_page();
        user.acceptCookies();
        user.enter_login_page();
        user.fillUsername(jsonDataProvider.getValue("email"));
        user.fillPassword(jsonDataProvider.getValue("password"));
        user.pressLogin();
        user.ziCaAiInteles();
        user.ignoraChestie();
        user.pressAdaugaAnunt();
    }
}
