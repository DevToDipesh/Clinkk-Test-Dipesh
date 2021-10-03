package com.clinkk.base

import androidx.fragment.app.Fragment



 open class BaseFragment : Fragment() {




    var loader: Loader? = null
    protected fun showLoader() {
        try {
            loader = null
            loader = context?.let { Loader(it) }
            if (loader != null && !loader?.isShowing!!) {
                loader?.show()
            }
        } catch (e: Exception) {
        }

    }

    protected fun hideLoader() {
        try {
            if (loader != null && loader?.isShowing!!) {
                loader?.dismiss()
            }
            loader = null
        } catch (e: Exception) {
        }

    }


}