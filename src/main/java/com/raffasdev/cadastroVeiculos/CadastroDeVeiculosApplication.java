package com.raffasdev.cadastroVeiculos;

import com.raffasdev.cadastroVeiculos.config.EnvInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroDeVeiculosApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CadastroDeVeiculosApplication.class);
        app.addInitializers(new EnvInitializer());
        app.run(args);
    }

}
