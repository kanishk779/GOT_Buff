package com.example.android.got_buff.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.got_buff.MainActivity;
import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;
import com.example.android.got_buff.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class HistoryDataAdapter extends RecyclerView.Adapter<HistoryDataAdapter.ViewHolder> {
    ArrayList<Data> list;
    private static final String BASE_URL = "https://api.got.show";
    public HistoryDataAdapter(ArrayList<Data> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public HistoryDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryDataAdapter.ViewHolder holder, int position) {
        Data d = list.get(position);
        holder.name.setText(d.getName());
        holder.culture.setText(d.getCulture());
        holder.house.setText(d.getHouse());
        Picasso.with(holder.poster.getContext()).load(BASE_URL+d.getImageLink()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView house,culture,name;
        ImageView poster;
        public ViewHolder(View itemView) {
            super(itemView);
            house  =itemView.findViewById(R.id.Character_house);
            culture =itemView.findViewById(R.id.character_culture);
            name =itemView.findViewById(R.id.character_name);
            poster =itemView.findViewById(R.id.characterimage);
        }
    }
}
