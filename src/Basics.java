import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;
public class Basics {

	public static void main(String[] args) {

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = 
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		//after assertThat method validation starts(Assertion)
		.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		//extract place ID from response generated

		String placeId = ReusableMethods.getJsonStringParameter(response, "place_id");
		
		System.out.println("Place Id is ---------->" + placeId);
		
		//-------->Update Place using placeId
		// PUT Method
		String newAddress = "Manas Society";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.updatePlace(placeId,newAddress))
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//----------->Get place details using place id
		
		String getNewAdress =
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();

		String actualAddress = ReusableMethods.getJsonStringParameter(getNewAdress, "address");
		System.out.println("New Address is ---------->" + actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
		
		
	}

}
