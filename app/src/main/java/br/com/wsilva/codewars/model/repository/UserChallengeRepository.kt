package br.com.wsilva.codewars.model.repository

import br.com.wsilva.codewars.model.dao.UserChallengeDAO
import br.com.wsilva.codewars.model.dto.ChallengesCompletedDTO
import br.com.wsilva.codewars.model.entity.UserChallengeEntity
import br.com.wsilva.wmc.core.BasicRepository
import io.reactivex.Flowable
import io.reactivex.Single

class UserChallengeRepository(private val dao: UserChallengeDAO): BasicRepository<UserChallengeEntity>(dao) {

    override fun listAll(): Flowable<List<UserChallengeEntity>> = dao.listAll()
    fun listAllByUserId(userId: Long): Flowable<List<UserChallengeEntity>> = dao.listAllByUserId(userId)
    override fun get(id: Long): UserChallengeEntity  = dao.get(id)
    override fun delete(entity: UserChallengeEntity): Int = dao.delete(entity)
    override fun insert(entity: UserChallengeEntity): Long = dao.insert(entity)
    override fun update(entity: UserChallengeEntity): Int  = dao.update(entity)

    fun save(userId: Long, challengesCompleted: ChallengesCompletedDTO, page: Int): Single<Int> {
        return Single.create {
            challengesCompleted.data.forEach { item ->
                val entity = UserChallengeEntity(userId = userId, challengeId = item.id, name = item.name,
                    completedAt = item.completedAt.toString(), slug = item.slug, page = page,
                    completedLanguages = item.completedLanguages.toString())
                insert(entity)
            }
            it.onSuccess(challengesCompleted.totalPages)
        }
    }
}