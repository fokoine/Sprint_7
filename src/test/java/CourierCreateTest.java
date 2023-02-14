import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends ScooterSitePreload {

    @Test
    @DisplayName("Create courier valid Test")
    @Description("Create courier. Very important case")
    public void createCourierValidTest() {
        createNewUser()
                .then()
                .statusCode(201).body("ok",equalTo(true));
    }

    @Test
    @DisplayName("Create courier invalid Test")
    @Description("If login already used. Very important case")
    public void createCourierInvalidTest() {
        createNewUser();
        createNewUser()
                .then()
                .statusCode(409)
                .body("message",equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}