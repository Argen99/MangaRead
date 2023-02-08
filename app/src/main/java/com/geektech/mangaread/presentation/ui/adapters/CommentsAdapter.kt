package com.geektech.mangaread.presentation.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.geektech.domain.model.MangaComments
import com.geektech.mangaread.core.extensions.gone
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.core.extensions.visible
import com.geektech.mangaread.databinding.ItemCommentsBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class CommentsAdapter(
    private var listComments: List<MangaComments>,
    private val onItemClick: (id: Int) -> Unit,
    private val isCommentsFragment: Boolean = false
) : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<MangaComments>) {
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
        return listComments.size
    }

    inner class CommentsViewHolder(_binding: ItemCommentsBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val binding = _binding

        fun onBind(position: Int) {
            binding.ivUserAvatar.loadImage(listComments[position].user.image_file)
            binding.tvUserName.text = listComments[position].user.username
            binding.tvComment.text = listComments[position].text

        }

        init {
            itemView.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
            if (isCommentsFragment) {
                binding.tvComment.maxLines = Int.MAX_VALUE
                binding.bgGradient.gone()
            } else {
                binding.tvComment.maxLines = 4
                binding.bgGradient.visible()
            }
        }
    }
}