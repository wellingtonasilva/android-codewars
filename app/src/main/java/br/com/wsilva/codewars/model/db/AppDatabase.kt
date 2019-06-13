package br.com.wsilva.codewars.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.wsilva.codewars.model.dao.UserDAO
import br.com.wsilva.codewars.model.entity.UserEntity

@Database(version = 1,
    entities = [
        UserEntity::class
    ])
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
}