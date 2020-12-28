package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class NordeaTests extends TestBase {

  @Test
  @DisplayName("The main page correctly opened")
  void mainPageTest() {
    open("https://www.nordea.com/en/");
    $x("//a[contains(text(),'Accept all cookies')]").click();

    // Assert
    $("title")
        .shouldHave(attribute("text", "Nordea Group – Nordic financial services | nordea.com "));
  }

  @DisplayName("Menu is displayed")
  @ParameterizedTest
  @ValueSource(strings = {"About Nordea", "Careers", "Our services"})
  void parametrizedMenuTest(String menu) {
    open("https://www.nordea.com/en/");
    $x("//a[contains(text(),'Accept all cookies')]").click();

    // Assert
    $("[data-wa-component=menu] nav[role=navigation]")
        .find(Selectors.byText(menu))
        .shouldBe(Condition.visible);
  }

  @Test
  @DisplayName("Job selection search is working correctly")
  void careersSearchTest() {
    open("https://www.nordea.com/en/");
    $x("//a[contains(text(),'Accept all cookies')]").click();

    $("[data-wa-menu-1=Careers]").click();

    $("#job-search-select-cty").click();
    $x("//select[@id='job-search-select-cty']//option[contains(text(), 'Sweden')]").click();

    $("#job-search-select-geo").click();
    $x("//select[@id='job-search-select-geo']//option[contains(text(), 'Stockholm')]").click();

    $("#job-search-select-area").click();
    $x("//select[@id='job-search-select-area']//option[contains(text(), 'IT')]").click();
    $x("//button[contains(text(),'Search')]").click();

    // Asserts
    $("#content-start").shouldHave(text("Vacant positions"));
    $("body").shouldHave(text("Senior QA Engineer, Stockholm"));
  }

  @Test
  @DisplayName("The required vacancy opens")
  void openRequiredVacancyTest() {
    open("https://www.nordea.com/en/");
    $x("//a[contains(text(),'Accept all cookies')]").click();

    $("[data-wa-menu-1=Careers]").click();

    $("#job-search-select-cty").click();
    $x("//select[@id='job-search-select-cty']//option[contains(text(), 'Sweden')]").click();

    $("#job-search-select-geo").click();
    $x("//select[@id='job-search-select-geo']//option[contains(text(), 'Stockholm')]").click();

    $("#job-search-select-area").click();
    $x("//select[@id='job-search-select-area']//option[contains(text(), 'IT')]").click();
    sleep(1000);
    $x("//button[contains(text(),'Search')]").click();

    $x("//tr[@class='job-item']//a[contains(text(),'Senior QA Engineer, Stockholm')]").click();

    // Asserts
    $("#content-start").shouldHave(text("Senior QA Engineer, Stockholm"));
    $x("//img[@class='job-details__image']").shouldBe(Condition.visible);
    $x("//h2[contains(text(),'About this opportunity')]").shouldBe(Condition.visible);
  }
}
