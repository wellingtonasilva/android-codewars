package br.com.wsilva.codewars.model.repository

import br.com.wsilva.codewars.model.dao.UserDAO
import br.com.wsilva.codewars.model.dto.UserDTO
import br.com.wsilva.codewars.model.entity.UserEntity
import br.com.wsilva.wmc.core.BasicRepository
import io.reactivex.Flowable
import io.reactivex.Single

class UserRepository(private val dao: UserDAO): BasicRepository<UserEntity>(dao) {

    override fun listAll(): Flowable<List<UserEntity>> = dao.listAll()
    fun listAllOrderByRank(): Flowable<List<UserEntity>> = dao.listAllOrderByRank()
    fun listAllOrderByTimeOfLookUp(): Flowable<List<UserEntity>> = dao.listAllOrderByTimeOfLookUp()
    override fun get(id: Long): UserEntity = dao.get(id)
    fun getByUsername(username: String): UserEntity = dao.getByUsername(username)
    override fun delete(entity: UserEntity): Int = dao.delete(entity)
    override fun insert(entity: UserEntity): Long = dao.insert(entity)
    override fun update(entity: UserEntity): Int = dao.update(entity)

    fun save(userDTO: UserDTO): Single<Boolean> {
        return Single.create {it
            try {
                // Exist in the local database
                var entity = getByUsername(userDTO.username)

                // if not exist create one
                if (entity == null) {
                    entity = UserEntity(username = userDTO.username, name = userDTO.name,
                        leaderboardPosition = userDTO.leaderboardPosition, honor = userDTO.honor,
                        clan = userDTO.clan)
                    it.onSuccess(insert(entity) > 0)
                } else {
                    entity.clan = userDTO.clan
                    entity.honor = userDTO.honor
                    entity.leaderboardPosition = userDTO.leaderboardPosition
                    entity.name = userDTO.name
                    it.onSuccess(update(entity) > 0)
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}