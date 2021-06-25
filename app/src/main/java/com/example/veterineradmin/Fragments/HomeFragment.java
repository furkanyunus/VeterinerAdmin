package com.example.veterineradmin.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.veterineradmin.R;
import com.example.veterineradmin.Utils.ChangeFragments;


public class HomeFragment extends Fragment {
private LinearLayout asiTakipLayout,kullanicilarLayout,soruLayout;
private View view;
private ChangeFragments changeFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        tanimla();
        clickToLayout();

        return view;
    }
    public void tanimla(){
        asiTakipLayout=(LinearLayout)view.findViewById(R.id.asiTakipLayout);
        kullanicilarLayout=(LinearLayout)view.findViewById(R.id.kullanicilarLayout);
        changeFragments=new ChangeFragments(getContext());
        soruLayout=view.findViewById(R.id.soruLayout);
    }
     public void clickToLayout(){
         asiTakipLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 changeFragments.change(new AsiTakipFragment());

             }
         });

         kullanicilarLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                changeFragments.change(new KullanicilarFragment());
             }

         });
         soruLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 changeFragments.change(new SorularFragment());
             }
         });
     }

}