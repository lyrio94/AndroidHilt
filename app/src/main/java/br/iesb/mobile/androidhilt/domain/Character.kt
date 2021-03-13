package br.iesb.mobile.androidhilt.domain

data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Location,
    var image: String,
    var episode: List<String>,
    var url: String
)

data class Location(
    var id: Int,
    var name: String,
    var url: String,
    var type: String,
    var dimension: String
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class RickAndMortyPage(
    val info: Info,
    val results: List<Character>
)