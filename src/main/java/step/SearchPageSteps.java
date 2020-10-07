package step;

import blocks.FirstProductContainer;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPageSteps extends BaseSteps {

    public SearchPageSteps(WebDriver driver, Atlas atlas) {
        super(driver, atlas);
    }

    //@Step("Проверка, что тайтл на странице после поиска соответсвует поисковому слову")
    public SearchPageSteps verifyingSearchPageTitle(String actualSearchText) {
        String expectedSearchText = onSearchResultsPage().searchTitle().getText().replaceAll("\"", "");
        Assert.assertEquals(actualSearchText.toUpperCase(), expectedSearchText);
        System.out.println("expectedSearchText: " + expectedSearchText);
        System.out.println("actualSearchText: " + actualSearchText);
        return this;
//        return new SearchPageSteps(driver, atlas);
    }

    //@Step("открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'")
    public SearchPageSteps chooseOptionFromDropdown() {
        onSearchResultsPage().dropdownSortBy().click();
        onSearchResultsPage().dropdownHighestPrice().click();
        return this;
//        return new SearchPageSteps(driver, atlas);
    }

    //@Step("Проверяем сортировку")
    public SearchPageSteps checkProductsSortHighestFirst() {
//        List<AtlasWebElement> productContainerList = onSearchResultsPage().setProductContainers();
        ElementsCollection<FirstProductContainer> productContainerList = onSearchResultsPage().setProductContainers();
        List<Float> actualProductPriceList = productContainerList.stream().map(webElement -> {
            try {
                return Float.parseFloat(onSearchResultsPage().productPrice("old-price").getText().substring(1));
            } catch (NoSuchElementException e) {
                return Float.parseFloat(onSearchResultsPage().productPrice("price").getText().substring(1));
            }
        }).collect(Collectors.toList());
        // Создаем копию массива цен
        List<Float> expectedProductPriceList = new ArrayList();
        expectedProductPriceList.addAll(actualProductPriceList);
        // Сортируем по убыванию копию массива цен
        Collections.sort(expectedProductPriceList, Collections.reverseOrder());
        // Сравним два списка
        Assert.assertEquals(expectedProductPriceList, actualProductPriceList);
        return this;
//        return new SearchPageSteps(driver, atlas);
    }

    //@Step("возвращает полное имя первого товара")
    public String getFirstProductFullName() {
        return onSearchResultsPage().firstProductContainer().firstProductName().getText();
    }

    //@Step("возвращает цену первого товара")
    public String getFirstProductPrice() {
        return onSearchResultsPage().firstProductContainer().firstProductPrice().getText();
    }

    //@Step("добавляем первый товар в корзину")
    public CartPageSteps addFirstProductToTheCart() {
        Actions action = new Actions(driver);
        action.moveToElement(onSearchResultsPage().firstProductContainer());
        action.perform();
        onSearchResultsPage().firstProductContainer().addToCartButton().click();
        onSearchResultsPage().firstProductContainer().proceedToCheckoutButton().click();
        return new CartPageSteps(driver, atlas);
    }

    private SearchResultsPage onSearchResultsPage() {
        return atlas.create(driver, SearchResultsPage.class);
    }

//    private FirstProductContainer onfirstProductContainer() {
//        return atlas.create(driver, FirstProductContainer.class);
//    }
}
