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

    @FindBy(xpath = "//button[@data-cy='post-new-ad-button']")
    private WebElementFacade adaugaAnuntButton;

    @FindBy(className = "css-6wwl3n-BaseStyles")
    private WebElementFacade amInteles;

    @FindBy(xpath = "//button[@data-cy='ads-reposting-dismiss']")
    private WebElementFacade ignoraChestie;

    @FindBy(className = "onetrust-pc-dark-filter ot-fade-in")
    private WebElementFacade cookieFade;

    @FindBy(className = "css-kfy9sk")
    private WebElementFacade butonImobiliare;

    @FindBy(className = "css-hncutm")
    private WebElementFacade dropDownCategorie;

    @FindBy(xpath="//button[@data-categoryid='619']")
    private WebElementFacade categorieServicii;

    @FindBy(xpath="//li[@data-categoryid='901']")
    private WebElementFacade categorieServiciiPC;

    @FindBy(xpath = "//textarea[@data-testid='posting-title']")
    private WebElementFacade titluAnuntText;

    @FindBy(xpath = "//textarea[@data-testid='posting-description-text-area']")
    private WebElementFacade descriereAnuntText;

    @FindBy(xpath = "//button[@data-testid='submit-btn']")
    private WebElementFacade publicaAnuntButton;

    @FindBy(xpath = "//div[@data-testid='private_business_private_unactive']")
    private WebElementFacade persoanaFizica;

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
        adaugaAnuntButton.waitUntilEnabled();
        adaugaAnuntButton.click();
    }

    public void ziCaAiInteles() {
        amInteles.click();
    }

    public void ignoraChestie() {
        ignoraChestie.waitUntilClickable();
        ignoraChestie.click();
    }

    public void fillTitleField(String title){
        titluAnuntText.waitUntilClickable();
        titluAnuntText.sendKeys(title);
    }

    public void fillDescriptionField(String description){
        descriereAnuntText.sendKeys(description);
    }

    public void publicaAnunt(){
        publicaAnuntButton.click();
    }

    public void deschideCategorii(){
        dropDownCategorie.click();
    }

    public void alegeCateogrieServicii(){
        categorieServicii.click();
    }

    public void alegeCategorieServiciiPC(){
        categorieServiciiPC.click();
    }

    public void persoanaFizica(){
        persoanaFizica.click();
    }


}

