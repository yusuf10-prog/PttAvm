package stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.HomePage;

@Epic("PTT AVM Web Otomasyonu")
@Feature("Kategori Navigasyonu Testi")
public class CategorySteps {
    private HomePage homePage;

    public CategorySteps() {
        this.homePage = new HomePage(SocialMediaSteps.getDriver());
    }

    @When("I click on {string} category")
    @Step("{string} kategorisine tıkla")
    @Severity(SeverityLevel.CRITICAL)
    public void i_click_on_category(String categoryName) throws InterruptedException {
        Thread.sleep(2000);
        homePage.clickCategory(categoryName);
        takeScreenshot(categoryName + " Kategorisine Tıklama");
        Thread.sleep(2000);
    }

    @Then("I should see the electronics page")
    @Step("Elektronik sayfasının açıldığını doğrula")
    @Severity(SeverityLevel.NORMAL)
    public void i_should_see_the_electronics_page() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue("Should be on electronics page",
            homePage.isCategoryPageDisplayed("elektronik"));
        takeScreenshot("Elektronik Sayfası Kontrolü");
    }

    @Then("I should see the cosmetics page")
    @Step("Kozmetik sayfasının açıldığını doğrula")
    @Severity(SeverityLevel.NORMAL)
    public void i_should_see_the_cosmetics_page() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue("Should be on cosmetics page",
            homePage.isCategoryPageDisplayed("kozmetik"));
        takeScreenshot("Kozmetik Sayfası Kontrolü");
    }

    @When("I navigate back to homepage")
    @Step("Ana sayfaya geri dön")
    @Severity(SeverityLevel.NORMAL)
    public void i_navigate_back_to_homepage() throws InterruptedException {
        Thread.sleep(2000);
        homePage.navigateToHomepage();
        takeScreenshot("Ana Sayfaya Dönüş");
        Thread.sleep(2000);
    }

    @Attachment(value = "{attachmentName}", type = "image/png")
    public byte[] takeScreenshot(String attachmentName) {
        return ((TakesScreenshot) SocialMediaSteps.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
