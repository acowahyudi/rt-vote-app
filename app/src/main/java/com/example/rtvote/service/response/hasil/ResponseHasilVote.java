package com.example.rtvote.service.response.hasil;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseHasilVote{

	@SerializedName("data")
	private List<DataHasilVote> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public List<DataHasilVote> getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}