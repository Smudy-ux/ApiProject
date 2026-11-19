package com.sparta.academy.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpartansItem {

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("stream")
	private String stream;

	@JsonProperty("university")
	private String university;

	@JsonProperty("graduated")
	private boolean graduated;

	@JsonProperty("degree")
	private String degree;

	@JsonProperty("course")
	private String course;

	@JsonProperty("courseId")
	private Integer courseId;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private Integer id;

	public SpartansItem() {
	}

	public SpartansItem(String firstName, String lastName, String university, String degree, Integer courseId, boolean graduated) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.university = university;
		this.degree = degree;
		this.courseId = courseId;
		this.graduated = graduated;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStream() {
		return stream;
	}

	public String getUniversity() {
		return university;
	}

	public boolean isGraduated() {
		return graduated;
	}

	public String getDegree() {
		return degree;
	}

	public String getCourse() {
		return course;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public List<LinksItem> getLinks() {
		return links;
	}

	public Integer getId() {
		return id;
	}
}