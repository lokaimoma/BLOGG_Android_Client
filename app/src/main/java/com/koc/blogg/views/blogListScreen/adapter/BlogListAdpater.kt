package com.koc.blogg.views.blogListScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.koc.blogg.databinding.BlogListItemBinding
import com.koc.blogg.model.remote.Blog
import com.koc.blogg.util.BlogItemClickedListener
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ActivityComponentBuilder
import java.text.SimpleDateFormat
import java.util.*

/**
Created by kelvin_clark on 7/24/2021 2:00 AM
 */
class BlogListAdpater(private val itemClickedListener: BlogItemClickedListener):
    ListAdapter<Blog, BlogListAdpater.BlogListViewHolder>(DiffUtilCallBack()) {

    private var _binding: BlogListItemBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogListViewHolder {
        _binding = BlogListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogListViewHolder()
    }

    override fun onBindViewHolder(holder: BlogListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun clearBinding() {
        _binding = null
    }

    inner class BlogListViewHolder: RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val blog = getItem(position)
                    itemClickedListener.onClicked(blogId = blog.id)
                }
            }
        }

        fun populateViews(blog: Blog) {
            binding.apply {
                blogTitle.text = blog.title
                blogBody.text = blog.body
                val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val dateTime = dateTimeFormat.parse(blog.createdDate)
                val dateFormatter = SimpleDateFormat("MMMM, dd", Locale.getDefault())
                blogCreatedDate.text = dateFormatter.format(dateTime!!)
            }
        }

    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Blog>() {
        override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean =
            oldItem == newItem

    }


}