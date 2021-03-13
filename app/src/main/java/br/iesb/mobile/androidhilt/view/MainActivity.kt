package br.iesb.mobile.androidhilt.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.iesb.mobile.androidhilt.R
import br.iesb.mobile.androidhilt.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
@WithFragmentBindings
class MainActivity : AppCompatActivity() {

    private var locationId: Int? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLoad.setOnClickListener { load() }
        btDetail.setOnClickListener { detail() }
    }

    private fun load() {
        btDetail.isEnabled = false
        viewModel.characters.observe(this) { list ->
            tvName.text = list[0].name
            btDetail.isEnabled = true
        }
        viewModel.getCharacters()
    }
    private fun detail() {
        val it = Intent(this, DetailActivity::class.java)
        it.putExtra("locationId", locationId)
        startActivity(it)
    }

}