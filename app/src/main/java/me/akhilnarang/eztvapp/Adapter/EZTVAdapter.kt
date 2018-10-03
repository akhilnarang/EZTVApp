package me.akhilnarang.eztvapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.akhilnarang.eztvapp.Model.Torrent
import me.akhilnarang.eztvapp.R
import me.akhilnarang.eztvapp.Utils.Tools

class EZTVAdapter(private val context: Context, private val eztvModelList: List<Torrent>) : RecyclerView.Adapter<EZTVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = eztvModelList[position].filename
        holder.filename.text = Tools.formatName(name)
        val imgUrl = eztvModelList[position].smallScreenshot
        if (imgUrl!!.isEmpty()) {
            Picasso.get().load(R.drawable.placeholder).into(holder.image)
        } else {
            Picasso.get().load(imgUrl.replace("//", "https://")).placeholder(R.drawable.placeholder).into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return eztvModelList.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal val filename: TextView
        internal val image: ImageView

        init {
            filename = view.findViewById<View>(R.id.filename) as TextView
            image = view.findViewById<View>(R.id.image) as ImageView
        }
    }

}
