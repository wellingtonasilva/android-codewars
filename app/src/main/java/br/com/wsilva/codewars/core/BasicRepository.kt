package br.com.wsilva.wmc.core

import io.reactivex.Flowable

abstract class BasicRepository<T> {
    abstract fun listAll() : Flowable<List<T>>
    abstract fun get(id : Long) : T
    abstract fun delete(entity : T) : Int
    abstract fun insert(entity : T) : Long
    abstract fun update(entity : T) : Int
}