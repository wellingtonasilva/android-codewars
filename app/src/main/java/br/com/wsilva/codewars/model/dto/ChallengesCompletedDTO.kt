package br.com.wsilva.codewars.model.dto


data class ChallengesCompletedDTO(
    val totalPages: Int,
    val totalItems: Int,
    val data: List<ChallengeDTO>
)

