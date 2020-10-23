package matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class HasText extends TypeSafeMatcher<AtlasWebElement> {

    private String expectedSearchText;
    private String actualSearchText;

    private HasText(String expectedSearchText) {
        this.expectedSearchText = expectedSearchText.toUpperCase();
    }

    @Override
    public boolean matchesSafely(AtlasWebElement item) {
        actualSearchText = item.getText().replaceAll("\"", "");
        return expectedSearchText.matches(actualSearchText);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Expected search text is " + expectedSearchText);
    }

    @Override
    protected void describeMismatchSafely(AtlasWebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("Actual search text was " + actualSearchText);
    }

    @Factory
    public static Matcher<AtlasWebElement> hasText(final String actualSearchText) {
        return new HasText(actualSearchText);
    }

}


