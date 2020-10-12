package com.automationpracticePislyakov2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.net.HttpHeaders.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class API_Test {

    @Test(dataProvider = "SearchProvider")
    public void AssuredAPITest(String url) throws IOException {
        given()
                .log().all()
                .header(USER_AGENT, "Jmeter")
                .baseUri(url)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .assertThat().header(CONTENT_TYPE, containsString("text/html"))
                .assertThat().header(CONTENT_ENCODING, "gzip");
    }

    @DataProvider(name = "SearchProvider")
    public Object[] getDataFromDataProvider() throws IOException {
        List<String> linksList = new ArrayList<String>();
        Reader in = new FileReader("src/main/resources/autoria_API_links.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            String columnOne = record.get(0);
            linksList.add(columnOne);
        }
        return linksList.toArray();
    }
}