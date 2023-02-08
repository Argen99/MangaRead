package com.geektech.mangaread.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.geektech.domain.model.Genres
import com.geektech.mangaread.databinding.ItemFilterBinding

class GenreAdapter(
    context: Context,
    private val layout: Int,
    private var genresList: List<Genres>
) : ArrayAdapter<Genres>(context, layout, genresList) {

    private val selectedItems = arrayListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Genres>) {
        this.genresList = list
        notifyDataSetChanged()
    }

    fun getSelectedItems(): ArrayList<String> {
        return selectedItems
    }

    fun clearSelectedItems() {
        selectedItems.clear()
    }

    override fun getCount(): Int = genresList.size

    override fun getItem(position: Int): Genres = genresList[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemFilterBinding
        var row = convertView

        if (row == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = ItemFilterBinding.inflate(inflater, parent, false)
            row = binding.root
        } else {
            binding = ItemFilterBinding.bind(row)
        }
        val item = genresList[position]

        binding.checkboxRv.text = genresList[position].title

        binding.checkboxRv.setOnCheckedChangeListener(object :
            RadioGroup.OnCheckedChangeListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

            }

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    selectedItems.add(buttonView?.text.toString())
                } else {
                    selectedItems.remove(buttonView?.text.toString())
                }
            }
        })

        return row
    }

}