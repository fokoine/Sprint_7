import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends ScooterSitePreload{

    @Test
    @DisplayName("Login courier with valid login data")
    @Description("Login to panel or something with correct data. Very important after valid create")
    public void loginTestValid() {
        createNewUser();
        loginRestTest(loginForm)
                .then()
                .statusCode(200).body("id", notNullValue());
    }

    @Test
    @DisplayName("Login courier with invalid login data")
    @Description("Login to panel or something without data. Very important after valid create")
    public void loginTestInvalidLoginData() {
        createNewUser();
        loginRestTest(loginFormInvalid)
                .then()
                .statusCode(404).body("message",equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Login courier without data")
    @Description("Check that we do not have user without data")
    public void loginTestWithoutParam() {
        createNewUser();
        loginRestTest(loginFormWithoutParam)
                .then().statusCode(400).body("message",equalTo("Недостаточно данных для входа"));
    }

}
