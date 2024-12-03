package com.assistente.Virtual;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvInitializer {
    static {
        String dotenvPath = "/etc/secrets/.env";

        Dotenv dotenv = Dotenv.configure()
            .directory("/etc/secrets")
            .filename(".env")
            .load();

        if (dotenv.entries().isEmpty()) {
            throw new RuntimeException("Arquivo .env não encontrado no caminho: " + dotenvPath);
        }
        
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
