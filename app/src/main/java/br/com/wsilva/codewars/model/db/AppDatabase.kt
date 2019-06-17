package br.com.wsilva.codewars.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.wsilva.codewars.model.dao.UserChallengeDAO
import br.com.wsilva.codewars.model.dao.UserDAO
import br.com.wsilva.codewars.model.dao.UserLanguagesDAO
import br.com.wsilva.codewars.model.entity.UserChallengeEntity
import br.com.wsilva.codewars.model.entity.UserEntity
import br.com.wsilva.codewars.model.entity.UserLanguagesEntity

@Database(version = 8,
    entities = [
        UserEntity::class,
        UserLanguagesEntity::class,
        UserChallengeEntity::class
    ])
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getUserLanguagesDAO(): UserLanguagesDAO
    abstract fun getUserChallengeDAO(): UserChallengeDAO
}