package com.automationpracticePislyakov2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class API_Test {

    @Test(dataProvider = "SearchProvider")
    public void AssuredAPITest(String url) throws InterruptedException {
        given().
                log().all().
                header("User-Agent", "Jmeter").
                baseUri(url).
                when().
                get().
                then().
                log().all().
                statusCode(200).
                assertThat().header("Content-Type", containsString("text/html")).
                assertThat().header("Content-Encoding", "gzip");
    }

    @DataProvider(name = "SearchProvider")
    public Object[] getDataFromDataprovider() {
        return new Object[]
                {
                        "https://auto.ria.com/auto_mitsubishi_lancer_21990745.html",
                        "https://auto.ria.com/auto_toyota_camry_22101863.html",
                        "https://auto.ria.com/auto_bmw_520_21996828.html",
                        "https://auto.ria.com/auto_nissan_leaf_22104675.html",
                        "https://auto.ria.com/auto_nissan_leaf_22104647.html",
                        "https://auto.ria.com/auto_mercedes_benz_e_220_21994701.html",
                        "https://auto.ria.com/auto_lexus_nx_200_22083607.html"
                };
    }
}
