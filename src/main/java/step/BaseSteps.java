package step;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.AtlasWebElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.FirstProductContainer;
import pages.MainPage;
import pages.SearchResultsPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BaseSteps {

    public WebDriver driver;
    public Atlas atlas;

    public BaseSteps(WebDriver driver, Atlas atlas) {
        this.driver = driver;
        this.atlas = atlas;
    }

//    //@Step("Make search with input string «{input}»")
//    public SearchResultsPage makeSearch(String input) {
//        onMainPage().searchField().click();
//        onMainPage().searchField().sendKeys(input);
//        onMainPage().searchSubmitButton().click();
//        return atlas.create(driver, SearchResultsPage.class);
//    }

//    //@Step("Нахождение тайтла (слова по которому был поиск) на странице результатов поиска")
//    public String searchResultTitleGet(SearchResultsPage searchResultsPage) {
//        String expectedSearchText = searchResultsPage.searchTitle().getText().replaceAll("\"", ""); ;
//        System.out.println(expectedSearchText);
//        return expectedSearchText;
//    }
//
//    //@Step("открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'")
//    public void chooseOptionFromDropdown() {
//        onSearchResultsPage().dropdownSortBy().click();
//        onSearchResultsPage().dropdownHighestPrice().click();
//    }
//
//    //@Step("Проверяем сортировку")
//    public void checkProductsSortHighestFirst() {
//        List<AtlasWebElement> productContainerList = onSearchResultsPage().setProductContainers();
//        List<Float> actualProductPriceList = productContainerList.stream().map(webElement -> {
//            try {
//                return Float.parseFloat(onSearchResultsPage().oldProductPrice().getText().substring(1));
//            } catch (NoSuchElementException e) {
//                return Float.parseFloat(onSearchResultsPage().newProductPrice().getText().substring(1));
//            }
//        }).collect(Collectors.toList());
//        // Создаем копию массива цен
//        List<Float> expectedProductPriceList = new ArrayList();
//        expectedProductPriceList.addAll(actualProductPriceList);
//        // Сортируем по убыванию копию массива цен
//        Collections.sort(expectedProductPriceList, Collections.reverseOrder());
//        // Сравним два списка
//        Assert.assertEquals(expectedProductPriceList, actualProductPriceList);
//    }
//
//    //@Step("возвращает полное имя первого товара")
//    public String getFirstProductFullName() {
//        //return onSearchResultsPage().firstProductName().getText();
//        return onFirstProductContainer().firstProductName().getText();
//    }
//
//    //@Step("возвращает цену первого товара")
//    public String getFirstProductPrice() {
//        //return onSearchResultsPage().firstProductPrice().getText();
//        return onFirstProductContainer().firstProductPrice().getText();
//    }
//
//    //@Step("добавляем первый товар в корзину")
//    public void addFirstProductToTheCart() {
//        Actions action = new Actions(driver);
//        //action.moveToElement(onSearchResultsPage().firstProductBlock());
//        action.moveToElement(onSearchResultsPage().firstProductContainer());
//        action.perform();
//        onFirstProductContainer().addToCartButton().click();
//        onFirstProductContainer().proceedToCheckoutButton().click();
//    }

//    //@Step("Сравниваем сравниваем название и цену в колонке "Total" у товара, на соответствие с сохраненными значениями")
//    public void compareNameAndPrice(String expectedName, String expectedPrice) {
//        String actualName = onCartPage().productNameOnCartPage().getText();
//        String actualPrice = onCartPage().productPriceOnCartPage().getText();
//        Assert.assertEquals(expectedName, actualName);
//        Assert.assertEquals(expectedPrice, actualPrice);
//    }


//    private SearchResultsPage onSearchResultsPage() {
//        return atlas.create(driver, SearchResultsPage.class);
//    }

//    private CartPage onCartPage() {
//        return atlas.create(driver, CartPage.class);
//    }

//    private FirstProductContainer onFirstProductContainer() {
//        return atlas.create(driver, FirstProductContainer.class);
//    }

}