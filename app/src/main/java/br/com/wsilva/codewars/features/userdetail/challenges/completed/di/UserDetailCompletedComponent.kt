package br.com.wsilva.codewars.features.userdetail.challenges.completed.di

import br.com.wsilva.codewars.di.AppDatabaseModule
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.userdetail.challenges.completed.UserDetailCompletedFragment
import dagger.Component

@Component(modules = [AppModule::class, AppDatabaseModule::class, UserDetailCompletedModule::class])
interface UserDetailCompletedComponent {
    fun inject(fragment: UserDetailCompletedFragment)
}