package com.example.android.got_buff.Fragments;

import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
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

import com.example.android.got_buff.Adapters.EpisodeDataAdapter;
import com.example.android.got_buff.ModelClasses.EpisodeModelClass.AllEpisode;
import com.example.android.got_buff.R;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class EpisodeFragment extends Fragment {
    private ArrayList<AllEpisode> EpisodeData;
    private EpisodeDataAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_episodes,container,false);
        try{
            EpisodeData = (ArrayList<AllEpisode>) getArguments().getSerializable("episodes");
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
        recyclerView = view.findViewById(R.id.recylerviewEpisode);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EpisodeDataAdapter(EpisodeData);
        recyclerView.setAdapter(adapter);
    }

}
