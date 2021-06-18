package com.example.rtvote.service.response.kandidat;

import com.google.gson.annotations.SerializedName;

public class Penduduk{

	@SerializedName("nik")
	private String nik;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("email")
	private String email;

	public String getNik(){
		return nik;
	}

	public String getNama(){
		return nama;
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

	public Object getDeletedAt(){
		return deletedAt;
	}

	public String getEmail(){
		return email;
	}
}