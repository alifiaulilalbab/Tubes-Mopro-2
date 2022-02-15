package org.d3ifcool.lagaligo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.d3ifcool.lagaligo.databinding.ActivityMainBinding
import org.d3ifcool.lagaligo.network.ApiStatus

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LagaligoViewmodel

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
        const val EXTRA_TYPE = "EXTRA_TYPE"
        const val EXTRA_WISATA = "EXTRA_WISATA"
        const val EXTRA_KEBUDAYAAN = "EXTRA_KEBUDAYAAN"
        const val EXTRA_KULINER = "EXTRA_KULINER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LagaligoViewmodel::class.java)

        viewModel.getData().observe(this, { data ->
            binding.llWisata.setOnClickListener {
                startActivity(
                    Intent(this, ListItemActivity::class.java).putExtra(
                        EXTRA_DATA,
                        data.wisata
                    ).putExtra(EXTRA_TYPE, EXTRA_WISATA)
                )
            }

            binding.llKebuyaan.setOnClickListener {
                startActivity(
                    Intent(this, ListItemActivity::class.java).putExtra(
                        EXTRA_DATA,
                        data.kebudayaan
                    ).putExtra(EXTRA_TYPE, EXTRA_KEBUDAYAAN)
                )
            }

            binding.llKuliner.setOnClickListener {
                startActivity(
                    Intent(this, ListItemActivity::class.java).putExtra(
                        EXTRA_DATA,
                        data.kuliner
                    ).putExtra(EXTRA_TYPE, EXTRA_KULINER)
                )
            }
        })

        viewModel.getStatus().observe(this, {
            updateProgress(it)
        })
    }

    //system loading
    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar
                    .visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}