package com.sparta.academy.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {

	@JsonProperty("Course")
	private List<CourseItem> course;

	public List<CourseItem> getCourse(){
		return course;
	}
}