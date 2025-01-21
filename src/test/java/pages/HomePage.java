package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href*='facebook.com']")
    private WebElement facebookIcon;

    @FindBy(css = "a[href*='twitter.com']")
    private WebElement twitterIcon;

    @FindBy(css = "a[href*='instagram.com']")
    private WebElement instagramIcon;

    @FindBy(css = "a[href*='youtube.com']")
    private WebElement youtubeIcon;

    @FindBy(css = "a[href*='/elektronik']")
    private WebElement elektronikCategory;

    @FindBy(css = "a[href*='/kozmetik']")
    private WebElement kozmetikCategory;

    public boolean isSocialMediaIconDisplayed(String platform) {
        switch (platform.toLowerCase()) {
            case "facebook":
                return facebookIcon.isDisplayed();
            case "twitter":
                return twitterIcon.isDisplayed();
            case "instagram":
                return instagramIcon.isDisplayed();
            case "youtube":
                return youtubeIcon.isDisplayed();
            default:
                return false;
        }
    }

    public void clickSocialMediaIcon(String platform) {
        switch (platform.toLowerCase()) {
            case "facebook":
                facebookIcon.click();
                break;
            case "twitter":
                twitterIcon.click();
                break;
            case "instagram":
                instagramIcon.click();
                break;
            case "youtube":
                youtubeIcon.click();
                break;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCategory(String categoryName) {
        switch (categoryName.toLowerCase()) {
            case "elektronik":
                elektronikCategory.click();
                break;
            case "kozmetik":
                kozmetikCategory.click();
                break;
            default:
                throw new IllegalArgumentException("Category " + categoryName + " not found");
        }
    }

    public boolean isCategoryPageDisplayed(String categoryName) {
        return driver.getCurrentUrl().toLowerCase().contains(categoryName.toLowerCase());
    }


    public void navigateToHomepage() {
        driver.get("https://www.pttavm.com/");
    }
}
