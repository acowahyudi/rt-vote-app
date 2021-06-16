package com.example.rtvote.service.response.kandidat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKandidat{

	@SerializedName("data")
	private List<DataKandidat> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public List<DataKandidat> getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}