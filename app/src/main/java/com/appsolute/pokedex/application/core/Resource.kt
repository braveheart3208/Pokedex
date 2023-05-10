package com.appsolute.pokedex.application.core

sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class OnSuccess<T>(data: T) : Resource<T>(data)
    class OnError<T>(data: T? = null, message: UiText? = null) : Resource<T>(data, message)
    class OnLoading<T>(data: T? = null) : Resource<T>(data)
}