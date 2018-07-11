package com.example.android.got_buff.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.got_buff.ModelClasses.EventModelClass.AllEvent;
import com.example.android.got_buff.R;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class EventDataAdapter extends RecyclerView.Adapter<EventDataAdapter.ViewHolder> {
    ArrayList<AllEvent> list;
    public EventDataAdapter(ArrayList<AllEvent> list){
        this.list = list;
    }
    @NonNull
    @Override
    public EventDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventDataAdapter.ViewHolder holder, int position) {
        AllEvent event = list.get(position);
        holder.name.setText(event.getName());
        holder.age.setText(event.getAge());
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,age;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventName);
            age = itemView.findViewById(R.id.eventAge);
        }
    }
}
