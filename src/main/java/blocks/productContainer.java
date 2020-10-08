package blocks;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;
import io.qameta.atlas.webdriver.extension.Param;
import org.openqa.selenium.NoSuchElementException;

public interface productContainer extends AtlasWebElement {

    @Name("Полное имя первого товара")
//    @FindBy("//*[@id='center_column']//*[@class='product-name']")
    @FindBy(".//*[@class='product-name']")
    AtlasWebElement ProductName();

    @Name("Кнопка Add to cart")
    @FindBy(".//*[contains(@class, 'button ajax_add_to_cart')]")
    AtlasWebElement addToCartButton();

    @Name("Цена товара. Элемент параметризован. В зависимости от параметра это или Старая цена <old-price> или Новая цена <price>")
    @FindBy(".//*[@class='right-block']//*[@class='{{ text }} product-price']")
    AtlasWebElement productPrice(@Param("text") String text);

    default Float getActualProductPrice(){
        try {
            return Float.parseFloat(productPrice("old-price").getText().substring(1));
        } catch (NoSuchElementException e) {
            return Float.parseFloat(productPrice("price").getText().substring(1));
        }
    }
}
