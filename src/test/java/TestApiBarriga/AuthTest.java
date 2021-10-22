package TestApiBarriga;

import Core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class AuthTest extends BaseTest {

    @Test
    public void naoDeveAcessarAPIsemToken(){
        // para remover especificaçoes do restAssured uso a sequencia abaixo
        FilterableRequestSpecification req = (FilterableRequestSpecification) RestAssured.requestSpecification;
        req.removeHeader("Authorization");
        // até aq.
        given()
                .when()
                .get("/contas")
                .then()
                .statusCode(401)
        ;
    }
}
