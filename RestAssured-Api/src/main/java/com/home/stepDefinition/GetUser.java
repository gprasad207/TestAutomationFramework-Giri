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
import com.home.request.pojo.UsersPojo;
import com.home.request.pojo.Address;
import com.home.request.pojo.Geo;
import com.home.request.pojo.Company;

public class GetUser {

	final static Logger logger = Logger.getLogger(GetUser.class);

	private static Response response;
	ConfigFileReader configFileReader = new ConfigFileReader();
	UsersPojo usersPojo = new UsersPojo();
	Address address = new Address();
	Geo geo = new Geo();
	Company company = new Company();

	// Build the query parameter and request format

	@Given("^I prepare \"([^\"]*)\" user Payload for \"([^\"]*)\"$")
	public void i_prepare_user_Payload_for(String arg1, String arg2) throws Throwable {

		try {

			RestAssured.baseURI = configFileReader.getBaseUrl().toString();

			//logger.info("Formed BaseURI is" + RestAssured.baseURI);

			RequestSpecification request = RestAssured.given().log().all().header("contentType", "application/JSON");

			if (arg1.equalsIgnoreCase("get")) {

				response = request.get(arg2);

				logger.info("Get-Request is involked and response recieved");
			} else {

				response = request.body(usersPojo).post(arg2);

				logger.info("Post-Response is involked and response recieved");
			}

		}

		catch (Exception e) {

			Assert.assertTrue(false);
			e.printStackTrace();

		}
	}

	// Get the response status code and match

	@Then("^I check the response in the api call is success code (\\d+)$")
	public void i_check_the_response_in_the_api_call_is_success_code(int arg1) throws Throwable {

		try {
			logger.info("Response code recieved out of this involk is "+ response.getStatusCode());
			
			if (response.getStatusCode() == arg1) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	// Compare the response body

	@Then("^I check \"([^\"]*)\" in response body is \"([^\"]*)\" on record entry index- (\\d+)$")
	public void i_check_in_response_body_is_on_record_entry_index(String arg1, String arg2, int arg3) throws Throwable {

		try {

			List<String> jsonResponse = response.jsonPath().getList(arg1);

			if ((jsonResponse.get(arg3)).equalsIgnoreCase(arg2)) {

				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}

	}

	@Given("^I build request body with the following details \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void i_build_request_body_with_the_following_details(String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, String arg7, String arg8, String arg9) throws Throwable {

		try {
			// serializerUserRequest.serialUserReq(arg1, arg2, arg3, arg4, arg5, arg6, arg7,
			// arg8, arg9);
			usersPojo.setId(500);
			usersPojo.setEmail(arg6);
			usersPojo.setName(arg7);
			usersPojo.setUserName("Bret");
			usersPojo.setWebsite("wwww.google.com");
			usersPojo.setPhone(arg8);
			usersPojo.setAddress(address);
			geo.setLng("82N34");
			geo.setLat(arg9);
			address.setGeo(geo);

			company.setCatchPhrase(arg5);			
			company.setBs("bs1");
			company.setName("Ustr");
			usersPojo.setCompany(company);

			address.setCity(arg1);
			address.setStreet(arg2);
			address.setSuite(arg3);
			address.setZipcode(arg4);

			usersPojo.setAddress(address);
			
			logger.info("Generated request body is"+usersPojo);

		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}

	}
	

}
