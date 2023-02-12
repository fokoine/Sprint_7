import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class ScooterSitePreload {

    UserData userData = new UserData();
    String newUser = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"" + userData.randomPassword + "\", \"firstName\": \"" + userData.randomFirstName + "\"}";
    String loginForm = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"" + userData.randomPassword + "\"}";
    String loginFormInvalid = "{\"login\": \"" + userData.randomPassword + "\", \"password\": \"" + userData.randomLogin + "\"}";
    String loginFormWithoutParam = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"\"}";


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void cleanUp() {
        UserId userId = given().header("Content-type", "application/json").body(loginForm).post("/api/v1/courier/login").body().as(UserId.class);
        given().header("Content-type", "application/json").and().body("{\"id\": \"" + userId.getId() + "\"}").delete("/api/v1/courier/" + userId.getId() + "");
    }
}