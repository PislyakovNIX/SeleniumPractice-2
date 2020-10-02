package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;
import io.qameta.atlas.webdriver.extension.Param;

import java.util.List;

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

    @Name("Коллекция веб элементов продуктовых контейнеров")
    @FindBy("//*[@class='product-container']")
    List<AtlasWebElement> setProductContainers();

    // Это добавлено для вынесения блоков
    @Name("Первый продуктовый контейнер")
    @FindBy("//*[@class='product-container']")
    FirstProductContainer firstProductContainer();

//    @Name("Старая цена товара")
//    @FindBy(".//*[@class='right-block']//*[@class='old-price product-price']")
//    AtlasWebElement oldProductPrice();
//
//    @Name("Новая цена товара")
//    @FindBy(".//*[@class='right-block']//*[@class='price product-price']")
//    AtlasWebElement newProductPrice();

    @Name("Цена товара. Элемент параметризован. В зависимости от параметра это или Старая цена или Новая цена")
    @FindBy(".//*[@class='right-block']//*[@class='{{ text }} product-price']")
    AtlasWebElement productPrice(@Param("text") String text);
}
