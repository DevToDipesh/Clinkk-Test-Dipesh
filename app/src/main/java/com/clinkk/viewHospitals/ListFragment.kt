package com.clinkk.viewHospitals

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.clinkk.R
import com.clinkk.common.loadGif
import com.clinkk.sharedPref.KeyValue
import com.clinkk.sharedPref.SharedPref
import com.clinkk.viewHospitals.adapter.ClinicAdapter
import com.clinkk.viewHospitals.bean.ClinicBean
import com.clinkk.viewHospitals.viewModel.ClinicModel


class ListFragment : Fragment() {
    lateinit var locationManager: LocationManager
    lateinit var recyclerView: RecyclerView
    private val viewModel: ClinicModel by viewModels()
    lateinit var adapter: ClinicAdapter
    lateinit var loadingView: View
    lateinit var vNoData: View

     var list=ArrayList<ClinicBean.Clinic>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_list, container, false)

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SharedPref.get().save(KeyValue.location,true)
        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        recyclerView = view.findViewById(R.id.rvHospitals)
        loadingView = view.findViewById(R.id.ivLoading)
        vNoData = view.findViewById(R.id.vNoData)
        adapter =  ClinicAdapter(list)
        view.findViewById<ImageView>(R.id.ivLoadingGif).loadGif(R.raw.search_raidar,
            CircularProgressDrawable(requireContext())
        )
        recyclerView.adapter = adapter
        registerUpdates()



        try {
            val lm = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            val location = lm!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if (viewModel.isLocationEnabled(requireContext()) == true  && location!=null) {
                viewModel.searchHospitalWithLocation(
                    long = location?.longitude.toString(),
                    lat = location?.latitude.toString(),
                    radius = "420000"
                )

            }else{

                viewModel.searchHospitalWithoutLocation()
            }
        } catch (e: Exception) {
        }
    }

    private fun registerUpdates() {

        viewModel.list.observe(viewLifecycleOwner) {
            try {
                list.addAll(it)
                adapter.notifyDataSetChanged()
                if (it.isNullOrEmpty()) vNoData.visibility=View.VISIBLE else vNoData.visibility=View.GONE
            } catch (e: Exception) {
            }

        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) loadingView.visibility = View.VISIBLE else loadingView.visibility = View.GONE

        }
    }

}