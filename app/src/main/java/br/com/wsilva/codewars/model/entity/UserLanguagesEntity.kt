package br.com.wsilva.codewars.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_languages")
data class UserLanguagesEntity(
    @ColumnInfo(name = "user_id") var userId: Long,
    @ColumnInfo(name = "language") var language: String,
    @ColumnInfo(name = "rank") var rank: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "color") var color: String,
    @ColumnInfo(name = "score") var score: Int
) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}