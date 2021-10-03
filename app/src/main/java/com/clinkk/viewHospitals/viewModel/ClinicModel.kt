package com.clinkk.viewHospitals.viewModel

import android.app.Application
import android.content.Context
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.clinkk.retrofit.RetrofitService
import com.clinkk.sharedPref.SharedPref
import com.clinkk.viewHospitals.bean.ClinicBean

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClinicModel(application: Application) : AndroidViewModel(application) {
    val list by lazy { MutableLiveData<List<ClinicBean.Clinic>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    private val apiService = RetrofitService()
    private  val TAG = "HospitalModel"

    fun searchHospitalWithLocation(lat: String, long: String, radius: String) {

        apiService.searchWithLocation(lat, long, radius).enqueue(object : Callback<ClinicBean?> {
            override fun onResponse(call: Call<ClinicBean?>, response: Response<ClinicBean?>) {
                loadError.value = false
                loading.value = false
                list.postValue(response.body()?.clinics)
            }

            override fun onFailure(call: Call<ClinicBean?>, t: Throwable) {
                t.printStackTrace()
                loading.value = false
                loadError.value = true
            }
        })
        /*disposable.add(
            apiService.searchWithLocation(lat, long, radius)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<ClinicBean>() {
                    override fun onSuccess(login: ClinicBean) {
                        loadError.value = false
                        loading.value = false
                        list.postValue(login.clinics)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                    }
                })
        )*/
    }

    fun searchHospitalWithoutLocation() {
        apiService.searchWithOutLocation().enqueue(object : Callback<ClinicBean?> {
            override fun onResponse(call: Call<ClinicBean?>, response: Response<ClinicBean?>) {
                loadError.value = false
                loading.value = false
                list.postValue(response.body()?.clinics)
            }

            override fun onFailure(call: Call<ClinicBean?>, t: Throwable) {
                t.printStackTrace()
                loading.value = false
                loadError.value = true            }
        })
   /*     disposable.add(
            apiService.searchWithOutLocation().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                DisposableSingleObserver<ClinicBean>() {
                override fun onSuccess(login: ClinicBean) {


                }

                override fun onError(e: Throwable) {

                }
            })
        )*/
    }
    fun isLocationEnabled(context: Context): Boolean? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // This is a new method provided in API 28
            val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            lm.isLocationEnabled
        } else {
            // This was deprecated in API 28
            val mode: Int = Settings.Secure.getInt(
                context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                Settings.Secure.LOCATION_MODE_OFF
            )
            mode != Settings.Secure.LOCATION_MODE_OFF
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}