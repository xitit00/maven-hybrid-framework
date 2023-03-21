package com.nopcomerce.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.GlobalConstants;

public class UserDataMapper {
	
	@JsonProperty("firstname")
	private String firstName;
	
	@JsonProperty("lastname")
	private String lastName;
	
	@JsonProperty("emailAddress")
	private String emailAddress;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("month")
	private String month;
	
	@JsonProperty("year")
	private String year;
	
	// Multiple Level 
	@JsonProperty("login")
	private Login login;
	
	static class Login {
		
		@JsonProperty("username")
		private String username;
		
		@JsonProperty("password")
		private String password;
		
	}
	
	public String getLoginUsername() {
		return  login.username;
	}
	public String getLoginUserPassword() {
		return login.password;
	}
	
	@JsonProperty("subjects")
	private List<Subject> ArraySubject;
	
	public static class Subject {
		
		@JsonProperty("name")
		private String subjectName;
		
		@JsonProperty("point")
		private String subjectPoint;
		
		public String getSubjectName() {
			return  subjectName;
		}
		public String getSubjectPoint() {
			return subjectPoint;
		}
	}
	
	public List<Subject> getSubjects() {
		return  ArraySubject;
	}
	
	
	// hàm parse to getUserData trả về chính class UserDataMapper
	public static UserDataMapper getUserData() {
		
		try  {
			
			System.out.println("Already here!");
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new java.io.File(GlobalConstants.PROJECT_PATH + "/src/test/resources/UserData.json" ), UserDataMapper.class);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return null;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public String getDate() {
		return date;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}
	
	
	
	

}
