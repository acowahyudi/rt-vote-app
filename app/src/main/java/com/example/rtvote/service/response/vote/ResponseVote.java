package com.example.rtvote.service.response.vote;

import com.google.gson.annotations.SerializedName;

public class ResponseVote{

	@SerializedName("data")
	private DataVote data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public DataVote getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}