package step;

import io.qameta.atlas.core.Atlas;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.WomenPage;

import static matchers.HasText.hasText;

public class WomenPageSteps extends BaseSteps {
    public WomenPageSteps(WebDriver driver, Atlas atlas) {
        super(driver, atlas);
    }

    //@Step("Проверка что в названии фильтра есть определенное слово, т.е. проверка что определнный фильтр есть на странице")
    public WomenPageSteps verifyingFilterExisting(String filterName) {
        onWomenPage().filterTitle(filterName).should(hasText(filterName));
        return this;
    }

    private MainPage onMainPage() {
        return atlas.create(driver, MainPage.class);
    }

    private WomenPage onWomenPage() {
        return atlas.create(driver, WomenPage.class);
    }
}
