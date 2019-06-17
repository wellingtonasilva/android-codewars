package br.com.wsilva.codewars.features.userdetail.challenges.completed

import br.com.wsilva.codewars.constants.AppConstants
import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.model.repository.UserChallengeRepository
import br.com.wsilva.codewars.sevice.rest.RestApi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserDetailCompletedPresenter: UserDetailCompletedContract.Presenter {

    private val view: UserDetailCompletedContract.View
    private val userChallengeRepository: UserChallengeRepository
    private val api: RestApi
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers
    private var pageId = 0
    private var totalPages = 0
    var username = ""
    var userId = 0L

    @Inject
    constructor(view: UserDetailCompletedContract.View,
                userChallengeRepository: UserChallengeRepository, api: RestApi,
                bag: CompositeDisposable, schedulers: AppSchedulers
    ) {
        this.view = view
        this.userChallengeRepository = userChallengeRepository
        this.api = api
        this.bag = bag
        this.schedulers = schedulers
    }

    override fun onCreate() {
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun loadCompletedChallenges(userId: Long, username: String) {
        bag.add(
            api.getCodeChallengesCompleted(username, pageId, AppConstants.API_KEY)
                .flatMap { t -> userChallengeRepository.save(userId, t, pageId) }
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { result -> load() }
        )
    }

    fun load() {
        bag.add(
            userChallengeRepository.listAllByUserId(userId)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { result -> view.showCompletedChallenges(result) }
        )
    }
}