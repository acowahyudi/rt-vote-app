package com.example.rtvote.service;

import com.example.rtvote.service.response.kandidat.ResponseKandidat;
import com.example.rtvote.service.response.loginwarga.ResponseLoginWarga;
import com.example.rtvote.service.response.vote.ResponseVote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("penduduks")
    Call<ResponseLoginWarga> getLoginWarga(
            @Query("nik_email")String nik_email
    );

    @GET("kandidats")
    Call<ResponseKandidat> getKandidat(@Query("penduduk_id")int warga);

    @FormUrlEncoded
    @POST("hasil_votings")
    Call<ResponseVote> postVote(
            @Field("penduduk_id") int warga,
            @Field("kandidat_id") int kandidat,
            @Field("periode_id") int periode
    );
//
//
//    @FormUrlEncoded
//    @POST("users")
//    Call<ResponseRegister> getRegisterApi(
//            @Field("name") String nama,
//            @Field("no_hp") String no_hp,
//            @Field("email") String email,
//            @Field("password") String password
//    );
//
//    @GET("filter")
//    Call <ResponseGetLocation> getFilterLocation(@Header("Authorization") String token,
//                                           @Query("my_lat")double lat,
//                                           @Query("my_lng")double lng,
//                                           @Query("nama_lokasi") String namaLokasi,
//                                           @Query("radius") String radius);
//
//    @GET("directions/json")
//    Call<ResponseGmaps> getDirectGmaps(@Query("origin") String origin,
//                                     @Query("destination") String dest,
//                                     @Query("key") String key);
}
