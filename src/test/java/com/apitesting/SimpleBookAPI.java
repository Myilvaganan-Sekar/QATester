package com.apitesting;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.io.File;
import java.util.Arrays;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.pojo.Address;
import com.pojo.User;
import com.utils.ConfigReader;

import io.restassured.http.ContentType;

@Listeners(com.reporting.ExtentTestNGListners.class)
public class SimpleBookAPI {
	User createdUser;
	@Test(enabled = false)
	public void status() {
		given()
			.baseUri("https://simple-books-api.click/")
		.when()
			.get("status")
		.then()
			.statusCode(200)
			.body("status",equalTo("OK"));
	}

	@Test(enabled = false)
	public void listOfBooks() {
		given()
			.baseUri("https://simple-books-api.click/")
		.when()
			.get("/books?type=non-fiction&limit=3")
		.then()
			.statusCode(200)
			.body("[0].name", equalTo("Just as"));
	}

	@Test(priority = 1)
	public void postApiUser() {
		Faker fake = new Faker();	
		Address address = new Address();
		address.setCity(fake.address().cityName());
		address.setZip(fake.address().zipCode());
		
		User user=new User();
		user.setName(fake.name().fullName());
		user.setEmail(fake.internet().emailAddress());
		user.setActive(true);
		user.setAddress(address);
		user.setSkills(Arrays.asList("java","selenium"));
//		baseURI = ConfigReader.get("baseUri");
		createdUser = given()
			.baseUri(ConfigReader.get("baseUri"))
			.contentType(ContentType.JSON)
			.header(ConfigReader.get("apiKey"),ConfigReader.get("apiValue"))
			.body(user)  // convert java object to byte stream [jackson binder]
			.log()
			.all()
		.when()
			.post("api/users")
		.then()
			.statusCode(201)
			.body(matchesJsonSchemaInClasspath("Schemas/users.json"))
			.extract().as(User.class);

		System.out.println("Created user name: "+createdUser.getName());
		System.out.println("First skill: "+createdUser.getSkills().get(1));
	}
	
	
	@Test(priority = 2)
	public void getMethod() {
		User user=new User();
		String name = user.getName(); //null
		System.out.println("Created user name: "+name);
	}
	
	

}