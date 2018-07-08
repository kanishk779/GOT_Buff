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

import com.example.android.got_buff.Adapters.EventDataAdapter;
import com.example.android.got_buff.Adapters.HistoryDataAdapter;
import com.example.android.got_buff.DataBases.CharacterHistoryDatabase;
import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;
import com.example.android.got_buff.R;

import java.util.ArrayList;

/**
 * Created by hp on 02-07-2018.
 */

public class HistoryFragment extends Fragment {
    CharacterHistoryDatabase db;
    ArrayList<Data> list;
    RecyclerView recyclerView;
    HistoryDataAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerviewHistory);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HistoryDataAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new CharacterHistoryDatabase(getContext());
        db.openRead();
        list = db.readAll();
        db.closeRead();
    }
}
