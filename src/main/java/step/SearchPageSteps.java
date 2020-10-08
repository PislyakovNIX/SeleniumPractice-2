package step;

import blocks.ProductContainer;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchPageSteps extends BaseSteps {

    public SearchPageSteps(WebDriver driver, Atlas atlas) {
        super(driver, atlas);
    }

    //@Step("Проверка, что тайтл на странице после поиска соответсвует поисковому слову")
    public SearchPageSteps verifyingSearchPageTitle(String actualSearchText) {
        String expectedSearchText = onSearchResultsPage().searchTitle().getText().replaceAll("\"", "");
        assertThat(actualSearchText.toUpperCase(), equalTo(expectedSearchText));
        System.out.println("expectedSearchText: " + expectedSearchText);
        System.out.println("actualSearchText: " + actualSearchText);
        return this;
    }

    //@Step("открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'")
    public SearchPageSteps chooseOptionFromDropdown() {
        onSearchResultsPage().dropdownSortBy().click();
        onSearchResultsPage().dropdownHighestPrice().click();
        return this;
    }

    //@Step("Проверяем сортировку")
    public SearchPageSteps checkProductsSortHighestFirst() {
        ElementsCollection<ProductContainer> productContainerList = onSearchResultsPage().setProductContainers();
        List<Float> actualProductPriceList = productContainerList.stream().map(webElement -> onSearchResultsPage().
                ProductContainer().getActualProductPrice(webElement)).collect(Collectors.toList());
        // Создаем копию массива цен
        List<Float> expectedProductPriceList = new ArrayList();
        expectedProductPriceList.addAll(actualProductPriceList);
        // Сортируем по убыванию копию массива цен
        Collections.sort(expectedProductPriceList, Collections.reverseOrder());
        // Сравним два списка
        Assert.assertEquals(expectedProductPriceList, actualProductPriceList);
        return this;
    }

    public SearchPageSteps rememberFirstNameAndPrice(Map<String, String> stringMap) {
        stringMap.put(onSearchResultsPage().ProductContainer().ProductName().getText(),
                onSearchResultsPage().ProductContainer().productPrice("price").getText());
        return this;
    }

    //@Step("добавляем первый товар в корзину")
    public CartPageSteps addFirstProductToTheCart() {
        Actions action = new Actions(driver);
        action.moveToElement(onSearchResultsPage().ProductContainer());
        action.perform();
        onSearchResultsPage().ProductContainer().addToCartButton().click();
        onSearchResultsPage().proceedToCheckoutButton().click();
        return new CartPageSteps(driver, atlas);
    }

    private SearchResultsPage onSearchResultsPage() {
        return atlas.create(driver, SearchResultsPage.class);
    }
}
