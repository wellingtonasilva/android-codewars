package br.com.wsilva.codewars.features.usersearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.Toast
import br.com.wsilva.codewars.AppApplication
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.di.AppModule
import br.com.wsilva.codewars.features.usersearch.di.DaggerUserSearchComponent
import br.com.wsilva.codewars.features.usersearch.di.UserSearchModule
import br.com.wsilva.codewars.model.entity.UserEntity
import kotlinx.android.synthetic.main.lay_user_search_fragment.*
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
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        inflater?.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean
            {
                searchItem?.collapseActionView()
                presenter.onQueryTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean
            {
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.loadUser()
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

    override fun showNoResult(display: Boolean) {
        txtMessage.visibility = if (display) View.VISIBLE else View.INVISIBLE
        recyclerView.visibility = if (display) View.INVISIBLE else View.VISIBLE
    }

    override fun showList(list: List<UserEntity>) {
        val adapter = UserSearchAdapter(activity!!, list, object: UserSearchAdapter.UserSearchAdapterListener {
            override fun OnClickListener(user: UserEntity) {
                Toast.makeText(context, user.username, Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
}