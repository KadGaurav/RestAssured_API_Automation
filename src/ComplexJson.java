import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
	public static void main(String[] args) {
		
	JsonPath js =new JsonPath(payload.complexJson());
	
	//1 getSize of courses
	System.out.println(js.getInt("courses.size()"));
	
	//2 Print purchase Amount
	System.out.println(js.getString("dashboard.purchaseAmount"));
	
	//3 Print title of first Course
	System.out.println(js.getString("courses.title[0]"));
	
	//4 Print All course titles and their respective Prices
	System.out.println(js.getString("courses.title"));
	
	for(int i = 0 ; i<js.getInt("courses.title.size()");i++) {
		System.out.println(js.getString("courses.title["+i+"]"));		
	}
	
	//5 Print no of copies sold by RPA Course
	for(int i = 0 ; i<js.getInt("courses.title.size()");i++) {
		String courseTitle =js.getString("courses.title["+i+"]");
		
		if(courseTitle.equalsIgnoreCase("RPA")) {
			System.out.println("No of Copies Sold by RPA ----> "+ js.getString("courses.copies["+i+"]"));
			break;
		};	
	}
	
	//6 Verify if Sum of all Course prices matches with Purchase Amount
	int purchaseAmount = js.getInt("dashboard.purchaseAmount");
	int sumOfCourses = 0 ;
	for(int i = 0 ; i<js.getInt("courses.size()");i++) {
		int price = js.getInt("courses["+i+"].price");
		int copies = js.getInt("courses["+i+"].copies");
		
		int multiply= price*copies;
		sumOfCourses += multiply; 
	}
	
	if(purchaseAmount==sumOfCourses) {
		System.out.println("Purchase Amount is ="+purchaseAmount +"\nSum of All Courses is = "+sumOfCourses);
		System.out.println("Purchase Amount is equal to Sum of All courses");
	}
	
	
	
	}
}
