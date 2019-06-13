package br.com.wsilva.codewars.features.usersearch

import android.util.Log
import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.model.entity.UserEntity
import br.com.wsilva.codewars.model.repository.UserRepository
import br.com.wsilva.codewars.sevice.rest.RestApi
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserSearchPresenter: UserSearchContract.Presenter {

    private val view: UserSearchContract.View
    private val api: RestApi
    private val repository: UserRepository
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers

    @Inject
    constructor(view: UserSearchContract.View, repository: UserRepository,  api: RestApi,
                bag: CompositeDisposable, schedulers: AppSchedulers) {
        this.view = view
        this.repository = repository
        this.api = api
        this.bag = bag
        this.schedulers = schedulers
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun onCreate() {
        val observable = Observable.create<Long>{ item ->
            item.onNext(repository.insert(UserEntity(username = "Wellington", clan = "was",
                honor = 10, leaderboardPosition = 11, name = "wsilva")))
        }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())

        bag.add(
            observable.subscribe { id -> Log.d("### ", id.toString()) }
        )
    }
}