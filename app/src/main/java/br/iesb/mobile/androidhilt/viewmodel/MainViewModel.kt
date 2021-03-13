package br.iesb.mobile.androidhilt.viewmodel

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.iesb.mobile.androidhilt.domain.Character
import br.iesb.mobile.androidhilt.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app) {

    val characters = MutableLiveData<List<Character>>()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val charList = repository.getCharactersFromApi(1)
                characters.value = charList
            } catch (t: Throwable) {
                Log.d("RickAndMorty", "[RickAndMortyViewModel] Error on Coroutine getCharacters: ${t.localizedMessage}")
            }
        }
    }



}