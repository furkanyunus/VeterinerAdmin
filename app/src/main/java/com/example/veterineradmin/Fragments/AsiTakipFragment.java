package com.example.veterineradmin.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import com.example.veterineradmin.Adapter.PetAsiTakipAdapter;
import com.example.veterineradmin.Models.PetAsiTakipModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.RestApi.ManagerAll;
import com.example.veterineradmin.Utils.ChangeFragments;
import com.example.veterineradmin.Utils.Warnings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsiTakipFragment extends Fragment {
    private View view;
    private DateFormat format;
    private Date date;
    private  String today;
    private ChangeFragments changeFragments;
    private RecyclerView asiTakipRecyclerView;
    private List<PetAsiTakipModel>list;
    private PetAsiTakipAdapter petAsiTakipAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_asi_takip, container, false);
        Define();
        istekAt(today);
        return view;
    }
    public void Define(){
        format=new SimpleDateFormat("dd/MM/yyyy");
        date= Calendar.getInstance().getTime();

        today=format.format(date);
        changeFragments=new ChangeFragments(getContext());
        asiTakipRecyclerView=(RecyclerView)view.findViewById(R.id.asiTakipRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        asiTakipRecyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
    }

    public void istekAt(String tarih) {
        Call<List<PetAsiTakipModel>> req = ManagerAll.getInstance().getAsiPet(tarih);
        req.enqueue(new Callback<List<PetAsiTakipModel>>() {
            @Override
            public void onResponse(Call<List<PetAsiTakipModel>> call, Response<List<PetAsiTakipModel>> response) {
                if (response.body().get(0).isTf()) {
                    Toast.makeText(getContext(), "Bugün " + response.body().size() + " pete aşı yapılacaktır.", Toast.LENGTH_LONG).show();
                   list = response.body();
                  petAsiTakipAdapter = new PetAsiTakipAdapter(list, getContext(), getActivity());
                   asiTakipRecyclerView.setAdapter(petAsiTakipAdapter);
                } else {
                    Toast.makeText(getContext(), "Bugün aşı yapılacak pet yoktur.", Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<PetAsiTakipModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
            }
        });

    }

}