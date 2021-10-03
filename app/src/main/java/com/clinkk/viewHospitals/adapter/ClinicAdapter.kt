package com.clinkk.viewHospitals.adapter

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.clinkk.R
import com.clinkk.common.loadImage
import com.clinkk.viewHospitals.GetCurrentTranslationData
import com.clinkk.viewHospitals.bean.ClinicBean
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView


class ClinicAdapter(val clinicDetails: List<ClinicBean.Clinic>): RecyclerView.Adapter<ClinicAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var imageView=itemView.findViewById<ShapeableImageView>(R.id.ivImage)
        var clinicName=itemView.findViewById<TextView>(R.id.tvClinic)
        var rating=itemView.findViewById<TextView>(R.id.rating)
        var distance=itemView.findViewById<TextView>(R.id.tvDistance)
        var minutes=itemView.findViewById<TextView>(R.id.tvMinutes)
        var description=itemView.findViewById<TextView>(R.id.tvDescription)
        var openToday=itemView.findViewById<TextView>(R.id.tvOpenToday)
        var googleMaps=itemView.findViewById<MaterialButton>(R.id.btGoogleMaps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clinic, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val clinicDetail= clinicDetails[position]

        val translationData=GetCurrentTranslationData.getData(clinicDetail.translations)
        val options= RequestOptions()
            .placeholder(CircularProgressDrawable(holder.itemView.context))
        if (!clinicDetail.images.isNullOrEmpty()){

            holder.imageView.loadImage(clinicDetail.images[0], CircularProgressDrawable(holder.itemView.context))
        }
        holder.clinicName.text=translationData.name
        holder.description.text="${translationData.address.line}, ${translationData.address.city} ,${translationData.address.state} ${translationData.address.zipcode}"
        holder.openToday.text=Html.fromHtml("<b>Open "+clinicDetail.timings.get(0).day+"-${clinicDetail.timings.get(clinicDetail.timings.size-1).day} </b> ${clinicDetail.timings.get(0).shifts.get(0).opening_time}-${clinicDetail.timings.get(0).shifts.get(0).closing_time}")
        holder.distance.text=" ${clinicDetail.distance.value} ${clinicDetail.distance.unit}"
   holder.googleMaps.setOnClickListener {
       val i = Intent(
           Intent.ACTION_VIEW,
           Uri.parse("http://maps.google.com/maps?q=loc:" + clinicDetail.address.lat.toString() + "," + clinicDetail.address.lon.toString())
       )
       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Only if initiating from a Broadcast Receiver

       val mapsPackageName = "com.google.android.apps.maps"
       if (isPackageExisted(holder.itemView.context, mapsPackageName)) {
           i.setClassName(mapsPackageName, "com.google.android.maps.MapsActivity")
           i.setPackage(mapsPackageName)
       }
       holder.itemView.context.startActivity(i)

   }
    }
    private fun isPackageExisted(context: Context, targetPackage: String): Boolean {
        val pm: PackageManager = context.getPackageManager()
        try {
            val info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }
    override fun getItemCount(): Int = clinicDetails.size
}