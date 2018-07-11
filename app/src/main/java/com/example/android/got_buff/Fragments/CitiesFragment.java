package com.example.android.got_buff.Fragments;

import android.content.Intent;
import android.net.Uri;
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

import com.example.android.got_buff.Adapters.CitiesDataAdapter;
import com.example.android.got_buff.Adapters.EpisodeDataAdapter;
import com.example.android.got_buff.ModelClasses.CitiesModelClass.AllCity;
import com.example.android.got_buff.ModelClasses.EpisodeModelClass.AllEpisode;
import com.example.android.got_buff.R;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class CitiesFragment extends Fragment implements CitiesDataAdapter.MyInterface{
    private ArrayList<AllCity> CityData;
    private CitiesDataAdapter adapter;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cities,container,false);
        try{
            CityData = (ArrayList<AllCity>) getArguments().getSerializable("cities");
            /*Toast.makeText(getContext(), "Cities Fragment\n"+CityData, Toast.LENGTH_SHORT).show();*/
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
        recyclerView = view.findViewById(R.id.recylerviewCities);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CitiesDataAdapter(CityData);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick1(int position) {
        AllCity city = CityData.get(position);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(city.getLink()));
        startActivity(browserIntent);
    }
}
