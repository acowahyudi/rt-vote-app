package com.example.rtvote.service.response.vote;

import com.google.gson.annotations.SerializedName;

public class DataVote {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("periode_id")
	private int periodeId;

	@SerializedName("kandidat_id")
	private int kandidatId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("penduduk_id")
	private int pendudukId;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getPeriodeId(){
		return periodeId;
	}

	public int getKandidatId(){
		return kandidatId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public int getPendudukId(){
		return pendudukId;
	}
}