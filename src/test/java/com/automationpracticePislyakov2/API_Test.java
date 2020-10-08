package com.automationpracticePislyakov2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class API_Test {

    @Test
    public void AssuredAPITest() throws IOException {

        Reader in = new FileReader("D:\\Automation testing\\SeleniumPractice-2\\src\\main\\resources\\autoria_API_links.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            String columnOne = record.get(0);
            given().
                    log().all().
                    header("User-Agent", "Jmeter").
                    baseUri(columnOne).
                    when().
                    get().
                    then().
                    log().all().
                    statusCode(200).
                    assertThat().header("Content-Type", containsString("text/html")).
                    assertThat().header("Content-Encoding", "gzip");
        }
    }
}
