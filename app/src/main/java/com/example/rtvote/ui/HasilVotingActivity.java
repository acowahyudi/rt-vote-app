package com.example.rtvote.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rtvote.R;
import com.example.rtvote.adapter.HasilAdapter;
import com.example.rtvote.adapter.KandidatAdapter;
import com.example.rtvote.service.ApiClient;
import com.example.rtvote.service.ApiInterface;
import com.example.rtvote.service.response.hasil.ResponseHasilVote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilVotingActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private HasilAdapter hasilAdapter;
    private RecyclerView rvHasilVote;
    private View infoVoteOnGoing;
    private TextView remainDay,periodeAktif;
    private Button btnBeranda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        setContentView(R.layout.activity_hasil_voting);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        infoVoteOnGoing = findViewById(R.id.info_vote_ongoing);
        btnBeranda = findViewById(R.id.btn_beranda);
        remainDay = findViewById(R.id.remain_day);
        periodeAktif = findViewById(R.id.periode_aktif);
        rvHasilVote = findViewById(R.id.rv_hasil);
        rvHasilVote.setHasFixedSize(true);
        rvHasilVote.setLayoutManager(new LinearLayoutManager(this));
        hasilAdapter = new HasilAdapter(this);

        btnBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HasilVotingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.swipe_refresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cekHasilVote();
                pullToRefresh.setRefreshing(false);
            }
        });

        cekHasilVote();
    }

    private void cekHasilVote() {
        Call<ResponseHasilVote> api = apiInterface.getHasilVote();
        api.enqueue(new Callback<ResponseHasilVote>() {
            @Override
            public void onResponse(Call<ResponseHasilVote> call, Response<ResponseHasilVote> response) {
                if (response.isSuccessful()){
                    if (response.body().isSuccess()){
                        if (response.body().getData().size()==1){
                            infoVoteOnGoing.setVisibility(View.VISIBLE);
                            rvHasilVote.setVisibility(View.GONE);
                            periodeAktif.setText("Voting untuk Periode "+response.body().getData().get(0).getPeriodeAktif()+" masih berlangsung");
                            remainDay.setText(response.body().getData().get(0).getRemainDay()+" hari lagi sebelum penutupan voting");
                        }else if (response.body().getData() != null && response.body().getData().size() > 1){
                            infoVoteOnGoing.setVisibility(View.GONE);
                            rvHasilVote.setVisibility(View.VISIBLE);
                            hasilAdapter.setKandidatList(response.body().getData());
                            rvHasilVote.setAdapter(hasilAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHasilVote> call, Throwable t) {

            }
        });
    }
}