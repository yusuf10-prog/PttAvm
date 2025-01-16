package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;

import java.util.List;
import java.util.Set;
import java.time.Duration;

@Epic("PTT AVM Web Otomasyonu")
@Feature("Sosyal Medya İkonları Testi")
public class SocialMediaSteps {
    private static WebDriver driver;
    private HomePage homePage;
    private Scenario scenario;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
    }

    @Given("I am on the PTT AVM homepage")
    @Step("PTT AVM ana sayfasına git")
    public void i_am_on_the_ptt_avm_homepage() throws InterruptedException {
        driver.get("https://www.pttavm.com/");
        Thread.sleep(3000);
        takeScreenshot("Ana Sayfa");
    }

    @Then("I should see the following social media icons")
    @Step("Sosyal medya ikonlarını kontrol et")
    public void i_should_see_the_following_social_media_icons(List<String> socialMediaPlatforms) throws InterruptedException {
        Thread.sleep(2000);
        for (String platform : socialMediaPlatforms) {
            Assert.assertTrue(platform + " icon should be displayed",
                homePage.isSocialMediaIconDisplayed(platform));
            Thread.sleep(1000);
            takeScreenshot(platform + " İkonu Kontrolü");
        }
    }

    @When("I click on each social media icon")
    @Step("Tüm sosyal medya ikonlarına tıkla")
    public void i_click_on_each_social_media_icon() throws InterruptedException {
        List<String> platforms = List.of("Facebook", "Twitter", "Instagram", "YouTube");
        for (String platform : platforms) {
            Thread.sleep(2000);
            homePage.clickSocialMediaIcon(platform);
            takeScreenshot(platform + " İkonuna Tıklama");
        }
    }

    @Then("each social media page should open in a new tab")
    @Step("Sosyal medya sayfalarının yeni sekmede açıldığını doğrula")
    public void each_social_media_page_should_open_in_a_new_tab() throws InterruptedException {
        Thread.sleep(3000);
        Set<String> windowHandles = driver.getWindowHandles();
        Assert.assertTrue("Expected multiple tabs to be open", windowHandles.size() > 1);

        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            takeScreenshot("Sosyal Medya Sayfası - " + currentUrl);
            Assert.assertTrue("URL should contain a social media domain",
                currentUrl.contains("facebook.com") ||
                currentUrl.contains("twitter.com") ||
                currentUrl.contains("instagram.com") ||
                currentUrl.contains("youtube.com"));
        }
    }

    @Attachment(value = "{attachmentName}", type = "image/png")
    public byte[] takeScreenshot(String attachmentName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot("Hata Durumu Screenshot");
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
