package com.home.stepDefinition;

import java.util.List;

import org.junit.Assert;
import com.home.utility.ConfigFileReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

public class GetUser {

	private static Response response;

	final static Logger logger = Logger.getLogger(GetUser.class);

	@Given("^I prepare user Payload$")
	public void i_prepare_user_Payload() throws Throwable {

		try {
					
		//	RestAssured.baseURI = (ConfigFileReader.getBaseUrl()).toString();
			RestAssured.baseURI= "https://jsonplaceholder.typicode.com/";
			
			RequestSpecification request = RestAssured.given().log().all().header("contentType", "application/JSON");
			response = request.get("/users");

			Assert.assertTrue(true);

		}

		catch (Exception e) {

			Assert.assertTrue(false);
			e.printStackTrace();

		}
	}

	@Then("^I check the response in the api call is success code (\\d+)$")
	public void i_check_the_response_in_the_api_call_is_success_code(int arg1) throws Throwable {

		System.out.println("--------->" + response.getStatusCode());

		try {
			if (response.getStatusCode() == arg1) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Then("^I check \"([^\"]*)\" in response body is \"([^\"]*)\" on record entry index- (\\d+)$")
	public void i_check_in_response_body_is_on_record_entry_index(String arg1, String arg2, int arg3) throws Throwable {

		try {

			List<String> jsonResponse = response.jsonPath().getList(arg1);
			// System.out.println("++++++++++>"+jsonResponse.get(0));

			if ((jsonResponse.get(arg3).toString()).equalsIgnoreCase(arg2)) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
