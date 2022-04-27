package ru.gb.test.spoon;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.gb.extensions.SpoonApiTest;
import static io.restassured.RestAssured.given;


@SpoonApiTest
public class FoodTest {

    @ParameterizedTest
    @ValueSource(strings = {"Pizza", "Sushi"})
    public void foodSearchTest(String queryParameter) {
        given()
                .queryParams("query", queryParameter)
                .queryParams("offset", 0)
                .queryParams("number", 10)
                .get("/food/search")
                .then()
                .statusCode(200)
                .body("query", Matchers.equalTo(queryParameter));
    }
}
