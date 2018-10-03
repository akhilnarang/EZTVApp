package me.akhilnarang.eztvapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.akhilnarang.eztvapp.R
import me.akhilnarang.eztvapp.model.Torrent
import me.akhilnarang.eztvapp.utils.Tools

open class EZTVAdapter(private var eztvModelList: List<Torrent>) : RecyclerView.Adapter<EZTVAdapter.ViewHolder>(),
    Filterable {
    private val originalEztvModelList: List<Torrent> = eztvModelList

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): Filter.FilterResults {
                val filteredResults: List<Torrent> = if (constraint.isEmpty()) {
                    originalEztvModelList
                } else {
                    getFilteredResults(constraint.toString().toLowerCase())
                }

                val results = Filter.FilterResults()
                results.values = filteredResults
                return results
            }

            override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
                eztvModelList = results.values as List<Torrent>
                notifyDataSetChanged()
            }
        }
    }

    protected fun getFilteredResults(constraint: String): List<Torrent> {
        val results = ArrayList<Torrent>()

        for (item in originalEztvModelList) {
            if (item.filename!!.toLowerCase().contains(constraint)) {
                results.add(item)
            }
        }

        return results
    }

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

        internal val filename: TextView = view.findViewById<View>(R.id.filename) as TextView
        internal val image: ImageView = view.findViewById<View>(R.id.image) as ImageView
    }
}
