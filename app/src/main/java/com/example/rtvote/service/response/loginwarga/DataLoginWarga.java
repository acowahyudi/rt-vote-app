package com.example.rtvote.service.response.loginwarga;

import com.google.gson.annotations.SerializedName;

public class DataLoginWarga {

	@SerializedName("nik")
	private String nik;

	@SerializedName("nama")
	private String nama;

	@SerializedName("tempat_lahir")
	private String tempatLahir;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("agama")
	private String agama;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	public String getNik(){
		return nik;
	}

	public String getNama(){
		return nama;
	}

	public String getTempatLahir(){
		return tempatLahir;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public String getAgama(){
		return agama;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public String getTglLahir(){
		return tglLahir;
	}
}