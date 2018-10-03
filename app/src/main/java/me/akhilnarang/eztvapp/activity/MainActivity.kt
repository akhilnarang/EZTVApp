package me.akhilnarang.eztvapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.akhilnarang.eztvapp.R
import me.akhilnarang.eztvapp.adapter.EZTVAdapter
import me.akhilnarang.eztvapp.model.EZTVModel
import me.akhilnarang.eztvapp.model.Torrent
import me.akhilnarang.eztvapp.network.ApiClient
import me.akhilnarang.eztvapp.network.ApiInterface
import me.akhilnarang.eztvapp.utils.EndlessRecyclerOnScrollListener
import me.akhilnarang.eztvapp.utils.RecyclerItemClickListener
import retrofit2.Call
import retrofit2.Callback
import java.util.ArrayList
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private var eztvAdapter: EZTVAdapter? = null
    private var apiService: ApiInterface? = null
    private val torrentList = ArrayList<Torrent>()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onBackPressed() {
        if (!searchView.isIconified)
            searchView.isIconified = true
        else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                eztvAdapter?.filter?.filter(newText)
                return true
            }
        })
        return true
    }

    private fun initViews() {

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        eztvAdapter = EZTVAdapter(torrentList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = eztvAdapter
        apiService = ApiClient.client.create(ApiInterface::class.java)
        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                getTorrents(current_page)
            }
        })
        getTorrents(1)
        // Kotlin doesn't like one-method interfaces that directly rely on the SAM
        // constructor implementing the method. Work around this by instantiating
        // the listener separately with explicit overrides.
        val listener = object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val torrentModel = torrentList[position]
                val i = Intent(applicationContext, DetailsActivity::class.java)
                i.putExtra("torrentObject", torrentModel)
                startActivity(i)
            }
        }
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this, listener)
        )
    }

    private fun getTorrents(page: Int) {
        val call = apiService!!.getTorrents(10, page)
        call.enqueue(object : Callback<EZTVModel> {
            override fun onResponse(call: Call<EZTVModel>, response: retrofit2.Response<EZTVModel>) {
                Objects.requireNonNull<EZTVModel>(response.body(), "Response body is null")
                Objects.requireNonNull<List<Torrent>>(
                    response.body()!!.torrents,
                    "Failed to parse torrents from response body"
                )
                torrentList.addAll(response.body()!!.torrents!!)
                eztvAdapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<EZTVModel>, t: Throwable) {
            }
        })
    }
}
