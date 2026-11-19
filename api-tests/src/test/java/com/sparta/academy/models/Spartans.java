package com.sparta.academy.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Spartans{

	@JsonProperty("Spartans")
	private List<SpartansItem> spartans;

	public List<SpartansItem> getSpartans(){
		return spartans;
	}
}