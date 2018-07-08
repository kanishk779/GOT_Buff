package com.example.android.got_buff.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.got_buff.ModelClasses.CitiesModelClass.AllCity;
import com.example.android.got_buff.R;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class CitiesDataAdapter extends RecyclerView.Adapter<CitiesDataAdapter.ViewHolder> {
    ArrayList<AllCity> list;
    public CitiesDataAdapter(ArrayList<AllCity> list){
        this.list = list;
    }
    @NonNull
    @Override
    public CitiesDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_episode, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesDataAdapter.ViewHolder holder, int position) {
        AllCity city = list.get(position);
        String link = city.getLink();
        String text = "<a href='" + link + "'> Get more information </a>";
        holder.name.setText(city.getName());
        holder.type.setText(city.getType());
        holder.moreInfo.setText(Html.fromHtml(text));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,moreInfo,type;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cityname);
            moreInfo = itemView.findViewById(R.id.citymoreInfo);
            type = itemView.findViewById(R.id.citytype);
        }
    }
}
