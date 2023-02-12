import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends ScooterSitePreload {

    @Test
    public void createCourierValidTest() {
        // регистрация пользака
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(newUser)
                        .post("/api/v1/courier");
        response.then().statusCode(201).body("ok",equalTo(true));
    }

    @Test
    public void createCourierInvalidTest() {
        // регистрация пользователя
        given().header("Content-type", "application/json").and().body(newUser).post("/api/v1/courier");
        Response response =
                // повторная регистрация пользователя с теми же данными
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(newUser)
                        .post("/api/v1/courier");
        response.then().statusCode(409).body("message",equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}