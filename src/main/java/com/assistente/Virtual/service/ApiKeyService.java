package com.assistente.Virtual.service;

import com.assistente.Virtual.model.ApiKey;
import com.assistente.Virtual.repository.ApiKeyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public boolean chaveEstaValida(String chave) {
        return apiKeyRepository.findByChave(chave).isPresent();
    }

    public boolean diminuiQuantidadeDePerguntasDisponiveis(String chave) {
        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findByChave(chave);

        if (apiKeyOptional.isPresent()) {
            ApiKey apiKey = apiKeyOptional.get();
            if (apiKey.getRemainingQuestions() > 0) {
                apiKey.setRemainingQuestions(apiKey.getRemainingQuestions() - 1);
                apiKeyRepository.save(apiKey);
                return true;
            }
        }
        return false;
    }
}
