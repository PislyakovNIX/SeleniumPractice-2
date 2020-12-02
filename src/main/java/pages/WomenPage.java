package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;

public interface WomenPage extends WebPage {


    @FindBy("//*[@class='layered_subtitle' and contains(text(), '{{ text }}')]")
    AtlasWebElement filterTitle(@Param("text") String text);
}
