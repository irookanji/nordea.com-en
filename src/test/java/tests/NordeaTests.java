package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CareersPage;
import pages.MainPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NordeaTests extends TestBase {

  private MainPage mainPage;
  private CareersPage careersPage;

  @BeforeEach
  public void setUp() {
    mainPage = MainPage.open();
    mainPage.acceptCookie();
  }

  @Test
  @DisplayName("The main page correctly opened")
  void mainPageTest() {

    // Asserts
    $("title")
        .shouldHave(attribute("text", "Nordea Group – Nordic financial services | nordea.com "));
    $("img[alt*='Nordea logo']").shouldBe(Condition.visible);
  }

  @DisplayName("Menu tabs are visible")
  @ParameterizedTest
  @ValueSource(strings = {"About Nordea", "Careers", "Our services"})
  void checkMenuTabsNamesTest(String menu) {

    // Assert
    mainPage.findByNameInMenu(menu).shouldBe(Condition.visible);
  }

  @Test
  @DisplayName("Job selection search is working correctly")
  void findJobPositionTest() {

    careersPage = mainPage.goToCareersPage();
    careersPage.chooseCountry();
    careersPage.chooseCity();
    careersPage.chooseCareerArea();
    careersPage.clickSearchButton();

    // Asserts
    $("#content-start").shouldHave(text("Vacant positions"));
    $("body").shouldHave(text("Senior QA Engineer, Stockholm"));
  }

  @Test
  @DisplayName("The required vacancy opens")
  void checkJobDetailsTest() {

    careersPage = mainPage.goToCareersPage();
    careersPage.chooseCountry();
    careersPage.chooseCity();
    careersPage.chooseCareerArea();
    careersPage.clickSearchButton();
    careersPage.chooseJobTitle();

    // Asserts
    $("#content-start").shouldHave(text("Senior QA Engineer, Stockholm"));
    $x("//img[@class='job-details__image']").shouldBe(Condition.visible);
    $x("//h2[contains(text(),'About this opportunity')]").shouldBe(Condition.visible);
  }
}
