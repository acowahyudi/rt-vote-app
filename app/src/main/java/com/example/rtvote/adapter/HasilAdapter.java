package com.example.rtvote.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtvote.R;
import com.example.rtvote.service.response.hasil.DataHasilVote;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.rtvote.service.ApiClient.URL_WEB;

public class HasilAdapter extends RecyclerView.Adapter<HasilAdapter.ViewHolder> {
    private List<DataHasilVote> hasilVoteList;
    private Activity activity;

    public HasilAdapter(Activity activity){
        this.hasilVoteList = new ArrayList<>();
        this.activity = activity;
    }

    public void setKandidatList(List<DataHasilVote> kandidatList) {
        this.hasilVoteList = kandidatList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HasilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow = LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil_item, parent, false);
        return new HasilAdapter.ViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilAdapter.ViewHolder holder, int position) {
        holder.noUrut.setText("0"+hasilVoteList.get(position).getNoCalon());
        holder.namaCalon.setText(hasilVoteList.get(position).getPenduduk().getNama());
        holder.periode.setText("Periode : "+hasilVoteList.get(position).getPeriode().getKeterangan());
        holder.jumlahVote.setText("Jumlah Vote : "+hasilVoteList.get(position).getVoteCount());
        holder.presentasi.setText("Presentasi Vote : "+hasilVoteList.get(position).getVoteCountPersentasi()+"%");
        holder.presentasiVote.setProgress((int) hasilVoteList.get(position).getVoteCountPersentasi());

        Picasso.get()
                .load(URL_WEB + hasilVoteList.get(position).getFoto())
                .fit()
                .placeholder(R.drawable.ic_holder_calon)
                .error(R.drawable.ic_user)
                .into(holder.fotoCalon);
    }

    @Override
    public int getItemCount() {
        return Math.max(hasilVoteList.size(), 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoCalon;
        TextView noUrut, namaCalon, periode, jumlahVote, presentasi;
        ProgressBar presentasiVote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoCalon = itemView.findViewById(R.id.foto_calon);
            noUrut = itemView.findViewById(R.id.no_calon);
            namaCalon = itemView.findViewById(R.id.nama_calon);
            periode = itemView.findViewById(R.id.periode);
            jumlahVote = itemView.findViewById(R.id.jumlah_vote);
            presentasi = itemView.findViewById(R.id.presentase_vote);
            presentasiVote = itemView.findViewById(R.id.percent_bar);
        }
    }
}
