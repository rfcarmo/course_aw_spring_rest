package com.algaworks.algafood;

import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ConsultaRestauranteApiIT {

    @LocalServerPort
    private int port;

    private String jsonCorretoCozinhaChinesa;

    private Flyway flyway;

    @Autowired
    public ConsultaRestauranteApiIT(Flyway flyway) {
        this.flyway = flyway;
    }

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        flyway.migrate();

        jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");

        prepararDados();
    }

    @Test
    public void test() {

    }

    private void prepararDados() {

    }

}
