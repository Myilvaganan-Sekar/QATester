package com.apitesting;

import com.github.javafaker.Faker;
import com.pojo.ClientRequest;
import com.pojo.ClientResponse;
import com.pojo.SubmitOrderRequest;
import com.pojo.SubmitOrderResponse;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.reporting.ExtentTestNGListners.class)
public class SubmitAnOrder {
  public String accessToken;
  public String orderId;
  public Faker fake = new Faker();
  
  @Test(priority = 1)
  public void aPIAuthentication() {

	  ClientRequest client = new ClientRequest(fake.name().fullName(),fake.internet().emailAddress());
	  
	  ClientResponse responseObj = given()
	  	.baseUri("https://simple-books-api.click")
	  	.contentType(ContentType.JSON)
	  	.body(client)
	  	.log().all()
	  .when()
	  	.post("/api-clients/")
	  .then()
	  	.log().all()
	  	.statusCode(201)
	  	.body("accessToken", notNullValue())
	  	.extract()
	  	.as(ClientResponse.class);
	  
	  accessToken = responseObj.getAccessToken();
	  System.out.println("Access Token Generated: "+accessToken);
  }
	
  
  	@Test(priority = 2, dependsOnMethods = {"aPIAuthentication"})
	public void submitAnOrder() {		
	SubmitOrderRequest order = new SubmitOrderRequest(5,fake.name().fullName());
		SubmitOrderResponse orderResponse = given()
			.baseUri("https://simple-books-api.click")
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer "+accessToken)
			.body(order)
			.log().all()
		.when()
			.post("/orders")
			.then()
			.log().all()
			.statusCode(201)
			.body("created", equalTo(true))
			.extract()
			.as(SubmitOrderResponse.class);
		
		orderId = orderResponse.getOrderId();
		System.out.println("Order ID Stored: "+orderId);
	}
}
