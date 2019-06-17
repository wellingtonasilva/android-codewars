package br.com.wsilva.codewars.features.userdetail.challenges.completed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.codewars.AppApplication
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.constants.AppConstants
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.userdetail.challenges.completed.di.DaggerUserDetailCompletedComponent
import br.com.wsilva.codewars.features.userdetail.challenges.completed.di.UserDetailCompletedModule
import br.com.wsilva.codewars.model.entity.UserChallengeEntity
import kotlinx.android.synthetic.main.lay_user_detail_completed_fragment.*
import javax.inject.Inject

class UserDetailCompletedFragment: Fragment(), UserDetailCompletedContract.View {

    @Inject
    lateinit var presenter: UserDetailCompletedPresenter

    companion object {
        val TAG: String = "UserDetailCompletedFragment"
        fun newInstance(): UserDetailCompletedFragment {
            return UserDetailCompletedFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //DI
        DaggerUserDetailCompletedComponent.builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .userDetailCompletedModule(UserDetailCompletedModule(this))
            .build()
            .inject(this)
        //Parameter
        if (savedInstanceState == null) {
            val bundle = arguments
            if (bundle != null) {
                presenter.userId = bundle.getLong(AppConstants.KEY_USER_ID)
                presenter.username = bundle.getString(AppConstants.KEY_USER_NAME)
            }
        } else {
            presenter.userId = savedInstanceState.getLong(AppConstants.KEY_USER_ID)
            presenter.username = savedInstanceState.getString(AppConstants.KEY_USER_NAME)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_user_detail_completed_fragment, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCompletedChallenges(presenter.userId, presenter.username)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showCompletedChallenges(list: List<UserChallengeEntity>) {
        val adapter = UserDetailCompletedAdapter(activity!!, list, object: UserDetailCompletedAdapter.UserDetailCompletedAdapterListener {
            override fun OnClickListener(userChallengeEntity: UserChallengeEntity) {
            }
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
    }
}