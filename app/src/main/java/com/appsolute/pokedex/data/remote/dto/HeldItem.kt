package com.appsolute.pokedex.data.remote.dto

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)