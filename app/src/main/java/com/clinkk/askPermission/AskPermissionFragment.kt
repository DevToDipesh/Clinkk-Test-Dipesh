package com.clinkk.askPermission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.clinkk.R
import com.clinkk.base.PermissionFragment
import com.clinkk.sharedPref.KeyValue
import com.clinkk.sharedPref.SharedPref
import com.google.android.material.button.MaterialButton

class AskPermissionFragment : PermissionFragment() {
    override fun onPermissionGranted(REQUESTED_FOR: Int) {
        view?.findNavController()?.navigate(R.id.action_permissionFragment_to_listFragment)

    }

    override fun onPermissionDisabled(REQUESTED_FOR: Int) {
    }

    override fun onPermissionDenied(REQUESTED_FOR: Int) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_permission, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.btnLocate).setOnClickListener {
            if (hasLocationPermission()){
                view.findNavController().navigate(R.id.action_permissionFragment_to_listFragment)
            }
        }
    /*    if (SharedPref.get().get(KeyValue.location,false)){
            view.findNavController().navigate(R.id.action_permissionFragment_to_listFragment)
        }*/
        view.findViewById<ImageView>(R.id.ivLanguages).setOnClickListener {
                view.findNavController().navigate(R.id.action_permissionFragment_to_selectLanguageFragment)

        }
    }
}