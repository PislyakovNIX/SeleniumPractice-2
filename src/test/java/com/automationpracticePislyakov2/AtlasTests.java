package com.automationpracticePislyakov2;

import EnvProperties.EnvProperties;
import org.testng.annotations.*;
import step.MainPageSteps;
import step.SearchPageSteps;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

//@RunWith(JUnitParamsRunner.class)
public class AtlasTests {

    // Мапа для сохранения имени и цены первого продукта
    Map<String, String> map = new HashMap<String, String>();

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
                .checkProductsSortHighestFirst()
                .rememberFirstNameAndPrice(map)
                .addFirstProductToTheCart()
                .compareNameAndPrice(map);
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
