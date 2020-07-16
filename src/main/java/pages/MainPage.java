package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;

public interface MainPage extends WebPage {

    @Name("Поле поиска")
    @FindBy("//*[@id='search_query_top']")
    AtlasWebElement searchField();

    @Name("Кнопка поиска")
    @FindBy("//*[@name='submit_search']")
    AtlasWebElement searchSubmitButton();
}