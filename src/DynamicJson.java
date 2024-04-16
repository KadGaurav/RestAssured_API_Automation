import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;

public class DynamicJson {

	@Test(dataProvider = "booksData")
	public void addBook(String isbn ,String aisle) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String addNewBook =
		given().header("Content-Type","application/json")				//Use .log().all() to see added json file
		.body(payload.addBook(isbn , aisle))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();		//Use .log().all() to see all response
		
		String idOfBook = ReusableMethods.getJsonStringParameter(addNewBook, "ID");
		System.out.println("Id : " + idOfBook);
		
		String message = ReusableMethods.getJsonStringParameter(addNewBook, "Msg");
		System.out.println("Message : " + message);
	}
	
	//Data Parameterization
	@DataProvider(name="booksData")
	public Object[][] getData() {
		return new Object[][] {{"aa","11"},{"bb","22"},{"cc","33"}};
		
	}
	
}
