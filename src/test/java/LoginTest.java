import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends ScooterSitePreload{

    @Test
    public void LoginTestValid() {
        given().header("Content-type", "application/json").and().body(newUser).post("/api/v1/courier");
        // тест авторизации
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginForm)
                .post("/api/v1/courier/login");
        response.then().statusCode(200).body("id", notNullValue());
        UserId userId = response.as(UserId.class);
        given().header("Content-type", "application/json").and().body("{\"id\": \"" + userId.getId() + "\"}").delete("/api/v1/courier/" + userId.getId() + "");
    }

    @Test
    public void LoginTestInvalidLoginData() {
        given().header("Content-type", "application/json").and().body(newUser).post("/api/v1/courier");
        // тест авторизации
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginFormInvalid)
                .post("/api/v1/courier/login");
        response.then().statusCode(404).body("message",equalTo("Учетная запись не найдена"));
        UserId userId = given().header("Content-type", "application/json").body(loginForm).post("/api/v1/courier/login").as(UserId.class);
        given().header("Content-type", "application/json").and().body("{\"id\": \"" + userId.getId() + "\"}").delete("/api/v1/courier/" + userId.getId() + "");
    }

    @Test
    public void LoginTestWithoutParam() {
        given().header("Content-type", "application/json").and().body(newUser).post("/api/v1/courier");
        // тест авторизации
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginFormWithoutParam)
                .post("/api/v1/courier/login");
        response.then().statusCode(400).body("message",equalTo("Недостаточно данных для входа"));
        UserId userId = given().header("Content-type", "application/json").body(loginForm).post("/api/v1/courier/login").as(UserId.class);
        given().header("Content-type", "application/json").and().body("{\"id\": \"" + userId.getId() + "\"}").delete("/api/v1/courier/" + userId.getId() + "");
    }

}
