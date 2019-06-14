package br.com.wsilva.codewars.features.userdetail.home

import android.support.v4.app.Fragment

class UserDetailHomeFragment: Fragment() {

    companion object {
        val TAG: String = "UserDetailHomeFragment"
        fun newInstance(): UserDetailHomeFragment {
            return UserDetailHomeFragment()
        }
    }
}