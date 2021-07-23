package com.example.rtvote.service.response.hasil;

import com.google.gson.annotations.SerializedName;

public class DataHasilVote {

	@SerializedName("periode_id")
	private int periodeId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("users_id")
	private int pendudukId;

	@SerializedName("periode")
	private Periode periode;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user")
	private Penduduk penduduk;

	@SerializedName("vote_count_persentasi")
	private double voteCountPersentasi;

	@SerializedName("id")
	private int id;

	@SerializedName("no_calon")
	private int noCalon;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("visi")
	private String visi;

	@SerializedName("remain_day")
	private int remainDay;

	@SerializedName("periode_voting")
	private String periodeAktif;

	public int getRemainDay() {
		return remainDay;
	}

	public String getPeriodeAktif() {
		return periodeAktif;
	}

	public int getPeriodeId(){
		return periodeId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public int getPendudukId(){
		return pendudukId;
	}

	public Periode getPeriode(){
		return periode;
	}

	public String getFoto(){
		return foto;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Penduduk getPenduduk(){
		return penduduk;
	}

	public double getVoteCountPersentasi(){
		return voteCountPersentasi;
	}

	public int getId(){
		return id;
	}

	public int getNoCalon(){
		return noCalon;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public String getVisi(){
		return visi;
	}
}