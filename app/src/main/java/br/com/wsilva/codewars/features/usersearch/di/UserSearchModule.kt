package br.com.wsilva.codewars.features.usersearch.di

import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.features.usersearch.UserSearchContract
import br.com.wsilva.codewars.features.usersearch.UserSearchPresenter
import br.com.wsilva.codewars.model.repository.UserRepository
import br.com.wsilva.codewars.sevice.rest.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class UserSearchModule(private val view: UserSearchContract.View) {

    @Provides
    fun provicesUserSearchView(): UserSearchContract.View = view

    @Provides
    fun provicesUserSearchPresenter(view: UserSearchContract.View, repository: UserRepository, api: RestApi,
                                    bag: CompositeDisposable, schedulers: AppSchedulers): UserSearchPresenter {
        return UserSearchPresenter(view, repository,  api,  bag, schedulers)
    }
}