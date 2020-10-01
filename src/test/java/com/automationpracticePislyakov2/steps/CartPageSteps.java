package com.automationpracticePislyakov2.steps;

import io.qameta.atlas.core.Atlas;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class CartPageSteps {
    public WebDriver driver;
    public Atlas atlas;

    public CartPageSteps(WebDriver driver, Atlas atlas) {
        this.driver = driver;
        this.atlas = atlas;
    }

    //@Step("Сравниваем сравниваем название и цену в колонке "Total" у товара, на соответствие с сохраненными значениями")
    public void compareNameAndPrice(String expectedName, String expectedPrice) {
        String actualName = onCartPage().productNameOnCartPage().getText();
        String actualPrice = onCartPage().productPriceOnCartPage().getText();
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedPrice, actualPrice);
    }

    private CartPage onCartPage() {
        return atlas.create(driver, CartPage.class);
    }
}
