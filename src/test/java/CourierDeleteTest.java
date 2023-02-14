import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierDeleteTest extends ScooterSitePreload{
    @Test
    @DisplayName("Delete courier valid Test")
    @Description("Delete courier after using or idk")
    public void createCourierValidTest() {
        createNewUser();
        UserId userId = loginRestTest(loginForm).as(UserId.class);
        Response response = given().header("Content-type", "application/json").and().body(userId).delete("/api/v1/courier/" + userId.getId() + "");
        response.then().statusCode(200).body("ok",equalTo(true));
    }
}
