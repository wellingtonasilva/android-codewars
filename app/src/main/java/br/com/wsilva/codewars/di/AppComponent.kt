package br.com.wsilva.codewars.di

import android.app.Application
import br.com.wsilva.codewars.model.db.AppDatabase
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class, AppDatabaseModule::class])
interface AppComponent {
    fun application(): Application
    fun retrofit(): Retrofit
    fun database(): AppDatabase
    fun schedulers(): AppSchedulers
}