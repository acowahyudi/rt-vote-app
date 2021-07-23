package com.example.rtvote.service.response.kandidat;

import com.google.gson.annotations.SerializedName;

public class DataKandidat {

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user")
	private Penduduk penduduk;

	@SerializedName("periode_id")
	private int periodeId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("no_calon")
	private int noCalon;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("visi")
	private String visi;

	@SerializedName("penduduk_id")
	private int pendudukId;

	@SerializedName("periode")
	private Periode periode;

	public String getFoto(){
		return foto;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Penduduk getPenduduk(){
		return penduduk;
	}

	public int getPeriodeId(){
		return periodeId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public int getNoCalon(){
		return noCalon;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public String getVisi(){
		return visi;
	}

	public int getPendudukId(){
		return pendudukId;
	}

	public Periode getPeriode(){
		return periode;
	}
}