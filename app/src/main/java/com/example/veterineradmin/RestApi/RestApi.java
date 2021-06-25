package com.example.veterineradmin.RestApi;


import com.example.veterineradmin.Models.AsiEkleModel;
import com.example.veterineradmin.Models.AsiOnaylaModel;
import com.example.veterineradmin.Models.CevaplaModel;
import com.example.veterineradmin.Models.KullaniciSilModel;
import com.example.veterineradmin.Models.KullanicilarModel;
import com.example.veterineradmin.Models.PetAsiTakipModel;
import com.example.veterineradmin.Models.PetEkleModel;
import com.example.veterineradmin.Models.PetModel;
import com.example.veterineradmin.Models.PetSilModel;
import com.example.veterineradmin.Models.SoruModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/veterinerservis/veterinerasitakip.php")
    Call<List<PetAsiTakipModel>> getPetAsiTakip(@Field("tarih") String tarih);

    @FormUrlEncoded
    @POST("/veterinerservis/asionayla.php")
    Call<AsiOnaylaModel> asiOnayla(@Field("id") String petid);

    @FormUrlEncoded
    @POST("/veterinerservis/asiiptal.php")
    Call<AsiOnaylaModel> asiIptal(@Field("id") String petid);

    @GET("/veterinerservis/sorular.php")
    Call<List<SoruModel>> getSoru();

    @FormUrlEncoded
    @POST("/veterinerservis/cevapla.php")
    Call<CevaplaModel> cevapla(@Field("musid") String musid, @Field("soruid") String soruid, @Field("text") String text);



    @GET("/veterinerservis/kullanicilar.php")
    Call<List<KullanicilarModel>> getKullanicilar();
    @FormUrlEncoded
    @POST("/veterinerservis/petlerim.php")
    Call<List<PetModel>> getPets(@Field("musid") String musid);

    @FormUrlEncoded
    @POST("/veterinerservis/petekle.php")
    Call<PetEkleModel> petEkle(@Field("musid") String musid, @Field("isim") String isim, @Field("tur") String tur, @Field("cins") String cins, @Field("resim") String resim);

    @FormUrlEncoded
    @POST("/veterinerservis/asiekle.php")
    Call<AsiEkleModel> asiEkle(@Field("musid") String musid, @Field("petid") String petid, @Field("name") String name, @Field("tarih") String tarih);


    @FormUrlEncoded
    @POST("/veterinerservis/kullanicisil.php")
    Call<KullaniciSilModel> kadiSil(@Field("id") String id);

    @FormUrlEncoded
    @POST("/veterinerservis/petsil.php")
    Call<PetSilModel> petSil(@Field("id") String id);

}
