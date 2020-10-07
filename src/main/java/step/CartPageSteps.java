package step;

import io.qameta.atlas.core.Atlas;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class CartPageSteps extends BaseSteps {

    public CartPageSteps(WebDriver driver, Atlas atlas) {
        super(driver, atlas);
    }

    //@Step("Сравниваем сравниваем название и цену в колонке "Total" у товара, на соответствие с сохраненными значениями")
    public void compareNameAndPrice(String expectedName, String expectedPrice) {
        String actualName = onCartPage().productName().getText();
        String actualPrice = onCartPage().productPrice().getText();
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedPrice, actualPrice);
    }

    private CartPage onCartPage() {
        return atlas.create(driver, CartPage.class);
    }
}
