package com.example.rtvote.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rtvote.R;
import com.example.rtvote.service.ApiClient;
import com.example.rtvote.service.ApiInterface;
import com.example.rtvote.service.Const;
import com.example.rtvote.service.PrefManager;
import com.example.rtvote.service.response.vote.ResponseVote;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.rtvote.service.ApiClient.URL_WEB;

public class DetailKandidatActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    TextView noUrut, namaCalon, periode, visi;
    ImageView fotoCalon;
    Button back, vote;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kandidat);
        Bundle extras = getIntent().getExtras();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        noUrut = findViewById(R.id.no_calon);
        namaCalon = findViewById(R.id.nama_calon);
        periode = findViewById(R.id.periode);
        visi = findViewById(R.id.visi);
        fotoCalon = findViewById(R.id.foto_calon);
        back = findViewById(R.id.kembali);
        vote = findViewById(R.id.vote);

        periode.setText("Periode: "+extras.getString("periode"));
        noUrut.setText(extras.getString("no_urut"));
        namaCalon.setText(extras.getString("nama"));
        visi.setText(extras.getString("visi"));
        Picasso.get()
                .load(URL_WEB + extras.getString("foto"))
                .fit()
                .placeholder(R.drawable.ic_holder_calon)
                .error(R.drawable.ic_user)
                .into(fotoCalon);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(DetailKandidatActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("PERHATIAN !")
                        .setContentText("Apakah anda yakin ingin vote kandidat ini?")
                        .setConfirmText("Ya")
                        .setCancelText("Batal")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                PrefManager prefManager = new PrefManager(DetailKandidatActivity.this);
                                int periode = extras.getInt("periode");
                                int warga = prefManager.getInt(Const.ID_USER);
                                int kandidat = extras.getInt("id_kandidat");

                                Call<ResponseVote> api = apiInterface.postVote(warga,kandidat,periode);
                                api.enqueue(new Callback<ResponseVote>() {
                                    @Override
                                    public void onResponse(Call<ResponseVote> call, Response<ResponseVote> response) {
                                        if (response.isSuccessful()){
                                            new SweetAlertDialog(DetailKandidatActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                    .setTitleText("SUKSES")
                                                    .setContentText("Voting kandidat berhasil")
                                                    .setConfirmText("Ok")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sDialog) {
                                                            sDialog.dismissWithAnimation();
                                                            Intent intent = new Intent(DetailKandidatActivity.this, MainActivity.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    })
                                                    .show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseVote> call, Throwable t) {
                                        new SweetAlertDialog(DetailKandidatActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Peringatan")
                                                .setContentText("Gagal vote kandidat!")
                                                .show();
                                    }
                                });
                            }
                        })
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });
    }
}