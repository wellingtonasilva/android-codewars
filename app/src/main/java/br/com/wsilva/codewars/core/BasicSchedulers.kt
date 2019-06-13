package br.com.wsilva.codewars.core

import io.reactivex.Scheduler

interface BasicSchedulers {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}