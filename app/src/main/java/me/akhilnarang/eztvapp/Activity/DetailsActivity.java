package me.akhilnarang.eztvapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import me.akhilnarang.eztvapp.Model.Torrent;
import me.akhilnarang.eztvapp.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        final Torrent torrentModel = (Torrent) i.getSerializableExtra("torrentObject");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setType("application");
                i.setData(Uri.parse(torrentModel.getMagnetUrl()));
                startActivity(Intent.createChooser(i, "Choose torrent app"));
            }
        });

        TextView torrentTitle = findViewById(R.id.torrentTitle);
        torrentTitle.setText(torrentModel.getFilename());

        ImageView torrentImage = findViewById(R.id.torrentImage);
        String imgurl = torrentModel.getLargeScreenshot();
        if (imgurl.isEmpty()) {
            Picasso.get().load(R.drawable.placeholder).into(torrentImage);
        } else {
            Picasso.get().load(imgurl.replace("//", "https://")).placeholder(R.drawable.placeholder).into(torrentImage);
        }
    }
}
