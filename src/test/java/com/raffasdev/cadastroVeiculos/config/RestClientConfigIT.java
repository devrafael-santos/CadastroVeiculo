package com.raffasdev.cadastroVeiculos.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RestClientConfigIT {

    @Autowired
    private RestClient restClient;

    @Test
    @DisplayName("restClientBean_CreatesAndInjectsRestClientBean_WhenSuccessful")
    void restClientBean_CreatesAndInjectsRestClientBean_WhenSuccessful() {
        assertNotNull(restClient);
    }
}