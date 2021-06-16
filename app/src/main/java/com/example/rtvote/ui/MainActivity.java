package com.example.rtvote.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.rtvote.R;
import com.example.rtvote.adapter.KandidatAdapter;
import com.example.rtvote.service.ApiClient;
import com.example.rtvote.service.ApiInterface;
import com.example.rtvote.service.Const;
import com.example.rtvote.service.PrefManager;
import com.example.rtvote.service.response.kandidat.ResponseKandidat;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    KandidatAdapter kandidatAdapter;
    RecyclerView rvKandidat;
    TextView infoCalon;
    View infoSuccess, infoNoVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        infoSuccess = findViewById(R.id.info_success_vote);
        infoNoVote = findViewById(R.id.info_no_vote);

        infoCalon = findViewById(R.id.info_calon);
        rvKandidat = findViewById(R.id.rv_calon);
        rvKandidat.setHasFixedSize(true);
        rvKandidat.setLayoutManager(new GridLayoutManager(this,2));
        kandidatAdapter = new KandidatAdapter(this);

        getListKandidat();
    }

    private void getListKandidat() {
        PrefManager prf = new PrefManager(this);
        Call<ResponseKandidat> api = apiInterface.getKandidat(prf.getInt(Const.ID_USER));
        api.enqueue(new Callback<ResponseKandidat>() {
            @Override
            public void onResponse(Call<ResponseKandidat> call, Response<ResponseKandidat> response) {
                if (response.isSuccessful()){
                    if(response.body() != null && response.body().getData().size() > 0){
                        kandidatAdapter.setKandidatList(response.body().getData());
                        rvKandidat.setAdapter(kandidatAdapter);
                    }else{
                        infoSuccess.setVisibility(View.VISIBLE);
                    }
                }else if (response.code()==422){
                    infoSuccess.setVisibility(View.VISIBLE);
                    infoCalon.setVisibility(View.GONE);
                    rvKandidat.setVisibility(View.GONE);
                }else if (response.code()==404){
                    infoCalon.setVisibility(View.GONE);
                    rvKandidat.setVisibility(View.GONE);
                    infoSuccess.setVisibility(View.GONE);
                    infoNoVote.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ResponseKandidat> call, Throwable t) {
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Peringatan")
                        .setContentText("Gagal memuat daftar calon ketua RT!")
                        .show();
            }
        });
    }
}