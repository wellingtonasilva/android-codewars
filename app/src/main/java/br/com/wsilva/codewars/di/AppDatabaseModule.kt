package br.com.wsilva.codewars.di

import android.app.Application
import android.arch.persistence.room.Room
import br.com.wsilva.codewars.model.dao.UserDAO
import br.com.wsilva.codewars.model.db.AppDatabase
import br.com.wsilva.codewars.model.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {

    @Provides
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application,
            AppDatabase::class.java, "dabase.db").build()
    }

    @Provides
    fun providesUserDAO(appDatabase: AppDatabase): UserDAO {
        return appDatabase.getUserDAO()
    }

    @Provides
    fun providesUserRepository(userDAO: UserDAO): UserRepository {
        return UserRepository(userDAO)
    }
}