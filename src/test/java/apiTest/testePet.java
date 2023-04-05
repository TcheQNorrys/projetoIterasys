// nome do pacote

package apiTest;

// Bibliotecas


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


//Classe
public class TesteUser {// Inicio da Classe
    // Atributos
    static String ct = "application/json"; // Contentype
    static String uriUserPet = "https://petstore.swagger.io/v2/pet/";
    // Funções e Métodos
    // Funcões de Apoio
    public static String lerArquivoJson (String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get( arquivoJson)));
    }

     @Test
    public void testarIncluirPetUser() throws IOException {
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/petUser.json");

        String userPetId = "788876";

        // realizar o teste
        given()                                        // dado que
                .contentType(ct)    // o tipo do conteúdo
                .log().all()                          // mostrre tudo
                .body(jsonBody)                       // corpo da aquisição
                .when()                                       // Quando
                .post(uriUserPet) // Endpoint / Onde
                .then()                                         //Então
                .log().all()                            // mostre tudo na volta
                .statusCode(200)                     // comunic. ida e volta ok
                .body("code", is(200))          // tag code 200
                .body("type", is("unknown"))    // tag type é Unknown
                .body("message", is(userPetId))               // message é o userId
        ;
    }
    @Test
    public void testarConsultarPetUser (){
        // resultdos esperados
        String petName = "milk";
        String userPetId = "788876"; // código do usuário
        String tagName = "vacinado";
        String status = "disponivel";
        String tipoPet = "gato";

        given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriUserPet + userPetId)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userPetId))
                .body("category.name", is(tipoPet))
                .body("tags[0].name", is(tagName))
        ; // fim do get
    }
}// Fim da Classe