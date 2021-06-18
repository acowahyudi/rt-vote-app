package com.example.rtvote.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtvote.R;
import com.example.rtvote.service.response.kandidat.DataKandidat;
import com.example.rtvote.ui.DetailKandidatActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.rtvote.service.ApiClient.URL_WEB;

public class KandidatAdapter extends RecyclerView.Adapter<KandidatAdapter.ViewHolder> {
    private List<DataKandidat> kandidatList;
    private Activity activity;

    public KandidatAdapter(Activity activity){
        this.kandidatList = new ArrayList<>();
        this.activity = activity;
    }

    public void setKandidatList(List<DataKandidat> kandidatList) {
        this.kandidatList = kandidatList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KandidatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow = LayoutInflater.from(parent.getContext()).inflate(R.layout.kandidat_item, parent, false);
        return new KandidatAdapter.ViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull KandidatAdapter.ViewHolder holder, int position) {
        holder.noUrut.setText("No Urut: 0"+kandidatList.get(position).getNoCalon());
        holder.namaCalon.setText(kandidatList.get(position).getPenduduk().getNama());
        Picasso.get()
                .load(URL_WEB + kandidatList.get(position).getFoto())
                .fit()
                .placeholder(R.drawable.ic_holder_calon)
                .error(R.drawable.ic_user)
                .into(holder.fotoCalon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailKandidatActivity.class);
                intent.putExtra("no_urut", kandidatList.get(position).getNoCalon());
                intent.putExtra("nama", kandidatList.get(position).getPenduduk().getNama());
                intent.putExtra("foto", kandidatList.get(position).getFoto());
                intent.putExtra("visi", kandidatList.get(position).getVisi());
                intent.putExtra("periode", kandidatList.get(position).getPeriode().getKeterangan());
                intent.putExtra("periode_id", kandidatList.get(position).getPeriode().getId());
                intent.putExtra("id_kandidat", kandidatList.get(position).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Math.max(kandidatList.size(), 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoCalon;
        TextView noUrut, namaCalon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoCalon = itemView.findViewById(R.id.foto_calon);
            noUrut = itemView.findViewById(R.id.no_calon);
            namaCalon = itemView.findViewById(R.id.nama_calon);
        }
    }
}
