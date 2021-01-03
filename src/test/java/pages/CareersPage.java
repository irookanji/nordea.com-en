package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CareersPage {

  private SelenideElement chooseCountryDropDown = $("#job-search-select-cty");
  private SelenideElement countrySelect =
      $x("//select[@id='job-search-select-cty']//option[contains(text(), 'Sweden')]");
  private SelenideElement chooseCityDropDown = $("#job-search-select-geo");
  private SelenideElement citySelect =
      $x("//select[@id='job-search-select-geo']//option[contains(text(), 'Stockholm')]");
  private SelenideElement chooseCareerAreaDropDown = $("#job-search-select-area");
  private SelenideElement careerAreaSelect =
      $x("//select[@id='job-search-select-area']//option[contains(text(), 'IT')]");
  private SelenideElement searchButton = $x("//button[contains(text(),'Search')]");
  private SelenideElement jobTitle =
      $x("//tr[@class='job-item']//a[contains(text(),'Senior QA Engineer, Stockholm')]");

  @Step("Choose country")
  public void chooseCountry() {
    chooseCountryDropDown.click();
    countrySelect.click();
  }

  @Step("Choose city")
  public void chooseCity() {
    chooseCityDropDown.click();
    citySelect.click();
  }

  @Step("Choose Career Area")
  public void chooseCareerArea() {
    chooseCareerAreaDropDown.click();
    careerAreaSelect.click();
  }

  @Step("Click Search button")
  public void clickSearchButton() {
    searchButton.click();
  }

  @Step("Choose job title")
  public void chooseJobTitle() {
    jobTitle.click();
  }
}
