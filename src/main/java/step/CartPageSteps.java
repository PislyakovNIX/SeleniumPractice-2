package step;

import io.qameta.atlas.core.Atlas;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.SearchResultsPage;

import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CartPageSteps extends BaseSteps {

    public CartPageSteps(WebDriver driver, Atlas atlas) {
        super(driver, atlas);
    }

    //@Step("Сравниваем сравниваем название и цену в колонке "Total" у товара, на соответствие с сохраненными значениями")
    public void compareNameAndPrice(Map<String, String> stringMap) {
        String actualName = onCartPage().productName().getText();
        String actualPrice = onCartPage().productPrice().getText();
        Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> pair = iterator.next();
            String key = pair.getKey(); //ключ
            String value = pair.getValue(); //значение
            assertThat(key, equalTo(actualName));
            assertThat(value, equalTo(actualPrice));
        }
    }
    private CartPage onCartPage() {
        return atlas.create(driver, CartPage.class);
    }
}
