package org.d3ifcool.lagaligo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_item.view.*
import org.d3ifcool.lagaligo.R
import org.d3ifcool.lagaligo.databinding.ActivityListItemBinding
import org.d3ifcool.lagaligo.model.KebudayaanItem
import org.d3ifcool.lagaligo.model.KulinerItem
import org.d3ifcool.lagaligo.model.WisataItem
import org.d3ifcool.lagaligo.ui.MainActivity.Companion.EXTRA_DATA
import org.d3ifcool.lagaligo.ui.MainActivity.Companion.EXTRA_KEBUDAYAAN
import org.d3ifcool.lagaligo.ui.MainActivity.Companion.EXTRA_KULINER
import org.d3ifcool.lagaligo.ui.MainActivity.Companion.EXTRA_TYPE
import org.d3ifcool.lagaligo.ui.MainActivity.Companion.EXTRA_WISATA
import org.d3ifcool.lagaligo.util.AdapterUtil

@Suppress("UNCHECKED_CAST")
class ListItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityListItemBinding
    lateinit var adapterKebudayaan: AdapterUtil<KebudayaanItem>
    lateinit var adapterKuliner: AdapterUtil<KulinerItem>
    lateinit var adapterWisata: AdapterUtil<WisataItem>
    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        type = intent.getStringExtra(EXTRA_TYPE).toString()
        when (type) {
            EXTRA_KEBUDAYAAN -> {
                val data = intent.getSerializableExtra(EXTRA_DATA) as ArrayList<KebudayaanItem>
                setAdapterKebudayaan(binding.rvKebudayaan, data)
            }
            EXTRA_KULINER -> {
                val data = intent.getSerializableExtra(EXTRA_DATA) as ArrayList<KulinerItem>
                setAdapterKuliner(binding.rvKebudayaan, data)
            }
            EXTRA_WISATA -> {
                val data = intent.getSerializableExtra(EXTRA_DATA) as ArrayList<WisataItem>
                setAdapterWisata(binding.rvKebudayaan, data)
            }
        }
    }

    private fun setAdapterKebudayaan(recylerView: RecyclerView, item: ArrayList<KebudayaanItem>) {
        adapterKebudayaan = AdapterUtil(
            R.layout.item_list_item,
            item,
            { view, kebudayaanItem ->
                view.tvJudul.text = kebudayaanItem.namaBudaya
                Glide.with(this).load(kebudayaanItem.imgBudaya).into(view.imageList)
            },
            { postion, kebudayaanItem ->
                startActivity(
                    Intent(this, DetailItemActivity::class.java).putExtra(
                        EXTRA_DATA,
                        kebudayaanItem
                    ).putExtra(EXTRA_TYPE, type)
                )
            })

        val layoutManager = LinearLayoutManager(this)
        recylerView.layoutManager = layoutManager
        recylerView.adapter = adapterKebudayaan
    }

    private fun setAdapterKuliner(recylerView: RecyclerView, item: ArrayList<KulinerItem>) {
        adapterKuliner = AdapterUtil(
            R.layout.item_list_item,
            item,
            { view, kulinerItem ->
                view.tvJudul.text = kulinerItem.namaKuliner
                Glide.with(this).load(kulinerItem.imgKuliner).into(view.imageList)
            },
            { postion, kulinerItem ->
                startActivity(
                    Intent(this, DetailItemActivity::class.java).putExtra(
                        EXTRA_DATA,
                        kulinerItem
                    ).putExtra(EXTRA_TYPE, type)
                )
            })

        val layoutManager = LinearLayoutManager(this)
        recylerView.layoutManager = layoutManager
        recylerView.adapter = adapterKuliner
    }

    private fun setAdapterWisata(recylerView: RecyclerView, item: ArrayList<WisataItem>) {
        adapterWisata = AdapterUtil(
            R.layout.item_list_item,
            item,
            { view, wisataItem ->
                view.tvJudul.text = wisataItem.namaWisata
                Glide.with(this).load(wisataItem.imgWisata).into(view.imageList)
            },
            { postion, wisataItem ->
                startActivity(
                    Intent(this, DetailItemActivity::class.java).putExtra(
                        EXTRA_DATA,
                        wisataItem
                    ).putExtra(EXTRA_TYPE, type)
                )
            })

        val layoutManager = LinearLayoutManager(this)
        recylerView.layoutManager = layoutManager
        recylerView.adapter = adapterWisata
    }
}