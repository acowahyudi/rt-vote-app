package com.example.rtvote.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtvote.R;
import com.example.rtvote.adapter.KandidatAdapter;
import com.example.rtvote.service.ApiClient;
import com.example.rtvote.service.ApiInterface;
import com.example.rtvote.service.Const;
import com.example.rtvote.service.PrefManager;
import com.example.rtvote.service.response.kandidat.ResponseKandidat;
import com.example.rtvote.service.response.vote.ResponseVote;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private KandidatAdapter kandidatAdapter;
    private RecyclerView rvKandidat;
    private TextView infoCalon;
    private View infoSuccess, infoNoVote, rlCalon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        PrefManager prf = new PrefManager(this);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Welcome, "+prf.getString(Const.NAMA));
        infoSuccess = findViewById(R.id.info_success_vote);
        infoNoVote = findViewById(R.id.info_no_vote);
        infoCalon = findViewById(R.id.info_calon);
        rlCalon = findViewById(R.id.rl_main);
        rvKandidat = findViewById(R.id.rv_calon);
        rvKandidat.setHasFixedSize(true);
        rvKandidat.setLayoutManager(new GridLayoutManager(this,2));
        kandidatAdapter = new KandidatAdapter(this);

        Button cekHasil = findViewById(R.id.cek_hasil);
        cekHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HasilVotingActivity.class);
                startActivity(intent);
            }
        });

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.swipe_refresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListKandidat();
                pullToRefresh.setRefreshing(false);
            }
        });

        getListKandidat();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log_out) {
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("PERHATIAN !")
                    .setContentText("Apakah anda yakin ingin log out?")
                    .setConfirmText("Ya")
                    .setCancelText("Batal")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            PrefManager prefManager = new PrefManager(MainActivity.this);
                            prefManager.remove(Const.ID_USER);
                            prefManager.remove(Const.NIK);
                            prefManager.remove(Const.NAMA);
                            prefManager.remove(Const.EMAIL);
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getListKandidat() {
        PrefManager prf = new PrefManager(this);
        Call<ResponseKandidat> api = apiInterface.getKandidat(prf.getInt(Const.ID_USER));
        api.enqueue(new Callback<ResponseKandidat>() {
            @Override
            public void onResponse(Call<ResponseKandidat> call, Response<ResponseKandidat> response) {
                if (response.isSuccessful()){
                    if(response.body() != null && response.body().getData().size() > 0){
                        rlCalon.setVisibility(View.VISIBLE);
                        infoCalon.setVisibility(View.VISIBLE);
                        infoSuccess.setVisibility(View.GONE);
                        infoNoVote.setVisibility(View.GONE);

                        kandidatAdapter.setKandidatList(response.body().getData());
                        rvKandidat.setAdapter(kandidatAdapter);
                    }else{
                        infoSuccess.setVisibility(View.VISIBLE);
                    }
                }else if (response.code()==422){
                    rlCalon.setVisibility(View.GONE);
                    infoCalon.setVisibility(View.GONE);
                    infoNoVote.setVisibility(View.GONE);

                    infoSuccess.setVisibility(View.VISIBLE);
                }else if (response.code()==404){
                    infoCalon.setVisibility(View.GONE);
                    rlCalon.setVisibility(View.GONE);
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