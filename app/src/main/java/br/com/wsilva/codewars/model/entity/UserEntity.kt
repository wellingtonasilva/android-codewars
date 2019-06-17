package br.com.wsilva.codewars.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "honor") var honor: Int,
    @ColumnInfo(name = "clan") var clan: String,
    @ColumnInfo(name = "leaderboardPosition") var leaderboardPosition: Int,
    @ColumnInfo(name = "overall_rank") var overallRank: Int,
    @ColumnInfo(name = "overall_name") var overallName: String,
    @ColumnInfo(name = "overall_color") var overallColor: String,
    @ColumnInfo(name = "overall_score") var overallScore: Int,
    @ColumnInfo(name = "total_authored") var totalAuthored: Int,
    @ColumnInfo(name = "total_completed") var totalCompleted: Int,
    @ColumnInfo(name = "skills") var skills: String,
    @ColumnInfo(name = "total_languages_trained") var totalLanguagesTrained: Int,
    @ColumnInfo(name = "highest_trained") var highestTrained: String

) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}