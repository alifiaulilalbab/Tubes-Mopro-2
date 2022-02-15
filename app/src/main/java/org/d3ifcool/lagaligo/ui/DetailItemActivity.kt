package org.d3ifcool.lagaligo.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.d3ifcool.lagaligo.R
import org.d3ifcool.lagaligo.databinding.ActivityDetailItemBinding
import org.d3ifcool.lagaligo.model.KebudayaanItem
import org.d3ifcool.lagaligo.model.KulinerItem
import org.d3ifcool.lagaligo.model.WisataItem
import org.d3ifcool.lagaligo.ui.MainActivity.Companion.EXTRA_DATA
import org.d3ifcool.lagaligo.ui.MainActivity.Companion.EXTRA_TYPE


class DetailItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailItemBinding
    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        type = intent.getStringExtra(EXTRA_TYPE).toString()

        when (type) {
            MainActivity.EXTRA_KEBUDAYAAN -> {
                val dataKebudayaan = intent.getSerializableExtra(EXTRA_DATA) as KebudayaanItem
                setData(
                    dataKebudayaan.namaBudaya.toString(),
                    dataKebudayaan.detailBudaya.toString(), dataKebudayaan.imgBudaya.toString()
                )
                binding.btWebsite.setOnClickListener {
                    openWebsite(dataKebudayaan.web.toString())
                }
            }
            MainActivity.EXTRA_KULINER -> {
                val dataKuliner = intent.getSerializableExtra(EXTRA_DATA) as KulinerItem
                setData(
                    dataKuliner.namaKuliner.toString(),
                    dataKuliner.detailKuliner.toString(), dataKuliner.imgKuliner.toString()
                )
                binding.tvAlamat.text = StringBuilder(getString(R.string.alamat)).append(dataKuliner.alamatKuliner)
                binding.tvKontak.text = StringBuilder(getString(R.string.kontak)).append(dataKuliner.kontak)
                binding.btWebsite.visibility = View.GONE
                binding.btLokasi.setOnClickListener {
                    openWebsite(dataKuliner.lokasiKuliner.toString())
                }
                binding.btWebsite.visibility = View.GONE
            }
            MainActivity.EXTRA_WISATA -> {
                val dataWisata = intent.getSerializableExtra(EXTRA_DATA) as WisataItem
                setData(
                    dataWisata.namaWisata.toString(),
                    dataWisata.detailWisata.toString(), dataWisata.imgWisata.toString()
                )
                binding.btLokasi.setOnClickListener {
                    openWebsite(dataWisata.lokasiWisata.toString())
                }
                binding.btWebsite.setOnClickListener {
                    openWebsite(dataWisata.web.toString())
                }
            }
        }

    }

    private fun setData(judul: String, desksripsi: String, image: String) {
        binding.tvJudul.text = judul
        binding.tvDeksripsi.text = desksripsi
        Glide.with(this).load(image).into(binding.imDetail)
    }

    private fun openWebsite(url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}