package com.assistente.Virtual.model;

import jakarta.persistence.*;

@Entity
@Table(name = "api_keys")
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String chave;

    @Column(nullable = false)
    private int remaining_questions;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public int getRemainingQuestions() {
        return remaining_questions;
    }

    public void setRemainingQuestions(int remaining_questions) {
        this.remaining_questions = remaining_questions;
    }
}
