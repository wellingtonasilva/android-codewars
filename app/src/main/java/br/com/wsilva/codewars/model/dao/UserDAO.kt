package br.com.wsilva.codewars.model.dao

import android.arch.persistence.room.*
import br.com.wsilva.codewars.core.BasicDAO
import br.com.wsilva.codewars.model.entity.UserEntity
import io.reactivex.Flowable

@Dao
interface UserDAO: BasicDAO<UserEntity> {

    @Query("SELECT * FROM user")
    override fun listAll(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM user ORDER BY leaderboardPosition ASC")
    fun listAllOrderByRank(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM user ORDER BY _id DESC")
    fun listAllOrderByTimeOfLookUp(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM user WHERE _id = :id")
    override fun get(id: Long): UserEntity

    @Query("SELECT * FROM user WHERE username = :username")
    fun getByUsername(username: String): UserEntity

    @Delete
    override fun delete(entity: UserEntity): Int

    @Insert
    override fun insert(entity: UserEntity): Long

    @Update
    override fun update(entity: UserEntity): Int
}