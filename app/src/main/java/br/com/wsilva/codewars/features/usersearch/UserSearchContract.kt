package br.com.wsilva.codewars.features.usersearch

import br.com.wsilva.codewars.core.BasicPresenter
import br.com.wsilva.codewars.model.dto.UserDTO
import br.com.wsilva.codewars.model.entity.UserEntity

interface UserSearchContract {
    interface View {
        fun showNoResult(display: Boolean)
        fun showList(list: List<UserEntity>)
    }

    interface Presenter: BasicPresenter {
        fun onQueryTextSubmit(query: String)
        fun onError(error: String)
        fun onSuccess(result: UserDTO)
        fun loadUser()
    }
}