package com.automationpracticePislyakov2;

import com.automationpracticePislyakov2.steps.BaseSteps;
import com.automationpracticePislyakov2.steps.CartPageSteps;
import com.automationpracticePislyakov2.steps.MainPageSteps;
import com.automationpracticePislyakov2.steps.SearchPageSteps;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchResultsPage;

@RunWith(JUnitParamsRunner.class)
public class AtlasTests {

    public WebDriver driver;
    public Atlas atlas;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        atlas = new Atlas(new WebDriverConfiguration(driver));
    }

    @Test
    @Parameters({"Summer", "Dress", "t-shirt"})
    public void simpleTest(String searchText) throws InterruptedException {

        //String searchText = "Summer";
        String expectedSearchText;

        BaseSteps steps = new BaseSteps(driver, atlas);
        MainPageSteps mainPageBaseSteps = new MainPageSteps(driver, atlas);
        SearchPageSteps searchPageSteps = new SearchPageSteps(driver, atlas);
        CartPageSteps cartPageSteps = new CartPageSteps(driver, atlas);


        // 1. Открываем сайт
        driver.get("http://automationpractice.com/index.php");

        // 2-3. Вводим слово для поиска и проверяем, что над списком продуктов в надписи 'SEARCH' отображается наш поисковый запрос - "SUMMER"
//        expectedSearchText = searchPageSteps.searchResultTitleGet(mainPageBaseSteps.makeSearch(searchText));
//        Assert.assertEquals(searchText.toUpperCase(), expectedSearchText);
        // Переделываем на цепочку методов
        mainPageBaseSteps
                .makeSearch(searchText)
                .verifyingSearchPageTitle(searchText)
                .chooseOptionFromDropdown()
                .checkProductsSortHighestFirst();
        //mainPageBaseSteps.makeSearch(searchText).verifyingSearchPageTitle(m, searchText);

        // 4. открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
        //searchPageSteps.chooseOptionFromDropdown();

        // 5. проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт по старой цене - если у товар есть скидка, нужно смотреть на старую цену)
        //searchPageSteps.checkProductsSortHighestFirst();

        // 6. берем первый из найденных товаров и запоминаем его полное название и цену
        String firstProductFullName = searchPageSteps.getFirstProductFullName();
        String firstProductPrice = searchPageSteps.getFirstProductPrice();

        // 7. Добавляем первый товар в корзину
        searchPageSteps
                .addFirstProductToTheCart()
                .compareNameAndPrice(firstProductFullName, firstProductPrice);
        //Thread.sleep(20000);

        // Сравниваем название и цену
        //cartPageSteps.compareNameAndPrice(firstProductFullName, firstProductPrice);
    }

    @After
    public void close() {
        driver.quit();
    }
}
