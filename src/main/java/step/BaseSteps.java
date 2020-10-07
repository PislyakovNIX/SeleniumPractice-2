package step;

import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;

public class BaseSteps {

    public WebDriver driver;
    public Atlas atlas;

    public BaseSteps(WebDriver driver, Atlas atlas) {
        this.driver = driver;
        this.atlas = atlas;
    }
}
