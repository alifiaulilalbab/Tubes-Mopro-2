package org.d3ifcool.lagaligo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseLagaligo(

	@field:SerializedName("kuliner")
	val kuliner: ArrayList<KulinerItem>? = arrayListOf(),

	@field:SerializedName("wisata")
	val wisata: ArrayList<WisataItem>? = arrayListOf(),

	@field:SerializedName("kebudayaan")
	val kebudayaan: ArrayList<KebudayaanItem>? = arrayListOf()
) : Serializable

data class KulinerItem(

	@field:SerializedName("kontak")
	val kontak: String? = "",

	@field:SerializedName("alamat_kuliner")
	val alamatKuliner: String? = "",

	@field:SerializedName("img_kuliner")
	val imgKuliner: String? = "",

	@field:SerializedName("nama_kuliner")
	val namaKuliner: String? = "",

	@field:SerializedName("lokasi_kuliner")
	val lokasiKuliner: String? = "",

	@field:SerializedName("id")
	val id: Int? = 0,

	@field:SerializedName("detail_kuliner")
	val detailKuliner: String? = ""
) : Serializable

data class KebudayaanItem(

	@field:SerializedName("img_budaya")
	val imgBudaya: String? = "",

	@field:SerializedName("detail_budaya")
	val detailBudaya: String? = "",

	@field:SerializedName("web")
	val web: String? = "",

	@field:SerializedName("nama_budaya")
	val namaBudaya: String? = "",

	@field:SerializedName("id")
	val id: Int? = 0
) : Serializable

data class WisataItem(

	@field:SerializedName("img_wisata")
	val imgWisata: String? = "",

	@field:SerializedName("lokasi_wisata")
	val lokasiWisata: String? = "",

	@field:SerializedName("web")
	val web: String? = "",

	@field:SerializedName("nama_wisata")
	val namaWisata: String? = "",

	@field:SerializedName("id")
	val id: Int? = 0,

	@field:SerializedName("detail_wisata")
	val detailWisata: String? = ""
) : Serializable
