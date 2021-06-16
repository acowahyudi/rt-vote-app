package com.example.rtvote.service.response.kandidat;

import com.google.gson.annotations.SerializedName;

public class Periode{

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("mulai_vote")
	private String mulaiVote;

	@SerializedName("selesai_vote")
	private String selesaiVote;

	@SerializedName("deleted_at")
	private Object deletedAt;

	public String getKeterangan(){
		return keterangan;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getMulaiVote(){
		return mulaiVote;
	}

	public String getSelesaiVote(){
		return selesaiVote;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}
}