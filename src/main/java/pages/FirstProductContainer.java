package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;

public interface FirstProductContainer extends AtlasWebElement {
    @Name("Полное имя первого товара")
    @FindBy("//*[@id='center_column']//*[@class='product-name']")
    AtlasWebElement firstProductName();

    @Name("Цена первого товара")
    @FindBy("//*[@class='right-block']//*[@class='price product-price']")
    AtlasWebElement firstProductPrice();

    @Name("Кнопка Add to cart")
    @FindBy("//*[contains(@class, 'button ajax_add_to_cart')]")
    AtlasWebElement addToCartButton();

    @Name("Кнопка Proceed to checkout")
    @FindBy("//*[@class='btn btn-default button button-medium']")
    AtlasWebElement proceedToCheckoutButton();

}
