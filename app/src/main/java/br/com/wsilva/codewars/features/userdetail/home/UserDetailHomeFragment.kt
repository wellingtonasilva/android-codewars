package br.com.wsilva.codewars.features.userdetail.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import br.com.wsilva.codewars.AppApplication
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.constants.AppConstants
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.userdetail.home.di.DaggerUserDetailHomeComponent
import br.com.wsilva.codewars.features.userdetail.home.di.UserDetailHomeModule
import br.com.wsilva.codewars.model.entity.UserLanguagesEntity
import kotlinx.android.synthetic.main.lay_user_detail_home_fragment.*
import javax.inject.Inject

class UserDetailHomeFragment: Fragment(), UserDetailHomeContract.View {

    @Inject
    lateinit var presenter: UserDetailHomePresenter

    companion object {
        val TAG: String = "UserDetailHomeFragment"
        fun newInstance(): UserDetailHomeFragment {
            return UserDetailHomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        DaggerUserDetailHomeComponent
            .builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .userDetailHomeModule(UserDetailHomeModule(this))
            .build()
            .inject(this)
        //Parameter
        if (savedInstanceState == null) {
            val bundle = arguments
            if (bundle != null) {
                presenter.userId = bundle.getLong(AppConstants.KEY_USER_ID)
            }
        } else {
            presenter.userId = savedInstanceState.getLong(AppConstants.KEY_USER_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_user_detail_home_fragment, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadLanguages(presenter.userId)
    }

    override fun showLanguages(list: List<UserLanguagesEntity>) {
        val adapter = UserDetailHomeLanguageAdapter(activity!!, list, object: UserDetailHomeLanguageAdapter.UserDetailHomeLanguageAdapterListener {
            override fun OnClickListener(userLanguagesEntity: UserLanguagesEntity) {
                Toast.makeText(context, userLanguagesEntity.language, Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
}