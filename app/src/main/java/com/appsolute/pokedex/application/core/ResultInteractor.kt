package com.appsolute.pokedex.application.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Alex Toan Duong on 28/04/2023.
 * This project belongs to Alex Toan Duong.
 * Do Not Copy in any circumstance
 * Please Contact braveheart3208@gmail.com
 * or minhtoanduongngo@outlook.com for more information
 */
abstract class ResultInteractor<in P, R> {
    abstract val dispatcher: CoroutineDispatcher

    suspend operator fun invoke(params: P): R {
        return withContext(dispatcher) { doWork(params) }
    }

    protected abstract suspend fun doWork(params: P): R
}