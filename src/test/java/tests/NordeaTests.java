package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class NordeaTests extends TestBase {

    @Test
    @DisplayName("The main page correctly opened")
    void mainPageTest() {
        open("https://www.nordea.com/en/");

        $x("//a[contains(text(),'Accept all cookies')]").click();

        $("title").shouldHave(attribute("text", "Nordea Group – Nordic financial services | nordea.com "));
    }

}
