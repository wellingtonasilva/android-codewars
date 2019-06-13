package br.com.wsilva.codewars.features.usersearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.codewars.AppApplication
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.di.AppDatabaseModule
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.usersearch.di.DaggerUserSearchComponent
import br.com.wsilva.codewars.features.usersearch.di.UserSearchModule
import javax.inject.Inject

class UserSearchFragment: Fragment(), UserSearchContract.View {

    @Inject
    lateinit var presenter: UserSearchPresenter

    companion object {
        val TAG: String = "UserSearchFragment"
        fun newInstance(): UserSearchFragment {
            return UserSearchFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        DaggerUserSearchComponent
            .builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .userSearchModule(UserSearchModule(this))
            .build()
            .inject(this)
    }

    override fun onResume() {
        super.onResume()

        //TODO Only for testing DI and Repository
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_user_search_fragment, container, false)
        return view
    }
}