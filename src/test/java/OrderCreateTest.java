import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest extends ScooterSitePreload {

    private final String first;
    private final String last;
    private final String addre;
    private final int metroStat;
    private final String phon;
    private final int rentTim;
    private final String deliveryDat;
    private final String coment;
    private final String colorSetting;

    public OrderCreateTest(String first, String last, String addre, int metroStat, String phon, int rentTim, String deliveryDat, String coment, String colorSetting) {
        this.first = first;
        this.last = last;
        this.addre = addre;
        this.metroStat = metroStat;
        this.phon = phon;
        this.rentTim = rentTim;
        this.deliveryDat = deliveryDat;
        this.coment = coment;
        this.colorSetting = colorSetting;

    }

    @Parameterized.Parameters
    public static Object[][] testParams() {
        return new Object[][]{
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", "BLACK"},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", "GREY"},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", ""},
        };
    }

    @Test
    @DisplayName("Create order test")
    @Description("Orders creates 3 times with all colors by params")
    public void createOrderValidTest() {
        OrderJson orderJson = new OrderJson(first, last, addre, metroStat, phon, rentTim, deliveryDat, coment);
        orderJson.setColor(new ArrayList<>(), colorSetting);
        Response response =
                given().log().all()
                        .header("Content-type", "application/json")
                        .and()
                        .body(orderJson)
                        .when()
                        .post("/api/v1/orders");
        response.then().statusCode(201).body("track", notNullValue());
    }
}