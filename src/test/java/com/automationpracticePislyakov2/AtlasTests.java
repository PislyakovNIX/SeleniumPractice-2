package com.automationpracticePislyakov2;

import org.testng.annotations.*;
import step.BaseSteps;
import step.CartPageSteps;
import step.MainPageSteps;
import step.SearchPageSteps;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//@RunWith(JUnitParamsRunner.class)
public class AtlasTests {

    public WebDriver driver;
    public Atlas atlas;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        atlas = new Atlas(new WebDriverConfiguration(driver));
    }

    @Test
    //@Parameters({"Summer", "Dress", "t-shirt"})
    @Parameters({"searchText"})
    public void simpleTest(String searchText) {

        MainPageSteps mainPageBaseSteps = new MainPageSteps(driver, atlas);
        SearchPageSteps searchPageSteps = new SearchPageSteps(driver, atlas);
        EnvProperties envProperties = new EnvProperties();

        // 1. Открываем тестовый сайт
        //driver.get("http://automationpractice.com/index.php");
        driver.get(envProperties.getEnv());

        mainPageBaseSteps
                .makeSearch(searchText)
                .verifyingSearchPageTitle(searchText)
                .chooseOptionFromDropdown()
                .checkProductsSortHighestFirst();

        // Запоминаем имя и цену первого товара
        String firstProductFullName = searchPageSteps.getFirstProductFullName();
        String firstProductPrice = searchPageSteps.getFirstProductPrice();

        searchPageSteps
                .addFirstProductToTheCart()
                .compareNameAndPrice(firstProductFullName, firstProductPrice);
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
