package com.example.trabalho2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter (
    private val photos: List<Photo> ,private val callback: (Photo)->Unit
): RecyclerView.Adapter<PhotoAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent,false)

        val vh = VH(v)

        vh.itemView.setOnClickListener {
            val message = photos[vh.adapterPosition]
            callback(message)
        }

        return vh
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val photo = photos.get(position)

        holder.txtId.text = photo.id
        holder.txtTitle.text = photo.title

        Picasso.get()
            .load(photo.thumbnailUrl)
            .into(holder.image)
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtId: TextView = itemView.txt_photoId
        val txtTitle: TextView = itemView.txt_photoTitle
        val image: ImageView = itemView.imageView_photo
    }
}