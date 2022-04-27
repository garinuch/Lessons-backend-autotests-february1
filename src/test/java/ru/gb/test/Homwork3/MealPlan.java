package ru.gb.test.Homwork3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.gb.extensions.SpoonApiTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class MealPlan {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/2")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void deleteRequest() {

        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/api/users/2")
                .then()
                .extract().response();
    }
}