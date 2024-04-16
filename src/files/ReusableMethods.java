package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static String getJsonStringParameter(String response,String parameter) {
		JsonPath js = new JsonPath(response);
		return js.getString(parameter);
	}

}
