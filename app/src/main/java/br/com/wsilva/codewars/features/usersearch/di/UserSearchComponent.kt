package br.com.wsilva.codewars.features.usersearch.di

import br.com.wsilva.codewars.di.AppDatabaseModule
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.usersearch.UserSearchFragment
import br.com.wsilva.codewars.features.usersearch.UserSearchPresenter
import dagger.Component

@Component(modules = [AppModule::class, AppDatabaseModule::class, UserSearchModule::class])
interface UserSearchComponent {
    fun inject(presenter: UserSearchPresenter)
    fun inject(fragment: UserSearchFragment)
}