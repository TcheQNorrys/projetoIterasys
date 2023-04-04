// nome do pacote

package apiTest;

// Bibliotecas


import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


//Classe
public class TesteUser {// Inicio da Classe
    // Atributos
    static String ct = "application/json"; // Contentype
    static String uriUser = "https://petstore.swagger.io/v2/user/";
    // Funções e Métodos
    // Funcões de Apoio
    public static String lerArquivoJson (String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get( arquivoJson)));
        }

    // Funões de testes
    @Test
    public void testarIncluirUser() throws IOException {
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");

        String userId = "1733657888";

        // realizar o teste
        given()                                        // dado que
                .contentType(ct)    // o tipo do conteúdo
                .log().all()                          // mostrre tudo
                .body(jsonBody)                       // corpo da aquisição
        .when()                                       // Quando
                .post(uriUser) // Endpoint / Onde
        .then()                                         //Então
                .log().all()                            // mostre tudo na volta
                .statusCode(200)                     // comunic. ida e volta ok
                .body("code", is(200))          // tag code 200
                .body("type", is("unknown"))    // tag type é Unknown
                .body("message", is(userId))               // message é o userId
        ;
    } // fim do post
    @Test
    public void testarConsultarUser (){
        String username = "tcheqnorrys";

        // resultdos esperados
        int userId = 1733657888; // código do usuário
        String email = "Tche@teste.com";
        String senha = "290589";
        String telefone = "11976287888";


        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userId))
                .body("email", is((email)))
                .body("password", is(senha))
                .body("phone", is(telefone))
        ; // fim do get
    }
    @Test
    public void testarAlterarUser() throws IOException { // Inicio do Put user
        String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");
        String userId = "1733657888";
        String username = "tcheqnorrys";


        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .put(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
                ;
    } // final do put user
    @Test
    public void testarExcluirUser(){ // Inicio delete user
    String username = "tcheqnorrys";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .delete(uriUser + username)
        .then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(username))
                ;
    } // fim delete user
    @Test
    public void testarLogin() {
        String username = "tcheqnorrys";
        String password = "123456";
        Response resp = (Response) given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriUser + "login?username=" + username + "&password=" + password)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", containsString("logged in user session:"))
                .body("message", hasLength(36))
                .extract();
        // Extrair token da resposta
        String token = resp.jsonPath().getString("message").substring(23);
        System.out.println("Conteúdo do token: " + token);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "src/test/resources/csv/massaUser.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarIncluirUserCSV(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus)
    { // inicio incluir csv

        StringBuilder jsonBody = new StringBuilder("{");
        jsonBody.append("'id'" + id + ",");
        jsonBody.append("'username'" + username + ",");
        jsonBody.append("'firstname'" + firstName + ",");
        jsonBody.append("'lasname'" + lastName + ",");
        jsonBody.append("'email'" + email + ",");
        jsonBody.append("'password'" + password + ",");
        jsonBody.append("'phone'" + phone + ",");
        jsonBody.append("'userStatus'" + userStatus);
        jsonBody.append("}");

        String userId = "1733657888";

        // realizar o teste
        given()                                        // dado que
                .contentType(ct)    // o tipo do conteúdo
                .log().all()                          // mostrre tudo
                .body(jsonBody)                       // corpo da aquisição
        .when()                                       // Quando
                .post(uriUser) // Endpoint / Onde
        .then()                                         //Então
                .log().all()                            // mostre tudo na volta
                .statusCode(200)                     // comunic. ida e volta ok
                .body("code", is(200))          // tag code 200
                .body("type", is("unknown"))    // tag type é Unknown
                .body("message", is(id))               // message é o userId
        ;

    } // fim incluir csv
}// Fim da Classe