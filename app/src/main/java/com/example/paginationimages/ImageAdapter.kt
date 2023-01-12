package com.example.paginationimages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.paginationimages.databinding.ItemImageBinding

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val list = arrayListOf<ImageModel>()

    class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ImageModel){
            binding.imageView.load(item.largeImageURL)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addImage(list : List<ImageModel>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size
}