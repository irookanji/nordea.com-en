package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class NordeaTests extends TestBase {

  @Test
  @DisplayName("The main page correctly opened")
  void mainPageTest() {
    open("https://www.nordea.com/en/");
    $x("//a[contains(text(),'Accept all cookies')]").click();

    $("title")
        .shouldHave(attribute("text", "Nordea Group – Nordic financial services | nordea.com "));
  }

  @DisplayName("Menu is displayed")
  @ParameterizedTest
  @ValueSource(strings = {"About Nordea", "Careers", "Our services"})
  void parametrizedMenuTest(String menu) {
    open("https://www.nordea.com/en/");
    $x("//a[contains(text(),'Accept all cookies')]").click();

    $("[data-wa-component=menu] nav[role=navigation]")
        .find(Selectors.byText(menu))
        .shouldBe(Condition.visible);
  }
}
