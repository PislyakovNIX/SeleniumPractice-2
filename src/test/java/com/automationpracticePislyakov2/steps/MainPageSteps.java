package com.automationpracticePislyakov2.steps;

import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.SearchResultsPage;

public class MainPageSteps {

    public WebDriver driver;
    public Atlas atlas;

    public MainPageSteps(WebDriver driver, Atlas atlas) {
        this.driver = driver;
        this.atlas = atlas;
    }

    //@Step("Make search with input string «{input}»")
    public SearchPageSteps makeSearch(String input) {
        onMainPage().searchField().click();
        onMainPage().searchField().sendKeys(input);
        onMainPage().searchSubmitButton().click();
        //return atlas.create(driver, SearchResultsPage.class);
        return new SearchPageSteps(driver, atlas);
    }

    private MainPage onMainPage() {
        return atlas.create(driver, MainPage.class);
    }

}
