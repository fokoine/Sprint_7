import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierDeleteTest extends ScooterSitePreload{
    @Test
    public void createCourierValidTest() {
        // регистрация пользака
        given().header("Content-type", "application/json").and().body(newUser).post("/api/v1/courier");
        // авторизация и получение id пользака
        UserId userId = given().header("Content-type", "application/json").body(loginForm).post("/api/v1/courier/login").as(UserId.class);
        // удаление пользака
        Response response = given().header("Content-type", "application/json").and().body("{\"id\": \"" + userId.getId() + "\"}").delete("/api/v1/courier/" + userId.getId() + "");
        response.then().statusCode(200).body("ok",equalTo(true));
    }
}
