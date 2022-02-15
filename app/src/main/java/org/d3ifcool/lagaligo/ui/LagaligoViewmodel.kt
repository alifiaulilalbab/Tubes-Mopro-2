package org.d3ifcool.lagaligo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.lagaligo.model.ResponseLagaligo
import org.d3ifcool.lagaligo.network.ApiLagaligo
import org.d3ifcool.lagaligo.network.ApiStatus

class LagaligoViewmodel : ViewModel() {
    private val lagaLigo = MutableLiveData<ResponseLagaligo>()
    private val status = MutableLiveData<ApiStatus>()

    //data status
    fun getStatus(): LiveData<ApiStatus> = status

    fun getData(): LiveData<ResponseLagaligo> = lagaLigo

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                requestData()
            }
        }
    }

    private suspend fun requestData() {
        try {
            status.postValue(ApiStatus.LOADING)
            val result = ApiLagaligo.service.getListLagaligo()
            lagaLigo.postValue(result)
            Log.w("MainActivity", result.toString())
            status.postValue(ApiStatus.SUCCESS)
        } catch (e: Exception) {
            status.postValue(ApiStatus.FAILED)
        }
    }
}