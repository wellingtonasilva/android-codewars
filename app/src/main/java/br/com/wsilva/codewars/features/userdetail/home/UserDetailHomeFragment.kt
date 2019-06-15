package br.com.wsilva.codewars.features.userdetail.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import br.com.wsilva.codewars.R

class UserDetailHomeFragment: Fragment(), UserDetailHomeContract.View {

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_user_detail_home_fragment, container, false)
        return view
    }
}