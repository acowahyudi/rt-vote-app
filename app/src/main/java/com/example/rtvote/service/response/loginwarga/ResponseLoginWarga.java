package com.example.rtvote.service.response.loginwarga;

import com.google.gson.annotations.SerializedName;

public class ResponseLoginWarga{

	@SerializedName("data")
	private DataLoginWarga data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public DataLoginWarga getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}