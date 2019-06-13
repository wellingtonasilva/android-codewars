package br.com.wsilva.codewars.features.usersearch

import android.os.Bundle
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.core.BasicActivity

class UserSearchActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_user_search_activity)
        //Configuração inicial
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(UserSearchFragment.TAG)
        if (fragment == null) {
            fragment = UserSearchFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, UserSearchFragment.TAG)
    }
}