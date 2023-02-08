package com.geektech.mangaread.presentation.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.geektech.mangaread.databinding.ItemFilterBinding

class TypesAdapter(
    context: Context,
    private val layout: Int,
    private val types: List<String>
): ArrayAdapter<String>(context, layout, types) {

    private val selectedItems = arrayListOf<String>()

    fun getSelectedItems(): ArrayList<String> {
        return selectedItems
    }

    fun clearSelectedItems() {
        selectedItems.clear()
    }

    override fun getCount(): Int = types.size

    override fun getItem(position: Int): String = types[position]

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
        val item = types[position]
        binding.checkboxRv.text = item

        binding.checkboxRv.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

            }

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    selectedItems.add(buttonView?.text.toString())
                } else {
                    selectedItems.remove(buttonView?.text.toString())
                }
            }
        })

        return row
    }

}