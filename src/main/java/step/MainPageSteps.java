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

    private MainPage onMainPage() {
        return atlas.create(driver, MainPage.class);
    }
}
