package com.assistente.Virtual.config;

import com.assistente.Virtual.repository.ApiKeyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ApiKeyRepository apiKeyRepository;

    public DataInitializer(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (apiKeyRepository.count() == 0) {
            System.out.println("Chaves de API iniciais criadas!");
        }
    }
}
