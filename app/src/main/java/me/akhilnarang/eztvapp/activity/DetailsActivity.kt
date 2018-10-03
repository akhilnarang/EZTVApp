package me.akhilnarang.eztvapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

import androidx.appcompat.app.AppCompatActivity
import me.akhilnarang.eztvapp.model.Torrent
import me.akhilnarang.eztvapp.R

import java.util.Objects

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val i = intent
        val torrentModel = i.getSerializableExtra("torrentObject") as Torrent

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(torrentModel.magnetUrl), "application")
            startActivity(Intent.createChooser(intent, "Choose torrent app"))
        }

        val torrentTitle = findViewById<TextView>(R.id.torrentTitle)
        torrentTitle.text = torrentModel.filename

        val torrentImage = findViewById<ImageView>(R.id.torrentImage)
        val imgurl = torrentModel.largeScreenshot
        Objects.requireNonNull<String>(imgurl, "No torrent image could be parsed")
        if (imgurl!!.isEmpty()) {
            Picasso.get().load(R.drawable.placeholder).into(torrentImage)
        } else {
            Picasso.get().load(imgurl.replace("//", "https://")).placeholder(R.drawable.placeholder).into(torrentImage)
        }
    }
}
