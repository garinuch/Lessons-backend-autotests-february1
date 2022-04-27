package ru.gb.test.shop;

import com.github.javafaker.Faker;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.extensions.MobileShopApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@MobileShopApiTest
public class UserTest {
    private static Faker faker = new Faker();
    String address = faker.address().fullAddress();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password();
    String phone = faker.phoneNumber().phoneNumber();
    String username = faker.name().fullName();
    String token;

    @BeforeEach
    void setUp() {
        token = given()
                .body("{\n" +
                        "  \"address\": \"" + address + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"phone\": \"" + phone + "\",\n" +
                        "  \"username\": \"" + username + "\"\n" +
                        "}")
                .post("/auth/register")
                .then()
                .statusCode(201)
                .body("address", equalTo(address))
                .body("email", equalTo(email))
                .body("phone", equalTo(phone))
                .body("username", equalTo(username))
                .extract()
                .body()
                .jsonPath()
                .getString("token");
    }

    @Test
    void registerUserTest() {
        given()
                .header(new Header("Authorization", "Bearer " + token))
                .get("/user")
                .then()
                .statusCode(200)
                .body("address", equalTo(address))
                .body("email", equalTo(email))
                .body("phone", equalTo(phone))
                .body("username", equalTo(username));
    }
}
