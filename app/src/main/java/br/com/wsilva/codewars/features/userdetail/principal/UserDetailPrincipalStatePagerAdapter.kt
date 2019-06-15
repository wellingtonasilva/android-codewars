package br.com.wsilva.codewars.features.userdetail.principal

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.wsilva.codewars.features.userdetail.challenges.authored.UserDetailAuthoredFragment
import br.com.wsilva.codewars.features.userdetail.challenges.completed.UserDetailCompletedFragment
import br.com.wsilva.codewars.features.userdetail.home.UserDetailHomeFragment

class UserDetailPrincipalStatePagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm)
{
    companion object {
        val PAGE_HOME           = 0
        val PAGE_COMPLETED      = 1
        val PAGE_AUTHORED       = 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when (position)
        {
            PAGE_HOME       -> fragment = UserDetailHomeFragment.newInstance()
            PAGE_COMPLETED  -> fragment = UserDetailCompletedFragment.newInstance()
            PAGE_AUTHORED   -> fragment = UserDetailAuthoredFragment.newInstance()
        }

        return fragment
    }

    override fun getCount(): Int {
        return 3
    }
}