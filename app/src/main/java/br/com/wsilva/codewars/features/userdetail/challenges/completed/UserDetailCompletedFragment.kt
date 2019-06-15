package br.com.wsilva.codewars.features.userdetail.challenges.completed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.codewars.R

class UserDetailCompletedFragment: Fragment() {

    companion object {
        val TAG: String = "UserDetailCompletedFragment"
        fun newInstance(): UserDetailCompletedFragment {
            return UserDetailCompletedFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_user_detail_completed_fragment, container, false)
        return view
    }
}