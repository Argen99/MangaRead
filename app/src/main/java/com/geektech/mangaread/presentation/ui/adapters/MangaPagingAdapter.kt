package com.geektech.mangaread.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geektech.domain.model.MangaResult
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.databinding.ItemMangaBinding

class MangaPagingAdapter(
    private val onItemCLick: (id: String) -> Unit,
) : PagingDataAdapter<MangaResult, MangaPagingAdapter.MangaViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MangaViewHolder(
        ItemMangaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class MangaViewHolder(private val binding: ItemMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(manga: MangaResult) = with(binding) {
            ivMangaImage.loadImage(manga.image)
            tvIssueYear.text = manga.issue_year.toString()
            tvMangaName.text = manga.ru_name
        }
        init {
            itemView.setOnClickListener {
                onItemCLick(getItem(absoluteAdapterPosition)?.id.toString())
            }
        }
    }
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<MangaResult>(){
            override fun areItemsTheSame(oldItem: MangaResult, newItem: MangaResult): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MangaResult, newItem: MangaResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}
