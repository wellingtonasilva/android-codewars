package br.com.wsilva.codewars.features.usersearch

import br.com.wsilva.codewars.constants.AppConstants
import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.model.dto.UserDTO
import br.com.wsilva.codewars.model.repository.UserLanguagesRepository
import br.com.wsilva.codewars.model.repository.UserRepository
import br.com.wsilva.codewars.sevice.rest.RestApi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserSearchPresenter: UserSearchContract.Presenter {

    private val view: UserSearchContract.View
    private val api: RestApi
    private val repository: UserRepository
    private val userLanguagesRepository: UserLanguagesRepository
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers

    @Inject
    constructor(view: UserSearchContract.View, repository: UserRepository,
                userLanguagesRepository: UserLanguagesRepository, api: RestApi,
                bag: CompositeDisposable, schedulers: AppSchedulers) {
        this.view = view
        this.repository = repository
        this.userLanguagesRepository = userLanguagesRepository
        this.api = api
        this.bag = bag
        this.schedulers = schedulers
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun onCreate() {
    }

    override fun onQueryTextSubmit(query: String) {
        bag.add(
            api.getUser(query,AppConstants.API_KEY)
                .flatMap { t: UserDTO -> repository.save(t) }
                .flatMap { t: UserDTO ->  userLanguagesRepository.save(t.id, t.ranks!!)}
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({result -> loadUser()},
                    {error -> onError(error.message ?: "Unknow error.")})
        )

    }

    override fun onError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(result: UserDTO) {
        bag.add(
            repository.save(result)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe {result -> loadUser()}
        )
    }

    override fun loadUser() {
        bag.add(
            repository
                .listAll()
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showNoResult(false); view.showList(it) }
        )
    }

    override fun orderByRank() {
        bag.add(
            repository
                .listAllOrderByRank()
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showNoResult(false); view.showList(it) }
        )
    }

    override fun orderByTimeOfLookUp() {
        bag.add(
            repository
                .listAllOrderByTimeOfLookUp()
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showNoResult(false); view.showList(it) }
        )
    }
}