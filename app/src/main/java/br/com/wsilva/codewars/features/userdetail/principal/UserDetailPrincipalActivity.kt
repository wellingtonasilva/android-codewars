package br.com.wsilva.codewars.features.userdetail.principal

import android.os.Bundle
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.core.BasicActivity

class UserDetailPrincipalActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_user_detail_principal_activity)
        //Configuração inicial
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(UserDetailPrincipalFragment.TAG)
        if (fragment == null) {
            fragment = UserDetailPrincipalFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout,
            UserDetailPrincipalFragment.TAG
        )
    }
}