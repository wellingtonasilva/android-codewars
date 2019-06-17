package br.com.wsilva.codewars.features.userdetail.challenges.completed.di

import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.features.userdetail.challenges.completed.UserDetailCompletedContract
import br.com.wsilva.codewars.features.userdetail.challenges.completed.UserDetailCompletedPresenter
import br.com.wsilva.codewars.model.repository.UserChallengeRepository
import br.com.wsilva.codewars.sevice.rest.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class UserDetailCompletedModule(private val view: UserDetailCompletedContract.View) {

    @Provides
    fun providesUserDetailCompletedView(): UserDetailCompletedContract.View = view

    @Provides
    fun providesUserDetailCompletedPresenter(view: UserDetailCompletedContract.View,
                                             userChallengeRepository: UserChallengeRepository,
                                             api: RestApi, bag: CompositeDisposable, schedulers: AppSchedulers): UserDetailCompletedPresenter {

        return UserDetailCompletedPresenter(view, userChallengeRepository, api, bag, schedulers)
    }
}