package com.openweathermap.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class RequestsUtility {
    public static Response get(String endpoint, Map<String, String> headers, Map<String, String> queryParams){
        return RestAssured.given()
                .headers(headers)
                .queryParams(queryParams)
                .contentType(ContentType.JSON)
                .when()
                .get(Constants.API_URL + endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response post(String endpoint, Map<String, String> headers, Map<String, String> queryParams, Object body) {
        return RestAssured.given()
                .headers(headers)
                .queryParams(queryParams)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public static void assert_status_code(Response response, int code){
        Assert.assertEquals(response.statusCode(), code, "Invalid status code");
    }

}
