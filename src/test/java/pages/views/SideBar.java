package pages.views;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SideBar {

    public SelenideElement userName() {
        return $(".user .info span");
    }
}
