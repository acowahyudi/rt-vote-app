package com.example.rtvote.service.response.kandidat;

import com.google.gson.annotations.SerializedName;

public class DataKandidat {

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

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

	@SerializedName("periode")
	private Periode periode;

	public String getNama(){
		return nama;
	}

	public String getFoto(){
		return foto;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public Periode getPeriode(){
		return periode;
	}
}