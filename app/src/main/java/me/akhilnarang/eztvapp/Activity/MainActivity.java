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
import me.akhilnarang.eztvapp.Utils.EndlessRecyclerOnScrollListener;
import me.akhilnarang.eztvapp.Utils.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EZTVAdapter eztvAdapter;
    private ApiInterface apiService;
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
        apiService = ApiClient.getClient().create(ApiInterface.class);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                getTorrents(current_page);
            }
        });
        getTorrents(1);
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

    private void getTorrents(int page) {
        Call<EZTVModel> call = apiService.getTorrents(10, page);
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
    }

}
