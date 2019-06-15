package br.com.wsilva.codewars.features.userdetail.home

import br.com.wsilva.codewars.core.BasicPresenter
import br.com.wsilva.codewars.model.entity.UserLanguagesEntity

interface UserDetailHomeContract {
    interface View {
        fun showLanguages(list: List<UserLanguagesEntity>)
    }

    interface Presenter: BasicPresenter {
        fun loadLanguages(userId: Long)
    }
}