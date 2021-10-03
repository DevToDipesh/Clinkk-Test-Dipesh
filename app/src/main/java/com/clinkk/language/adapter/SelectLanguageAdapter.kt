package com.clinkk.language.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clinkk.R
import com.clinkk.base.ResourceUtils
import com.clinkk.language.bean.Language
import com.clinkk.language.bean.Languages
import com.clinkk.sharedPref.KeyValue
import com.clinkk.sharedPref.SharedPref
import com.google.android.material.card.MaterialCardView


class SelectLanguageAdapter: RecyclerView.Adapter<SelectLanguageAdapter.ViewHolder>() {
    var languages=Languages.getAvailableLanguages()
    var selectedItem=0
    var lastSelected=0
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val textView: TextView = itemView.findViewById(R.id.tvLanguage)
    val main: MaterialCardView = itemView.findViewById(R.id.cvCard)


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val language=languages.get(position)
        checkSelected(holder,language)

        if (language.abbreviation=="en"){
            holder.textView.text=language.language
        }else{

            holder.textView.text="${language.name} * ${language.language}"
        }
        holder.main.setOnClickListener {
            SharedPref.get().save(KeyValue.current_translation,language.abbreviation)
            lastSelected = selectedItem;
            //Save the position of the current selected item
            selectedItem = position;

            //This update the last item selected
            notifyItemChanged(lastSelected);

            //This update the item selected
            notifyItemChanged(selectedItem);
        }
     /*   val backgroundColor: Int = if (position == selectedItem) ResourceUtils.getColor(R.color.teal_200) else ResourceUtils.getColor( R.color.unselected_color)
        holder.main.setBackgroundColor(backgroundColor)*/

    }

    private fun checkSelected(holder: ViewHolder, language: Language) {

        val selectedLg=SharedPref.get().get(KeyValue.current_translation,"en")
        if (language.abbreviation==selectedLg){
            selectedItem=holder.position
            holder.main.background=ResourceUtils.getDrawable(R.drawable.item_selected)
            holder.main.checkedIcon=ResourceUtils.getDrawable(R.drawable.ic_checkmark1)
        }else{
            holder.main.setBackgroundColor(ResourceUtils.getColor(R.color.unselected_color))
            holder.main.checkedIcon=null

        }
    }

    override fun getItemCount(): Int {
        return languages.size
    }
}