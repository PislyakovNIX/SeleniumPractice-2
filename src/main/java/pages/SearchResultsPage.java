package pages;

import blocks.ProductContainer;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;
import io.qameta.atlas.webdriver.extension.Param;

public interface SearchResultsPage extends WebPage {

    @Name("Заголовок с введенным поисковым словом")
    @FindBy("//*[@class='lighter']")
    AtlasWebElement searchTitle();

    @Name("Дропдаун “Sort by”")
    @FindBy("//*[@id='selectProductSort']")
    AtlasWebElement dropdownSortBy();

    @Name("Элемент дропдауна 'Price: Highest first'")
    @FindBy("//*[@id='selectProductSort']/option[contains(text(),'Price: Highest first')]")
    AtlasWebElement dropdownHighestPrice();

    @Name("Коллекция веб элементов продуктовых контейнеров (как коллекция первых контейнеров)")
    @FindBy("//*[@class='product-container']")
    ElementsCollection<ProductContainer> setProductContainers();

    @Name("Кнопка Proceed to checkout")
    @FindBy("//*[@class='btn btn-default button button-medium']")
    AtlasWebElement proceedToCheckoutButton();

    // Это добавлено для вынесения блоков
    @Name("Первый продуктовый контейнер")
    @FindBy("//*[@class='product-container']")
    ProductContainer ProductContainer();
}
