package br.com.wsilva.codewars.model.dto

data class RanksDTO (
    var overall: RankDTO? = null,
    var languages: Map<String, RankDTO>? = null
)
