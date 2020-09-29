package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;

public interface CartPage extends WebPage {

    @Name("Имя продукта")
    @FindBy("//*[@class='cart_description']//*[@class='product-name']")
    AtlasWebElement productNameOnCartPage();

    @Name("Цена продукта")
    @FindBy("//*[@class='cart_total']")
    AtlasWebElement productPriceOnCartPage();

}
