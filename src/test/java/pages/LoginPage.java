package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public class LoginPage {

    public void clearSession() {
//                executeJavaScript("localStorage.clear();"); => pode ser usado no lugar de clearBrowserLocalStorage()
        clearBrowserLocalStorage();
    }

    public LoginPage open() {
        Selenide.open("/login");
        return this;
    }

    public LoginPage with(String email, String pass) {
        $("input[name=\"email\"]").setValue(email);
        $("#passId").setValue(pass);
        $(byText("Entrar")).click();
        return this;
    }

    public SelenideElement alert() {
        return $(".alert span");
    }
}
