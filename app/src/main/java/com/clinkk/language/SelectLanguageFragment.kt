package com.clinkk.language

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clinkk.R
import com.clinkk.language.adapter.SelectLanguageAdapter
import com.google.android.material.button.MaterialButton


class SelectLanguageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_languge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rvLanguages).adapter=SelectLanguageAdapter()
view.findViewById<MaterialButton>(R.id.materialButton).setOnClickListener {
    activity?.onBackPressed()
}
    }

}