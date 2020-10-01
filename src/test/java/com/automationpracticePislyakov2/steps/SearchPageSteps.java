package com.automationpracticePislyakov2.steps;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.AtlasWebElement;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.FirstProductContainer;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPageSteps {
    public WebDriver driver;
    public Atlas atlas;

    public SearchPageSteps(WebDriver driver, Atlas atlas) {
        this.driver = driver;
        this.atlas = atlas;
    }
    //@Step("Нахождение тайтла (слова по которому был поиск) на странице результатов поиска")
//    public String searchResultTitleGet(SearchResultsPage searchResultsPage) {
//        String expectedSearchText = searchResultsPage.searchTitle().getText().replaceAll("\"", ""); ;
//        System.out.println(expectedSearchText);
//        return expectedSearchText;
//    }
    // Переделаем метод searchResultTitleGet на verifyingSearchPageTitle
    //@Step("Проверка, что тайтл на странице после поиска соответсвует поисковому слову")
    public SearchPageSteps verifyingSearchPageTitle(String actualSearchText) {
        String expectedSearchText = onSearchResultsPage().searchTitle().getText().replaceAll("\"", ""); ;
        Assert.assertEquals(actualSearchText.toUpperCase(), expectedSearchText);
        System.out.println("expectedSearchText: " + expectedSearchText);
        System.out.println("actualSearchText: " + actualSearchText);
        //return atlas.create(driver, SearchPageSteps.class);
        return new SearchPageSteps(driver, atlas);
    }

    //@Step("открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'")
    public SearchPageSteps chooseOptionFromDropdown() {
        onSearchResultsPage().dropdownSortBy().click();
        onSearchResultsPage().dropdownHighestPrice().click();
        return new SearchPageSteps(driver, atlas);
    }

    //@Step("Проверяем сортировку")
    public SearchPageSteps checkProductsSortHighestFirst() {
        List<AtlasWebElement> productContainerList = onSearchResultsPage().setProductContainers();
        List<Float> actualProductPriceList = productContainerList.stream().map(webElement -> {
            try {
                return Float.parseFloat(onSearchResultsPage().oldProductPrice().getText().substring(1));
            } catch (NoSuchElementException e) {
                return Float.parseFloat(onSearchResultsPage().newProductPrice().getText().substring(1));
            }
        }).collect(Collectors.toList());
        // Создаем копию массива цен
        List<Float> expectedProductPriceList = new ArrayList();
        expectedProductPriceList.addAll(actualProductPriceList);
        // Сортируем по убыванию копию массива цен
        Collections.sort(expectedProductPriceList, Collections.reverseOrder());
        // Сравним два списка
        Assert.assertEquals(expectedProductPriceList, actualProductPriceList);
        return new SearchPageSteps(driver, atlas);
    }

    //@Step("возвращает полное имя первого товара")
    public String getFirstProductFullName() {
        //return onSearchResultsPage().firstProductName().getText();
        return onFirstProductContainer().firstProductName().getText();
    }

    //@Step("возвращает цену первого товара")
    public String getFirstProductPrice() {
        //return onSearchResultsPage().firstProductPrice().getText();
        return onFirstProductContainer().firstProductPrice().getText();
    }

    //@Step("добавляем первый товар в корзину")
    public CartPageSteps addFirstProductToTheCart() {
        Actions action = new Actions(driver);
        //action.moveToElement(onSearchResultsPage().firstProductBlock());
        action.moveToElement(onSearchResultsPage().firstProductContainer());
        action.perform();
        onFirstProductContainer().addToCartButton().click();
        onFirstProductContainer().proceedToCheckoutButton().click();
        return new CartPageSteps(driver, atlas);
    }
    private SearchResultsPage onSearchResultsPage() {
        return atlas.create(driver, SearchResultsPage.class);
    }

    private FirstProductContainer onFirstProductContainer() {
        return atlas.create(driver, FirstProductContainer.class);
    }

}
