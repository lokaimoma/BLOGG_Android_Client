package com.koc.blogg.view.blogActivity.blogListScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.koc.blogg.databinding.BlogListItemBinding
import com.koc.blogg.model.remote.Blog
import com.koc.blogg.util.BlogItemClickedListener
import java.text.SimpleDateFormat
import java.util.*

/**
Created by kelvin_clark on 7/20/2021 2:26 PM
 */
class BlogListAdapter(private val blogItemClickedListener: BlogItemClickedListener) :
    ListAdapter<Blog, BlogListAdapter.BlogListViewHolder>(DiffUtilCallback()) {

    private var _binding: BlogListItemBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogListViewHolder {
        _binding = BlogListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogListViewHolder()
    }

    override fun onBindViewHolder(holder: BlogListViewHolder, position: Int) {
        holder.populateViews(getItem(position))
    }

    inner class BlogListViewHolder :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val blog = getItem(position)
                        blogItemClickedListener.onClicked(blogId = blog.id)
                    }
                }
            }
        }

        fun populateViews(blog: Blog) {
            binding.apply {
                blogTitle.text = blog.title
                blogBody.text = blog.body
                val dateFormat = SimpleDateFormat("MMMM, dd", Locale.getDefault())
                val date = dateFormat.parse(blog.createdDate)
                blogDate.text = date?.toString()
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Blog>() {
        override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean =
            oldItem == newItem

    }
}