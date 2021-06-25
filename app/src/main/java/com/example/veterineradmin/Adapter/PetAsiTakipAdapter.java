package com.example.veterineradmin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineradmin.Models.AsiOnaylaModel;
import com.example.veterineradmin.Models.PetAsiTakipModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.RestApi.ManagerAll;
import com.example.veterineradmin.Utils.Warnings;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetAsiTakipAdapter  extends RecyclerView.Adapter<PetAsiTakipAdapter.ViewHolder> {

    List<PetAsiTakipModel> list;
    Context context;
    Activity activity;

    public PetAsiTakipAdapter(List<PetAsiTakipModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.asitakiplayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir
        holder.asiTakipPetName.setText(list.get(position).getPetisim());
        holder.asiTakipBilgiText.setText(list.get(position).getKadi() + " isimli kullanıcının " + list.get(position).getPetisim() + " isimli petinin "
                + list.get(position).getAsiisim() + " aşısı yapılacaktır.");

        Picasso.get().load(list.get(position).getPetresim()).resize(200,200).into(holder.AsiTakipImage);
        holder.AsiTakipOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asiOnayla(list.get(position).getAsiid().toString(),position);
            }
        });

        holder.AsiTakipCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onaylama(list.get(position).getAsiid().toString(),position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView asiTakipPetName,asiTakipBilgiText;
        ImageView AsiTakipImage,AsiTakipOkButton,AsiTakipCancelButton;
        //CardView c;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            asiTakipPetName=(TextView)itemView.findViewById(R.id.asiTakipPetName);
            asiTakipBilgiText=(TextView)itemView.findViewById(R.id.asiTakipBilgiText);
            AsiTakipImage=(ImageView) itemView.findViewById(R.id.AsiTakipImage);
            AsiTakipOkButton=(ImageView) itemView.findViewById(R.id.AsiTakipOkButton);
            AsiTakipCancelButton=(ImageView) itemView.findViewById(R.id.AsiTakipCancelButton);

        }
    }
    public void deleteToList(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void asiOnayla(String id,int position){
        Call<AsiOnaylaModel>req=ManagerAll.getInstance().asiOnayla(id);
        req.enqueue(new Callback<AsiOnaylaModel>() {
            @Override
            public void onResponse(Call<AsiOnaylaModel> call, Response<AsiOnaylaModel> response) {
                Toast.makeText(context,response.body().getText().toString(),Toast.LENGTH_LONG).show();
                deleteToList(position);
            }

            @Override
            public void onFailure(Call<AsiOnaylaModel> call, Throwable t) {
                Toast.makeText(context, Warnings.internetProblemText.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onaylama(String id,final int position){
        Call<AsiOnaylaModel>req=ManagerAll.getInstance().asiIptal(id);
        req.enqueue(new Callback<AsiOnaylaModel>() {
            @Override
            public void onResponse(Call<AsiOnaylaModel> call, Response<AsiOnaylaModel> response) {
                Toast.makeText(context,response.body().getText().toString(),Toast.LENGTH_LONG).show();
                deleteToList(position);
            }

            @Override
            public void onFailure(Call<AsiOnaylaModel> call, Throwable t) {
                Toast.makeText(context, Warnings.internetProblemText.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
