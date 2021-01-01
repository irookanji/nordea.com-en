package pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
  private static final String URL = "https://www.nordea.com/en/";

  private SelenideElement acceptCookieButton = $x("//a[contains(text(),'Accept all cookies')]");
  private SelenideElement menu = $("[data-wa-component=menu] nav[role=navigation]");
  private SelenideElement careersButton = $("[data-wa-menu-1=Careers]");

  @Step
  public void acceptCookie() {
    acceptCookieButton.click();
  }

  @Step
  public static MainPage open() {
    return Selenide.open(URL, MainPage.class);
  }

  @Step
  public SelenideElement findByNameInMenu(String name) {
    return menu.find(Selectors.byText(name));
  }

  @Step
  public CareersPage goToCareersPage() {
    careersButton.click();
    return new CareersPage();
  }
}
