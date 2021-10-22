package TestApiBarriga;

import Core.BaseTest;
import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class BarrigaTestRefecContas extends BaseTest {


    @Test
    public void deveIncluirContaComSucesso(){

        given()
                .body("{ \"nome\": \"Conta inserida\"}")
                // Para autenticação de APIs mais recentes substituir o JWT por bearer
        .when()
                .post("/contas")
        .then()
                .statusCode(201)

        ;
    }

    @Test
    public void deveAlterarContaComSucesso(){
        Integer CONTA_ID = getIdContaPeloNome("Conta para alterar");

        given()
                .body("{ \"nome\": \"Conta alterada\"}")
                .pathParam("id", CONTA_ID)
        .when()
                .put("/contas/{id}")
        .then()
                .statusCode(200)
                .body("nome", is("Conta alterada"))
        ;
    }

    public Integer getIdContaPeloNome(String nome){
        return RestAssured.get("/contas?name="+nome).then().extract().path("id[0]");
    }

    @Test
    public void naoDeveIncluirContaComOnomeRepetido(){

        given()
                .body("{ \"nome\": \"Conta mesmo nome\"}")
        .when()
                .post("/contas")
        .then()
                .statusCode(400)
                .body("error", is("Já existe uma conta com esse nome!"))
        ;
    }

}
