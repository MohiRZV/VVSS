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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
    public void open_olx_main_page_select_contul_meu_fill_credentials_press_login_should_open_user_page() throws InterruptedException {
        user.is_the_home_page();
        user.acceptCookies();
        user.enter_login_page();
        user.fillUsername(jsonDataProvider.getValue("email"));
        user.fillPassword(jsonDataProvider.getValue("password"));
        user.pressLogin();
        user.ziCaAiInteles();
        user.ignoraChestie();
        user.pressAdaugaAnunt();

        user.inAsteptare();
        List<WebElement> button = webdriver.findElements(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div[3]/div/div[2]/div/div[2]/div[2]/ul/li[2]/button"));
        if (button.isEmpty()) {
            user.active();
            webdriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div[3]/div/div[2]/div/div[2]/div[2]/ul/li[5]/button")).click();
        }
        else {
            button.get(0).click();
        }

        user.dezactivezaAnunt();

        //logout
        WebElement ele = webdriver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[2]/div[1]/a"));
        Actions action = new Actions(webdriver);
        action.moveToElement(ele);
        WebElement subMenu = webdriver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[2]/div[2]/ul[2]/li[3]/a"));
        action.moveToElement(subMenu);
        action.click().build().perform();

        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        try {
            wait.until(ExpectedConditions.titleIs("OLX - Cumpără și vinde"));
            assert false;
        }catch (TimeoutException ex){
            assert true;
        }

        user.fillTitleField("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        user.deschideCategorii();
        user.alegeCategorieServicii();
        user.alegeCategorieServiciiPC();
        user.fillDescriptionField("Buna ziua aceasta este o descriere imi pare bine sa va cunosc nu ma simt bine deloc");
        user.persoanaFizica();
        user.publicaAnunt();
        Thread.sleep(10000);
    }
}
