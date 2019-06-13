package br.com.wsilva.codewars.model.dto

data class UserDTO (
    var username: String? = null,
    var name: String? = null,
    var honor: Int? = null,
    var clan: String? = null,
    var leaderboardPosition: Int? = null,
    var skills: List<String>? = null,
    var ranks: RanksDTO? = null,
    var codeChallenges: CodeChallengesDTO? = null,
    var success: Boolean? = null,
    var reason: String? = null
)