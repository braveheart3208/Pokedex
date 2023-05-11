package com.appsolute.pokedex.application.extension

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

fun String.toUpperCaseFirstLetter(): String {
    return if (this.isEmpty()) "" else this.replaceFirst(
        oldChar = this[0],
        newChar = this[0].uppercaseChar(),
        ignoreCase = true
    )
}