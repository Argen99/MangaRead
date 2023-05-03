package com.geektech.mangaread.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.geektech.data.local_db.prefs.SelectedItemsPrefs
import com.geektech.mangaread.databinding.ItemFilterBinding

class TypesAdapter(
    context: Context,
    private val layout: Int,
    private val types: List<String>,
    private val selectedItemsPrefs: SelectedItemsPrefs
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
        binding.checkboxRv.text = types[position]

        binding.checkboxRv.isChecked = selectedItems.contains(binding.checkboxRv.text.toString())

        binding.checkboxRv.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

            }

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                val text = buttonView?.text.toString()
                if (isChecked && !selectedItems.contains(text)){
                    selectedItems.add(text)
                } else {
                    selectedItems.remove(text)
                }
            }
        })

        return row
    }

}