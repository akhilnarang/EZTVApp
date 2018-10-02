package me.akhilnarang.eztvapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        final EZTVModel eztvModel = (EZTVModel) i.getSerializableExtra("eztvObject");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setType("application");
                i.setData(Uri.parse(eztvModel.getMagnetUrl()));
                startActivity(Intent.createChooser(i, "Choose torrent app"));

            }
        });

        TextView torrentTitle = (TextView) findViewById(R.id.torrentTitle);
        torrentTitle.setText(eztvModel.getFilename());

        ImageView torrentImage = (ImageView) findViewById(R.id.torrentImage);
        Picasso.get().load(eztvModel.getImageUrl()).placeholder(R.drawable.placeholder).into(torrentImage);
    }
}
