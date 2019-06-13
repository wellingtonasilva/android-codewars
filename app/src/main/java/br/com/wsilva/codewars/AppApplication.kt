package br.com.wsilva.codewars

import android.app.Application
import br.com.wsilva.codewars.di.AppComponent
import br.com.wsilva.codewars.di.AppDatabaseModule
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.di.DaggerAppComponent

class AppApplication: Application() {
    companion object{
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .appDatabaseModule(AppDatabaseModule())
            .build()
    }
}