package br.com.wsilva.codewars.model.repository

import br.com.wsilva.codewars.model.dao.UserDAO
import br.com.wsilva.codewars.model.entity.UserEntity
import br.com.wsilva.wmc.core.BasicRepository
import io.reactivex.Flowable

class UserRepository(private val dao: UserDAO): BasicRepository<UserEntity>(dao) {

    override fun listAll(): Flowable<List<UserEntity>> = dao.listAll()
    override fun get(id: Long): UserEntity = dao.get(id)
    override fun delete(entity: UserEntity): Int = dao.delete(entity)
    override fun insert(entity: UserEntity): Long = dao.insert(entity)
    override fun update(entity: UserEntity): Int = dao.update(entity)
}