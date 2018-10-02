package me.akhilnarang.eztvapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.akhilnarang.eztvapp.Adapter.EZTVAdapter;
import me.akhilnarang.eztvapp.Model.EZTVModel;
import me.akhilnarang.eztvapp.Model.Torrent;
import me.akhilnarang.eztvapp.Network.ApiClient;
import me.akhilnarang.eztvapp.Network.ApiInterface;
import me.akhilnarang.eztvapp.R;
import me.akhilnarang.eztvapp.Utils.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EZTVAdapter eztvAdapter;
    private List<Torrent> torrentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        recyclerView = findViewById(R.id.recyclerView);
        eztvAdapter = new EZTVAdapter(this, torrentList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eztvAdapter);
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<EZTVModel> call = apiService.getTorrents();
        call.enqueue(new Callback<EZTVModel>() {
            @Override
            public void onResponse(Call<EZTVModel> call, retrofit2.Response<EZTVModel> response) {
                torrentList.addAll(response.body().getTorrents());
                eztvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<EZTVModel> call, Throwable t) {

            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Torrent torrentModel = torrentList.get(position);
                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                i.putExtra("torrentObject", torrentModel);
                startActivity(i);
            }
        }));
    }

}
