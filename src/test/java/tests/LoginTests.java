package tests;

import common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class LoginTests extends BaseTest {

    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"daniel@ninjaplus.com", "123456incorreto", "Usuário e/ou senha inválidos"},
                {"incorreto@ninjaplus.com", "123456abc@", "Usuário e/ou senha inválidos"},
                {"", "123456abc@", "Opps. Cadê o email?"},
                {"daniel@ninjaplus.com", "", "Opps. Cadê a senha?"},
        };
    }

    @Test
    public void shouldSeeLoggedUser() {
        login
                .open()
                .with("daniel@ninjaplus.com", "123456abc@");
        side.userName().shouldHave(text("daniel"));
    }

    @Test(dataProvider = "login-alerts")
    public void shouldSeeLoginAlerts(String email, String pass, String expectAlert) {
        login
                .open()
                .with(email, pass)
                .alert().shouldHave(text(expectAlert));
    }
}
