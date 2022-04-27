package ru.gb.test.Homwork3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.gb.extensions.SpoonApiTest;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@SpoonApiTest
public class FoodTest2 {
    String token = "9578b6a80fdc47df8612218290502c63";

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void foodSearchTest() {
        String queryParameter = "Pizza";
        given()
                .queryParam("query", queryParameter)
                .queryParam("ofset", 0)
                .queryParam("apikey", token)
                .queryParam("number", 10)
                .get(baseURI + "/food/search")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("query", Matchers.equalTo(queryParameter));

    }

    @Test
    void getRecipePositiveTest() {
        RestAssured.baseURI = "https://api.spoonacular.com";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(baseURI + "/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

        @Test
        void catalogTest() {
            RestAssured.baseURI = "https://api.spoonacular.com";
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            given()
                    .get("/recipes/cuisine")
                    .then();
    }

    @Test
    void beforeAllJson() {
        String queryParameter = "Pork roast with green beans";
        given()
                .queryParam("title", queryParameter)
                .get("https://api.spoonacular.com/recipes/cuisine")
                .prettyPeek()
                .then()
                .extract()
                .body()
                .jsonPath();
    }


}
