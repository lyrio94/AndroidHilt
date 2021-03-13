package br.iesb.mobile.androidhilt.repository

import br.iesb.mobile.androidhilt.di.RickAndMortyService
import br.iesb.mobile.androidhilt.domain.Character
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: RickAndMortyService
) {

    suspend fun getCharactersFromApi(page: Int): List<Character> {
        return service.characterList(page).results
    }

}