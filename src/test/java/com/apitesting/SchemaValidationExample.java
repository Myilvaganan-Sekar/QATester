package com.apitesting;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.reporting.ExtentTestNGListners.class)
public class SchemaValidationExample {
	@Test
	public void listOfUsers() {
		given()
		.baseUri("https://reqres.in/api/")
	.when()
		.get("users?page=2")
	.then()
		.statusCode(200)
		.body(matchesJsonSchemaInClasspath("Schemas/ListofUsers.json"));
	}
}
