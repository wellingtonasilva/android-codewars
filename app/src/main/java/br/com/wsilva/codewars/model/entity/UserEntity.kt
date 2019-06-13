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
    @ColumnInfo(name = "leaderboardPosition") var leaderboardPosition: Int
) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}