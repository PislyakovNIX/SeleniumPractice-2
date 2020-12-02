package step;

import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class MainPageSteps extends BaseSteps {
    public MainPageSteps(WebDriver driver, Atlas atlas) {
        super(driver, atlas);
    }

    //@Step("Make search with input string «{input}»")
    public SearchPageSteps makeSearch(String input) {
        onMainPage().searchField().click();
        onMainPage().searchField().sendKeys(input);
        onMainPage().searchSubmitButton().click();
        return new SearchPageSteps(driver, atlas);
    }
    //@Step("Go to Women page")
    public WomenPageSteps goToWomenPage() {
        onMainPage().womenCategoryButton().click();
        return new WomenPageSteps(driver, atlas);

    }

    private MainPage onMainPage() {
        return atlas.create(driver, MainPage.class);
    }
}
