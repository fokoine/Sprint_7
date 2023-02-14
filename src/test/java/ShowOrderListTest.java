import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ShowOrderListTest extends ScooterSitePreload {

    @Test
    @DisplayName("Show order list test")
    @Description("Check valid load orders list")
    public void showOrderListTest() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .get("/api/v1/orders");
        response.then().statusCode(200)
                .and().body("orders", notNullValue())
                .and().body("pageInfo", notNullValue())
                .and().body("availableStations", notNullValue());
    }
}