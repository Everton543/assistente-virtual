package com.assistente.Virtual;

import com.assistente.Virtual.model.ApiKey;
import com.assistente.Virtual.repository.ApiKeyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRepository implements CommandLineRunner {

    private final ApiKeyRepository apiKeyRepository;

    public TestRepository(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*ApiKey apiKey = new ApiKey();
        apiKey.setChave("test-key-update");
        apiKey.setRemainingQuestions(10);
        apiKeyRepository.save(apiKey);

        System.out.println("Chave criada: " + apiKey);

        // Atualizando o número de perguntas restantes
        apiKey.setRemainingQuestions(7);
        apiKeyRepository.save(apiKey);

        System.out.println("Chave atualizada: " + apiKeyRepository.findByChave("test-key-update").orElse(null));*/
    }

}
