package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.OLXPage;

public class UserSteps {

    OLXPage olxPage;

    @Step
    public void enter_login_page() {
        olxPage.pressContulMeu();
    }

    @Step
    public void fillUsername(String username) {
        olxPage.fillUsernameField(username);
    }

    @Step
    public void fillPassword(String pass) {
        olxPage.fillPasswordField(pass);
    }

    @Step
    public void is_the_home_page() {
        olxPage.open();
    }

    @Step
    public void acceptCookies() {
        olxPage.acceptCookies();
    }

    @Step
    public void pressLogin() {
        olxPage.pressLogin();
    }

    @Step
    public void pressAdaugaAnunt() {
        olxPage.pressAdaugaAnunt();
    }

    @Step
    public void ziCaAiInteles() {
        olxPage.ziCaAiInteles();
    }

    @Step
    public void ignoraChestie() {
        olxPage.ignoraChestie();
    }

    @Step
    public void inAsteptare() {
        olxPage.inAsteptare();
    }

    @Step
    public void active(){
        olxPage.active();
    }

    @Step
    public void dezactivezaAnunt() {
        olxPage.dezactiveazaAnunt();
    }
}
