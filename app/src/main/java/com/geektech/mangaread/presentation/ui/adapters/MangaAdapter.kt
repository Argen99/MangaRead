package com.geektech.mangaread.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.domain.model.MangaResult
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.databinding.ItemMangaBinding

class MangaAdapter(
    private var listManga: ArrayList<MangaResult>,
    private val onItemClick: (id: String) -> Unit
) : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<MangaResult>) {
        this.listManga = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        return MangaViewHolder(
            ItemMangaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = listManga.size

    inner class MangaViewHolder(private val binding: ItemMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            binding.apply {
                ivMangaImage.loadImage(listManga[position].image)
                tvIssueYear.text =listManga[position].issue_year.toString()
                tvMangaName.text = listManga[position].ru_name
            }
        }
        init {
            itemView.setOnClickListener {
                onItemClick(listManga[absoluteAdapterPosition].id.toString())
            }
        }
    }
}