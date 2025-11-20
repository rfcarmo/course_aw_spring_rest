package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CadastroCozinhaApiIT {

    private static final String COZINHA_ID_INEXISTENTE = "00000000-0000-0000-0000-000000000000";
    private static final String COZINHA_ID_EXISTENTE = "5776008f-ad3f-409b-8ce9-7b1c3e16defb";

    @LocalServerPort
    private int port;

    private String jsonCorretoCozinhaChinesa;
    private Cozinha cozinhaAmericana;
    private int quantidadeCozinhasCadastradas;

    private Flyway flyway;

    private CozinhaRepository cozinhaRepository;

    @Autowired
    public CadastroCozinhaApiIT(Flyway flyway, CozinhaRepository cozinhaRepository) {
        this.flyway = flyway;
        this.cozinhaRepository = cozinhaRepository;
    }

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        flyway.migrate();

        jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");

        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured
            .given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConterQuatroCozinhas_QuandoConsultarCozinhas() {
        RestAssured
            .given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("", Matchers.hasSize(quantidadeCozinhasCadastradas))
                .body("nome", Matchers.hasItems("Italiana", "Portuguesa"));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(jsonCorretoCozinhaChinesa)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        RestAssured
            .given()
                .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}", COZINHA_ID_EXISTENTE)
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo("Italiana"));
    }

    @Test
    public void deveRetornar404_QuandoConsultarCozinhaInexistente() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}", COZINHA_ID_INEXISTENTE)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinhaTailandesa = new Cozinha();
        cozinhaTailandesa.setNome("Tailandesa");
        cozinhaRepository.save(cozinhaTailandesa);

        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
    }

}
