package br.com.wsilva.codewars.features.userdetail.home.di

import br.com.wsilva.codewars.di.AppDatabaseModule
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.userdetail.home.UserDetailHomeFragment
import dagger.Component

@Component(modules = [AppModule::class, AppDatabaseModule::class, UserDetailHomeModule::class])
interface UserDetailHomeComponent {
    fun inject(fragment: UserDetailHomeFragment)
}