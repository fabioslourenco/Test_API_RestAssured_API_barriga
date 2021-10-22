package Suíte;

import Core.BaseTest;
import TestApiBarriga.AuthTest;
import TestApiBarriga.BarrigaTestRefecContas;
import TestApiBarriga.BarrigaTestRefecMovimentacao;
import TestApiBarriga.BarrigaTestRefecSaldo;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

@RunWith(org.junit.runners.Suite.class)
@Suite.SuiteClasses({
        BarrigaTestRefecContas.class,
        BarrigaTestRefecMovimentacao.class,
        BarrigaTestRefecSaldo.class,
        AuthTest.class
})

public class Suíte extends BaseTest {
    @BeforeClass
    public static void login() {
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "fabiollourencosillva@gmail.com");
        login.put("senha", "711800@F");

        String token = given()
                .body(login)
                .when()
                .post("/signin")
                .then()
                .statusCode(200)
                .extract().path("token");

        RestAssured.requestSpecification.header("Authorization", "JWT " + token);
        RestAssured.get("/reset").then().statusCode(200);
    }

}
