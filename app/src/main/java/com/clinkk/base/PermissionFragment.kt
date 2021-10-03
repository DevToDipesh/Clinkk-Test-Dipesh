package com.clinkk.base

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import com.clinkk.R
import com.clinkk.base.permDialog.ReqPermissionDialog

/**
 * This is abstract classes for Permission, which includes Camera , Write Storage, Read Storage permissions.
 * - Checking the permission.
 * - Checking the rational permission, one time permission.
 * - Abstract functions for PermissionGranted, Permission is disabled and Permission is denied.
 * - Showing the custom popup for permission Required.
 * */
abstract class PermissionFragment : BaseFragment() {

    protected val CAMERA = Manifest.permission.CAMERA
    protected val R_E_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    protected val W_E_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    protected val RQ_CAMERA = 1004
    protected val RQ_LOCATION = 1005
    protected val PM_CAMERA = arrayOf(CAMERA, W_E_STORAGE, R_E_STORAGE)
    protected val PM_LOCATION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)

    @StringRes
    private var vTitle: Int = 0
    private var vMessage: Int = 0
    private var PERMISSIONS: Array<String>? = null
    var RQ_CODE: Int = 0

    protected abstract fun onPermissionGranted(REQUESTED_FOR: Int)

    protected abstract fun onPermissionDisabled(REQUESTED_FOR: Int)

    protected abstract fun onPermissionDenied(REQUESTED_FOR: Int)


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        processOnRequestPermission(requestCode, permissions, grantResults)
    }

    protected fun hasPermission(
        permissions: Array<String>,
        requestedCode: Int,
        @StringRes reqTitle: Int,
        @StringRes reqDsc: Int
    ): Boolean {
        PERMISSIONS = permissions
        RQ_CODE = requestedCode
        vTitle = reqTitle
        vMessage = reqDsc

        /* check if OS is not smaller than MARSHMALLOW*/
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        /*check if granted @PERMISSIONS*/
        if (hasPermissions(*PERMISSIONS!!)) {
            return true
        }

        /*  can show permission requirement cause*/
        if (shouldShowRationale(PERMISSIONS!!)) {
            /*initiate dialog*/
            val permissionDialog =
                activity?.let {
                    ReqPermissionDialog(
                        it,
                        reqTitle,
                        reqDsc,
                        onPreviouslyDinedListener
                    )
                }
            /* show permission dialog*/
            permissionDialog?.show()
        } else {

            /*asking permission for 1st time*/
            activity?.let { ActivityCompat.requestPermissions(it, PERMISSIONS!!, requestedCode) }
        }
        return false

    }

    private fun hasPermissions(vararg permission: String): Boolean {
        for (PERMISSION in permission) {
            /* checking permission 1 by 1*/
            if (activity?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        PERMISSION
                    )
                } == PackageManager.PERMISSION_DENIED
            ) {
                /* if any permission is not granted
                @return false*/
                return false
            }
        }
        /*All permission are granted*/
        return true
    }

    private fun processOnRequestPermission(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        var isAllowed = true
        for (p in grantResults) {
            if (p == PackageManager.PERMISSION_DENIED) {
                isAllowed = false
                break
            }
        }
        if (isAllowed) {
            onPermissionGranted(requestCode)
        } else if (!shouldShowRationale(permissions)) {

            val dialog = activity?.let {
                ReqPermissionDialog(
                    it,
                    vTitle,
                    vMessage,
                    onManuallyDisabledListener
                )
            }
            dialog?.show()

        } else {
            onPermissionDenied(requestCode)
        }

    }

    private fun shouldShowRationale(permission: Array<String>): Boolean {
        for (p in permission) {
            if (activity?.let {
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        it,
                        p
                    )
                } == true) {
                return true
            }
        }
        return false
    }

    private val onPreviouslyDinedListener = object : ReqPermissionDialog.Listener {
        override fun onPositive() {
            activity?.let { ActivityCompat.requestPermissions(it, PERMISSIONS!!, RQ_CODE) }
        }

        override fun onNegative() {
            onPermissionDisabled(RQ_CODE)
        }
    }
    private val onManuallyDisabledListener = object : ReqPermissionDialog.Listener {
        override fun onPositive() {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", activity?.packageName, null)
            intent.data = uri
            startActivity(intent)
        }

        override fun onNegative() {
            onPermissionDisabled(RQ_CODE)
        }
    }



    protected fun hasCameraAndStoragePermission(): Boolean {
        return hasPermission(
            PM_CAMERA,
            RQ_CAMERA,
            R.string.per_title_camera,
            R.string.per_msg_location
        )
    }
    protected fun hasLocationPermission(): Boolean {
        return hasPermission(
            PM_LOCATION,
            RQ_LOCATION,
            R.string.per_title_camera,
            R.string.per_msg_location
        )
    }

}