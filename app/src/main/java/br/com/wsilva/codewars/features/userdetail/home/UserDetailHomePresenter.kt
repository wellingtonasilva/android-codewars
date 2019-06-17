package br.com.wsilva.codewars.features.userdetail.home

import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.model.entity.UserEntity
import br.com.wsilva.codewars.model.repository.UserLanguagesRepository
import br.com.wsilva.codewars.model.repository.UserRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserDetailHomePresenter: UserDetailHomeContract.Presenter {

    private val view: UserDetailHomeContract.View
    private val userRepository: UserRepository
    private val userLanguagesRepository: UserLanguagesRepository
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers
    var userId = 0L

    @Inject
    constructor(view: UserDetailHomeContract.View, userRepository: UserRepository,
                userLanguagesRepository: UserLanguagesRepository, bag: CompositeDisposable,
                schedulers: AppSchedulers
    ) {
        this.view = view
        this.userRepository = userRepository
        this.userLanguagesRepository = userLanguagesRepository
        this.bag = bag
        this.schedulers = schedulers
    }

    override fun onCreate() {
    }

    override fun onDestroy() {
    }

    override fun loadUser(userId: Long) {
        bag.add (
            Single.create<UserEntity> { it.onSuccess(userRepository.get(userId)) }
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { result -> view.showUser(result) }
        )
    }
}