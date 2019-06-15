package br.com.wsilva.codewars.model.dao

import android.arch.persistence.room.*
import br.com.wsilva.codewars.core.BasicDAO
import br.com.wsilva.codewars.model.entity.UserLanguagesEntity
import io.reactivex.Flowable

@Dao
interface UserLanguagesDAO: BasicDAO<UserLanguagesEntity> {

    @Query("SELECT * FROM user_languages")
    override fun listAll(): Flowable<List<UserLanguagesEntity>>

    @Query("SELECT * FROM user_languages WHERE user_id = :userId")
    fun listAllByUserId(userId: Long): Flowable<List<UserLanguagesEntity>>

    @Query("SELECT * FROM user_languages WHERE _id = :id")
    override fun get(id: Long): UserLanguagesEntity

    @Query("SELECT * FROM user_languages WHERE user_id = :userId AND language = :language")
    fun getByLanguage(userId: Long, language: String): UserLanguagesEntity

    @Delete
    override fun delete(entity: UserLanguagesEntity): Int

    @Insert
    override fun insert(entity: UserLanguagesEntity): Long

    @Update
    override fun update(entity: UserLanguagesEntity): Int
}