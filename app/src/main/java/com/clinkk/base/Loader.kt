package com.clinkk.base

import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.annotation.RawRes
import com.clinkk.R


class Loader(context: Context) : BaseDialog(context) {
    var mCTx = context

    @RawRes
    var loaderGif = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)
        setDimBlur(window)
    }
}