package br.com.wsilva.codewars.model.dao

import android.arch.persistence.room.*
import br.com.wsilva.codewars.core.BasicDAO
import br.com.wsilva.codewars.model.entity.UserChallengeEntity
import io.reactivex.Flowable

@Dao
interface UserChallengeDAO: BasicDAO<UserChallengeEntity> {
    @Query("SELECT * FROM user_challenge")
    override fun listAll(): Flowable<List<UserChallengeEntity>>

    @Query("SELECT * FROM user_challenge WHERE user_id = :userId")
    fun listAllByUserId(userId: Long): Flowable<List<UserChallengeEntity>>

    @Query("SELECT * FROM user_challenge WHERE _id = :id")
    override fun get(id: Long): UserChallengeEntity

    @Delete
    override fun delete(entity: UserChallengeEntity): Int

    @Insert
    override fun insert(entity: UserChallengeEntity): Long

    @Update
    override fun update(entity: UserChallengeEntity): Int
}