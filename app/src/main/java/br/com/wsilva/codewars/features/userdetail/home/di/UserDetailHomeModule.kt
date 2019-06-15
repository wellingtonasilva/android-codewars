package br.com.wsilva.codewars.features.userdetail.home.di

import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.features.userdetail.home.UserDetailHomeContract
import br.com.wsilva.codewars.features.userdetail.home.UserDetailHomePresenter
import br.com.wsilva.codewars.model.repository.UserLanguagesRepository
import br.com.wsilva.codewars.model.repository.UserRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class UserDetailHomeModule(private val view: UserDetailHomeContract.View) {

    @Provides
    fun providesUserDetailHomeView(): UserDetailHomeContract.View = view

    @Provides
    fun providesUserDetailHomePresenter(view: UserDetailHomeContract.View, userRepository: UserRepository,
                                        userLanguagesRepository: UserLanguagesRepository, bag: CompositeDisposable,
                                        schedulers: AppSchedulers): UserDetailHomeContract.Presenter {

        return UserDetailHomePresenter(view, userRepository, userLanguagesRepository, bag, schedulers)
    }
}