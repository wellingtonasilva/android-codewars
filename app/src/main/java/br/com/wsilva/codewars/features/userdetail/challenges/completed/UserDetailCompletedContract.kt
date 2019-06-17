package br.com.wsilva.codewars.features.userdetail.challenges.completed

import br.com.wsilva.codewars.core.BasicPresenter
import br.com.wsilva.codewars.model.entity.UserChallengeEntity

interface UserDetailCompletedContract {
    interface View {
        fun showCompletedChallenges(list: List<UserChallengeEntity>)
    }

    interface Presenter: BasicPresenter {
        fun loadCompletedChallenges(userId: Long, username: String)
    }
}