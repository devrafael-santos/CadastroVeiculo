package com.raffasdev.cadastroVeiculos;

import com.raffasdev.cadastroVeiculos.config.EnvInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class CadastroDeVeiculosApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CadastroDeVeiculosApplication.class);
        app.addInitializers(new EnvInitializer());
        app.run(args);
    }

}
