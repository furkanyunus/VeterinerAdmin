package com.example.veterineradmin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineradmin.Fragments.KullaniciPetlerFragment;
import com.example.veterineradmin.Models.AsiOnaylaModel;
import com.example.veterineradmin.Models.KullanicilarModel;
import com.example.veterineradmin.Models.PetAsiTakipModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.RestApi.ManagerAll;
import com.example.veterineradmin.Utils.ChangeFragments;
import com.example.veterineradmin.Utils.Warnings;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<KullanicilarModel> list;
    Context context;
    Activity activity;
    ChangeFragments changeFragments ;
    public UserAdapter(List<KullanicilarModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
        changeFragments = new ChangeFragments(context);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.kullaniciitemlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.kullaniciNameText.setText(list.get(position).getKadi().toString());

        holder.userPetlerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeFragments.changeWithParameters(new KullaniciPetlerFragment(), list.get(position).getId().toString());


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView kullaniciNameText;
        Button userPetlerButon;
        CardView usercardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kullaniciNameText=(TextView)itemView.findViewById(R.id.kullaniciNameText);
            userPetlerButon=(Button) itemView.findViewById(R.id.userPetlerButon);
            usercardView=(CardView) itemView.findViewById(R.id.usercardView);

        }
    }



}
