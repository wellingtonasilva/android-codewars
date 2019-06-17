package br.com.wsilva.codewars.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "user_challenge")
data class UserChallengeEntity(
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "id") val challengeId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "completed_languages") val completedLanguages: String,
    @ColumnInfo(name = "completed_at") val completedAt: String,
    @ColumnInfo(name = "page") val page: Int
) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}