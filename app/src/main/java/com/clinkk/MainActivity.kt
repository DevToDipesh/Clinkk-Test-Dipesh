package com.clinkk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SharedMemory
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.clinkk.sharedPref.KeyValue
import com.clinkk.sharedPref.SharedPref

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

}