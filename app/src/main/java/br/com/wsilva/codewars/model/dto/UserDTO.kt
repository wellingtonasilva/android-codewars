package br.com.wsilva.codewars.model.dto

data class UserDTO (
    var id: Long = 0,
    val username: String,
    val name: String,
    val honor: Int,
    val clan: String,
    val leaderboardPosition: Int,
    val skills: List<String>,
    val ranks: RanksDTO,
    val codeChallenges: CodeChallengesDTO,
    val success: Boolean?,
    val reason: String?
)