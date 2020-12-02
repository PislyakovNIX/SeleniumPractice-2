package com.automationpracticePislyakov2;

import envProperties.EnvProperties;
import org.testng.annotations.*;
import step.MainPageSteps;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

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
    @Parameters({"searchText"})
    public void simpleTest(String searchText) {
        // Мапа для сохранения имени и цены первого продукта
        Map<String, String> mapWithProductNameAndPrice = new HashMap<String, String>();

        MainPageSteps mainPageBaseSteps = new MainPageSteps(driver, atlas);
        EnvProperties envProperties = new EnvProperties();

        // 1. Открываем тестовый сайт
        //driver.get("http://automationpractice.com/index.php");
        driver.get(envProperties.getEnv());

        mainPageBaseSteps
                .makeSearch(searchText)
                .verifyingSearchPageTitle(searchText)
                .chooseOptionFromDropdown()
                .checkProductsSortHighestFirst()
                .rememberFirstNameAndPrice(mapWithProductNameAndPrice)
                .addFirstProductToTheCart()
                .compareNameAndPrice(mapWithProductNameAndPrice);
    }

    @Test
    public void womenCategoryTest() throws InterruptedException {

        MainPageSteps mainPageBaseSteps = new MainPageSteps(driver, atlas);
        EnvProperties envProperties = new EnvProperties();

        driver.get(envProperties.getEnv());

        mainPageBaseSteps
                .goToWomenPage()
                .verifyingFilterExisting("Categories")
                .verifyingFilterExisting("Size")
                .verifyingFilterExisting("Color")
                .verifyingFilterExisting("Compositions")
                .verifyingFilterExisting("Styles")
                .verifyingFilterExisting("Properties")
                .verifyingFilterExisting("Availability")
                .verifyingFilterExisting("Manufacturer")
                .verifyingFilterExisting("Condition")
                .verifyingFilterExisting("Price");
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
