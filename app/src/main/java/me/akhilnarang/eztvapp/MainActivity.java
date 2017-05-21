package me.akhilnarang.eztvapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "filename";
    private static final String EPISODE_URL = "episode_url";
    private static final String MAGENT_URL = "magnet_url";
    private static final String IMAGE_URL = "small_screenshot";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EZTVAdapter eztvAdapter;
    private List<EZTVModel> eztvModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        Button b = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String limit = editText.getText().toString();
                if (!limit.isEmpty()) {
                    try {
                        int tmp = Integer.parseInt(limit);
                        if (tmp < 1) {
                            throw new NumberFormatException(); // I mean seriously?
                        }
                        Log.v("onClick", "Limit is: " + limit);
                        doStuff(limit);
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(getApplicationContext(), "Who do you think you are, trying to " +
                                "input such invalid shiet :|", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.v("onClick", "Limit is: " + limit);
                    doStuff(limit);
                }
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        eztvAdapter = new EZTVAdapter(this, eztvModels);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eztvAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                EZTVModel eztvModel = eztvModels.get(position);
                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                i.putExtra("eztvObject", eztvModel);
                startActivity(i);
            }
        }));
    }

    private void doStuff(String limit) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = getResources().getString(R.string.url);
        String finalURL = limit.isEmpty() ? URL : URL + "?limit=" + limit;
        Log.v("doStuff", "URL is " + URL);
        Log.v("doStuff", "finaLURL is " + finalURL);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, finalURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("torrents");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String filename = Tools.formatName(jsonObject.getString(FILENAME));
                                String episodeUrl = jsonObject.getString(EPISODE_URL);
                                String magnetUrl = jsonObject.getString(MAGENT_URL);
                                String imageUrl = jsonObject.getString(IMAGE_URL);
                                if (imageUrl.isEmpty()) {
                                    eztvModels.add(new EZTVModel(filename, episodeUrl, magnetUrl));
                                } else {
                                    Log.d("EZTV", imageUrl.replace("//","https://"));
                                    eztvModels.add(new EZTVModel(filename, episodeUrl, magnetUrl, imageUrl.replace("//","https://")));
                                }
                            }
                            eztvAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error occurred while parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
