package tests;

import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

public class HelloSelenideTest extends BaseTest {

    @Test
    public void onAir() {
        isChrome();
        open("http://ninjaplus-web:5000");
        Assert.assertEquals(title(), "Ninja+");
    }
}
