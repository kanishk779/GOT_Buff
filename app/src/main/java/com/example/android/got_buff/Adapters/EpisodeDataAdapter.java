package com.example.android.got_buff.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.got_buff.ModelClasses.EpisodeModelClass.AllEpisode;
import com.example.android.got_buff.R;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class EpisodeDataAdapter extends RecyclerView.Adapter<EpisodeDataAdapter.ViewHolder> {
    ArrayList<AllEpisode> list;

    public EpisodeDataAdapter(ArrayList<AllEpisode> list){
        this.list = list;
    }

    @NonNull
    @Override
    public EpisodeDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_episode, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeDataAdapter.ViewHolder holder, int position) {
        AllEpisode episode = list.get(position);
        holder.director.setText(episode.getDirector());
        holder.airdate.setText(episode.getAirDate());
        holder.season.setText(String.valueOf(episode.getSeason()));
        holder.name.setText(episode.getName());
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView director,season,name,airdate;

        public ViewHolder(View itemView) {
            super(itemView);
            director = itemView.findViewById(R.id.episodeDirector);
            season = itemView.findViewById(R.id.episodeSeason);
            name = itemView.findViewById(R.id.episodename);
            airdate = itemView.findViewById(R.id.episodeAirDate);
        }
    }
}
