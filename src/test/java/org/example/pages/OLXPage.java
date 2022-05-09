package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

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

    public void pressContulMeu() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contulMeu.click();
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public void pressLogin() {
        loginButton.waitUntilClickable();
        loginButton.click();
    }

    public void fillUsernameField(String username) {
        usernameField.sendKeys(username);
    }

    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void pressAdaugaAnunt() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adaugaAnuntButton.click();
    }

    public void ziCaAiInteles() {
        amInteles.click();
    }

    public void ignoraChestie() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ignoraChestie.click();
    }

}
