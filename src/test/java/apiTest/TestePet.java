package apiTest;

import com.google.gson.Gson;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestePet {

    static String ct = "application/json"; // Contentype
    static String uriPet = "https://petstore.swagger.io/v2/pet/";

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test
    @Order(1)
    public void testarIncluirPetUser() throws IOException {
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/petUser.json");

        int userPetId = 788876;

        // realizar o teste
        given()                                        // dado que
                .contentType(ct)    // o tipo do conteúdo
                .log().all()                          // mostrre tudo
                .body(jsonBody)                       // corpo da aquisição
        .when()                                       // Quando
                .post(uriPet) // Endpoint / Onde
        .then()                                         //Então
                .log().all()                            // mostre tudo na volta
                .statusCode(200)                     // comunic. ida e volta ok
                .body("id", is(userPetId))               // message é o userId
        ;
    }

    @Test
    @Order(2)
    public void testarConsultarPetUser() {
        // resultdos esperados
        String petName = "milk";
        int userPetId = 788876; // código do usuário
        String tagName = "vacinado";
        String status = "disponivel";
        String tipoPet = "gato";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriPet + userPetId)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userPetId))
                .body("category.name", is(tipoPet))
                .body("tags[0].name", is(tagName))
        ; // fim do get
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/csv/massaPet.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarIncluirPetUser(
            int id,
            String category_id,
            String category_name,
            String name,
            String photoUrls,
            int tags_id,
            String tags_name,
            String status){

         // inicio incluir csv

        Pet pet = new Pet(); // instancia a classe user

        pet.id = id;
        pet.category = new Pet.Category(category_id, category_name);
        pet.name = name;
        pet.photoUrls = new String[]{photoUrls};
        pet.tags = new Pet.Tags[]{
                new Pet.Tags(tags_id, tags_name)
        };
        pet.status = status;

        Gson gson = new Gson(); // instancia a classe Gson
        String jsonBody = gson.toJson(pet);

        // realizar o teste
        given()                                        // dado que
                .contentType(ct)    // o tipo do conteúdo
                .log().all()                          // mostrre tudo
                .body(jsonBody)                       // corpo da aquisição
                .when()                                       // Quando
                .post(uriPet) // Endpoint / Onde
                .then()                                         //Então
                .log().all()                            // mostre tudo na volta
                .statusCode(200)                     // comunic. ida e volta ok
                .body("id", is(id))          // tag code 200
                .body("name", is(name))    // tag type é Unknown
                .body("tags[0].name", is(tags_name))               // message é o userId
        ;

    }
}
