package br.com.wsilva.codewars.model.dto

data class ChallengeDTO(
    val id: String,
    val name: String,
    val slug: String,
    val completedLanguages: List<String>,
    val completedAt:  String
)