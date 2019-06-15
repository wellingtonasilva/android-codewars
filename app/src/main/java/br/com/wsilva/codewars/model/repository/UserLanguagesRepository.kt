package br.com.wsilva.codewars.model.repository

import br.com.wsilva.codewars.model.dao.UserLanguagesDAO
import br.com.wsilva.codewars.model.dto.RankDTO
import br.com.wsilva.codewars.model.dto.RanksDTO
import br.com.wsilva.codewars.model.entity.UserLanguagesEntity
import br.com.wsilva.wmc.core.BasicRepository
import io.reactivex.Flowable
import io.reactivex.Single

class UserLanguagesRepository(private val dao: UserLanguagesDAO): BasicRepository<UserLanguagesEntity>(dao) {

    override fun listAll(): Flowable<List<UserLanguagesEntity>> = dao.listAll()
    fun listAllByUserId(userId: Long): Flowable<List<UserLanguagesEntity>> = dao.listAllByUserId(userId)
    override fun get(id: Long): UserLanguagesEntity = dao.get(id)
    fun getByLanguage(userId: Long, language: String): UserLanguagesEntity = dao.getByLanguage(userId, language)
    override fun delete(entity: UserLanguagesEntity): Int = dao.delete(entity)
    override fun insert(entity: UserLanguagesEntity): Long = dao.insert(entity)
    override fun update(entity: UserLanguagesEntity): Int = dao.update(entity)

    fun save(userId: Long, ranks: RanksDTO): Single<Boolean> {
        return Single.create {
            try {
                ranks.languages.also {
                    it?.forEach { item -> save(userId, item.key, item.value) }
                }
                it.onSuccess(true)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun save(userId: Long, language: String, rankDTO: RankDTO) {
        var entity = getByLanguage(userId, language)

        if (entity == null) {
            entity = UserLanguagesEntity(userId = userId, name = rankDTO.name, color = rankDTO.color,
                language = language, rank = rankDTO.rank, score = rankDTO.score)
            insert(entity)
        } else {
            with (entity) {
                name = rankDTO.name
                color = rankDTO.color
                rank = rankDTO.rank
                score = rankDTO.score
            }
            update(entity)
        }
    }
}