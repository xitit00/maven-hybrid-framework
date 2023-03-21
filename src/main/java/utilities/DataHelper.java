package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	
	public String firstName;
	public String lastName;
	public String fullName;
	public String address;
	public String email;
	public String phone;
	public String city;
	public String cityName;
	public String password;
	
	
	public Locale locale = new Locale("en");
	public Faker faker = new Faker();
	
	public static DataHelper getDataHelper() {
		
		return new DataHelper();
	}
	
	public String getFirstName() {
		return faker.name().firstName();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return faker.name().fullName();
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return faker.address().streetAddress();
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return faker.internet().password(8, 16, true, true);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return faker.phoneNumber().phoneNumber();
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return faker.address().city();
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityName() {
		return faker.address().cityName();
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
