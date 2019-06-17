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

    fun save(userDTO: UserDTO): Single<UserDTO> {
        return Single.create {it
            try {
                // Exist in the local database
                var entity = getByUsername(userDTO.username)

                // if not exist create one
                if (entity == null) {
                    entity = UserEntity(username = userDTO.username, name = userDTO.name,
                        leaderboardPosition = userDTO.leaderboardPosition, honor = userDTO.honor,
                        clan = userDTO.clan, overallColor = userDTO.ranks.overall.color,
                        overallName = userDTO.ranks.overall.name, overallRank = userDTO.ranks.overall.rank,
                        overallScore = userDTO.ranks.overall.score, totalAuthored = userDTO.codeChallenges.totalAuthored,
                        totalCompleted = userDTO.codeChallenges.totalCompleted, skills = userDTO.skills.toString(),
                        totalLanguagesTrained = getTotalLanguagesTrained(userDTO), highestTrained = getHighestTrained(userDTO))
                    val id = insert(entity)
                    userDTO.id = id
                    it.onSuccess(userDTO)
                } else {
                    with (entity) {
                        clan = userDTO.clan
                        honor = userDTO.honor
                        leaderboardPosition = userDTO.leaderboardPosition
                        name = userDTO.name
                        overallColor = userDTO.ranks.overall.color
                        overallName = userDTO.ranks.overall.name
                        overallRank = userDTO.ranks.overall.rank
                        overallScore = userDTO.ranks.overall.score
                        totalAuthored = userDTO.codeChallenges.totalAuthored
                        totalCompleted = userDTO.codeChallenges.totalCompleted
                        skills = userDTO.skills.toString()
                        totalLanguagesTrained = getTotalLanguagesTrained(userDTO)
                        highestTrained = getHighestTrained(userDTO)
                    }

                    update(entity)
                    userDTO.id = entity.id
                    it.onSuccess(userDTO)
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun getTotalLanguagesTrained(userDTO: UserDTO): Int {
        userDTO.ranks.languages.also {
            return it!!.size
        }
        return 0
    }

    fun getHighestTrained(userDTO: UserDTO): String {
        var max = 0
        var language = ""
        userDTO.ranks.languages?.forEach { item ->
            if (item.value.score > max) {
                max = item.value.score
                language =  "${item.key}(${item.value.name})"
            }
        }
        return language
    }
}