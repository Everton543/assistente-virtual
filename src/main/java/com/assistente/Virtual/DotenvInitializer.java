package com.assistente.Virtual;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvInitializer {
    static {
        Dotenv dotenv = Dotenv.configure()
            .filename(".env")
            .load();

        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
