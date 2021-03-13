package br.iesb.mobile.androidhilt.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.iesb.mobile.androidhilt.R
import br.iesb.mobile.androidhilt.di.RickAndMortyService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), CoroutineScope {

    private var locationId: Int? = null

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    @Inject
    lateinit var service: RickAndMortyService

    @Inject
    lateinit var t: OQuePodeSerFeito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        locationId = intent.getIntExtra("locationId", -9999)

        btGetLocation.setOnClickListener { loadLocation() }
    }

    private fun loadLocation() {

        val x = t.imprimir("bla bla bla")
        println(x)

        val lid = locationId ?: return

        launch {
            val response = service.location(lid)
            tvLocationName.text = response.name
            tvLocationType.text = response.type
            tvLocationDimension.text = response.dimension
        }
    }

}