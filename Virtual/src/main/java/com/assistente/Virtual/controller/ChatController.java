package com.assistente.Virtual.controller;

import com.assistente.Virtual.service.ApiKeyService;
import com.assistente.Virtual.service.ChatGptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatGptService chatGPTService;
    private final ApiKeyService apiKeyService;

    // Construtor para injeção de dependências
    public ChatController(ChatGptService chatGPTService, ApiKeyService apiKeyService) {
        this.chatGPTService = chatGPTService;
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> requestBody) {
        String chaveAcesso = requestBody.get("chave_acesso");
        String prompt = requestBody.get("prompt");

        // Verifica se os parâmetros estão presentes
        if (chaveAcesso == null || prompt == null || chaveAcesso.isEmpty() || prompt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", "Chave de acesso ou prompt não fornecido."));
        }

        // Valida a chave
        if (!apiKeyService.chaveEstaValida(chaveAcesso)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("erro", "Chave inválida. Você não tem permissão para usar este serviço."));
        }

        // Verifica o limite de mensagens
        if (!apiKeyService.diminuiQuantidadeDePerguntasDisponiveis(chaveAcesso)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("erro", "Limite de mensagens atingido. Você já utilizou todas as mensagens permitidas."));
        }

        // Processa a pergunta com o ChatGPT
        String response = chatGPTService.getRespostaDoChatGPT(prompt);
        return ResponseEntity.ok(Map.of("resposta", response));
    }
}
