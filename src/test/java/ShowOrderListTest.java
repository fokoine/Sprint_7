import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ShowOrderListTest extends ScooterSitePreload {

    @Test
    public void ShowOrderListTest() {
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