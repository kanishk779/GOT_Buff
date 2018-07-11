package com.example.android.got_buff.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.got_buff.Adapters.EventDataAdapter;
import com.example.android.got_buff.ModelClasses.EventModelClass.AllEvent;
import com.example.android.got_buff.R;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class EventFragment extends Fragment {
    private ArrayList<AllEvent> EventData;
    private RecyclerView recyclerView;
    private EventDataAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event,container,false);
        try{
            EventData = (ArrayList<AllEvent>) getArguments().getSerializable("events");
            /*Toast.makeText(getContext(), "Event Fragment\n"+EventData, Toast.LENGTH_SHORT).show();*/
        }
        catch(Exception e)
        {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recylerviewEvent);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EventDataAdapter(EventData);
        recyclerView.setAdapter(adapter);
    }
}
