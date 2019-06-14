package br.com.wsilva.codewars.features.usersearch

import br.com.wsilva.codewars.constants.AppConstants
import br.com.wsilva.codewars.di.AppSchedulers
import br.com.wsilva.codewars.model.dto.UserDTO
import br.com.wsilva.codewars.model.repository.UserRepository
import br.com.wsilva.codewars.sevice.rest.RestApi
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
    }

    override fun onQueryTextSubmit(query: String) {
        bag.add(
            api.getUser(query,AppConstants.API_KEY)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({result -> onSuccess(result)},
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
        repository
            .listAll()
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribe { view.showNoResult(false); view.showList(it) }
    }
}