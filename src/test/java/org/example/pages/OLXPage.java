package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.temporal.ChronoUnit.SECONDS;

@DefaultUrl("https://www.olx.ro/")
public class OLXPage extends PageObject {

    @FindBy(id="topLoginLink")
    private WebElementFacade contulMeu;

    @FindBy(id = "userEmail")
    private WebElementFacade usernameField;

    @FindBy(id = "userPass")
    private WebElementFacade passwordField;

    @FindBy(id = "se_userLogin")
    private WebElementFacade loginButton;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElementFacade acceptCookiesButton;

    @FindBy(className = "css-1povu0j")
    private WebElementFacade adaugaAnuntButton;

    @FindBy(className = "css-6wwl3n-BaseStyles")
    private WebElementFacade amInteles;

    @FindBy(className = "css-rfuzkc-BaseStyles")
    private WebElementFacade ignoraChestie;

    @FindBy(className = "onetrust-pc-dark-filter ot-fade-in")
    private WebElementFacade cookieFade;

    public void pressContulMeu() {
        setImplicitTimeout(10, SECONDS);
        contulMeu.click();
        waitFor(ExpectedConditions.titleIs("OLX - Cumpără și vinde"));
    }

    public void acceptCookies() {
        setImplicitTimeout(1, SECONDS);
        acceptCookiesButton.click();
        cookieFade.waitUntilNotVisible();
        resetImplicitTimeout();
    }

    public void pressLogin() {
        setImplicitTimeout(10, SECONDS);
        loginButton.waitUntilClickable();
        loginButton.click();
        waitFor(ExpectedConditions.titleIs("Anunturi Gratuite - Peste 4 milioane anunturi - OLX.ro"));
        resetImplicitTimeout();
    }

    public void fillUsernameField(String username) {
        usernameField.sendKeys(username);
    }

    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void pressAdaugaAnunt() {
        adaugaAnuntButton.click();
    }

    public void ziCaAiInteles() {
        amInteles.click();
    }

    public void ignoraChestie() {
        ignoraChestie.click();
    }

}
