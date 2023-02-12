import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest extends ScooterSitePreload {

    private final String colourType;

    public OrderCreateTest(String colourType) {
        this.colourType = colourType;
    }

    @Parameterized.Parameters
    public static Object[][] testParams() {
        return new Object[][]{
                {"src/test/resources/grey.json"},
                {"src/test/resources/grey.json"},
                {"src/test/resources/noColour.json"}
        };
    }

    @Test
    public void createCourierValidTest() {
        // регистрация пользака
        File json = new File(colourType);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/orders");
        response.then().statusCode(201).body("track", notNullValue());
    }

}