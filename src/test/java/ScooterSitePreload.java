import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class ScooterSitePreload {

    UserData userData = new UserData();
    String newUser = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"" + userData.randomPassword + "\", \"firstName\": \"" + userData.randomFirstName + "\"}";
    String loginForm = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"" + userData.randomPassword + "\"}";
    String loginFormInvalid = "{\"login\": \"" + userData.randomPassword + "\", \"password\": \"" + userData.randomLogin + "\"}";
    String loginFormWithoutParam = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"\"}";

    @Step("Create user")
    public Response createNewUser() {
        Response response = given().header("Content-type", "application/json").and().body(newUser).post("/api/v1/courier");
        return response;
    }

    @Step("Login user")
    public Response loginRestTest(String loginPresetForm){
        Response loginResponse = given()
                .header("Content-type", "application/json")
                .body(loginPresetForm)
                .post("/api/v1/courier/login");
        return loginResponse;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    @Step("Clean up user data")
    public void cleanUp() {
        UserId userId = given().header("Content-type", "application/json").body(loginForm).post("/api/v1/courier/login").body().as(UserId.class);
        given().header("Content-type", "application/json").and().body(userId).delete("/api/v1/courier/" + userId.getId() + "");
    }
}