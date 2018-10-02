package me.akhilnarang.eztvapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import me.akhilnarang.eztvapp.Model.Torrent;
import me.akhilnarang.eztvapp.R;
import me.akhilnarang.eztvapp.Utils.Tools;

/**
 * Created by men_in_black007 on 23/5/17.
 */

public class EZTVAdapter extends RecyclerView.Adapter<EZTVAdapter.ViewHolder> {

    private List<Torrent> eztvModelList;
    private Context context;

    public EZTVAdapter(Context context, List<Torrent> eztvModels) {
        this.eztvModelList = eztvModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = eztvModelList.get(position).getFilename();
        holder.filename.setText(Tools.formatName(name));
        String imgUrl = eztvModelList.get(position).getSmallScreenshot();
        if (imgUrl.isEmpty()) {
            Picasso.get().load(R.drawable.placeholder).into(holder.image);
        } else {
            Picasso.get().load(imgUrl.replace("//", "https://")).placeholder(R.drawable.placeholder).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return eztvModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView filename;
        private ImageView image;

        private ViewHolder(View view) {
            super(view);
            filename = (TextView) view.findViewById(R.id.filename);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

}
