package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;

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

    @Name("Старая цена товара")
    @FindBy(".//*[@class='right-block']//*[@class='old-price product-price']")
    AtlasWebElement oldProductPrice();

    @Name("Новая цена товара")
    @FindBy(".//*[@class='right-block']//*[@class='price product-price']")
    AtlasWebElement newProductPrice();

}
