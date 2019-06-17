package br.com.wsilva.codewars.features.userdetail.home

import br.com.wsilva.codewars.core.BasicPresenter
import br.com.wsilva.codewars.model.entity.UserEntity

interface UserDetailHomeContract {
    interface View {
        fun showUser(userEntity: UserEntity)
    }

    interface Presenter: BasicPresenter {
        fun loadUser(userId: Long)
    }
}