package com.geektech.mangaread.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.mangaread.databinding.ItemCommentsBinding

class CommentsAdapter(
    private var listComments: ArrayList<String>,
    private val onItemClick: (id: Int) -> Unit,
    private val isCommentsFragment: Boolean = false
) : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<String>) {
        this.listComments = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
            ItemCommentsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class CommentsViewHolder(_binding: ItemCommentsBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val binding = _binding

        fun onBind(position: Int) {

            if (isCommentsFragment) {
                binding.tvComment.maxLines = Int.MAX_VALUE
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }
    }
}