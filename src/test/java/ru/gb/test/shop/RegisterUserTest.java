package ru.gb.test.shop;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.gb.extensions.MobileShopApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@MobileShopApiTest
public class RegisterUserTest {
    private static Faker faker = new Faker();
    String address = faker.address().fullAddress();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password();
    String phone = faker.phoneNumber().phoneNumber();
    String username = faker.name().fullName();

    @Test
    void registerUserTest() {
        given()
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
                .body("username", equalTo(username));
    }
}
