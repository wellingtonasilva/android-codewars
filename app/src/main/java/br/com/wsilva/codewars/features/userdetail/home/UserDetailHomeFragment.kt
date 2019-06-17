package br.com.wsilva.codewars.features.userdetail.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import br.com.wsilva.codewars.AppApplication
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.constants.AppConstants
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.userdetail.home.di.DaggerUserDetailHomeComponent
import br.com.wsilva.codewars.features.userdetail.home.di.UserDetailHomeModule
import br.com.wsilva.codewars.model.entity.UserEntity
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(AppConstants.KEY_USER_ID, presenter.userId)
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadUser(presenter.userId)
    }

    override fun showUser(userEntity: UserEntity) {
        lblUsername.text = userEntity.username
        lblName.text = context!!.getString(R.string.app_name_user, userEntity.name)
        lblClan.text = context!!.getString(R.string.app_user_clan, userEntity.clan)
        lblSkills.text = context!!.getString(R.string.app_user_skills, userEntity.skills)
        lblRank.text = context!!.getString(R.string.app_rank, userEntity.overallName)
        lblHonor.text = context!!.getString(R.string.app_honor, userEntity.honor)
        lblLeaderboardPosition.text = context!!.getString(R.string.app_leaderboard_position, userEntity.leaderboardPosition)
        lblTotalLanguagesTrained.text = context!!.getString(R.string.app_total_languages_trained, userEntity.totalLanguagesTrained)
        lblHighestTrained.text = context!!.getString(R.string.app_highest_trained, userEntity.highestTrained)
    }
}