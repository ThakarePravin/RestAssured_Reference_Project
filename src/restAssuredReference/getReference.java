package restAssuredReference;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.*;

public class getReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Step 1 : Declare Base URL
		RestAssured.baseURI = "https://reqres.in/";
		
		// Step 2 : Configure Response Body
		int statusCode = given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().statusCode();
		System.out.println("Status Code :" + statusCode);
		
		String responseBody = given().header("Content-Type","application.json").when().get("/api/users?page=2").then().extract().response().asString();
		System.out.println("Response Body :" + responseBody);
		
		JsonPath jsp = new JsonPath(responseBody);
		int dataSize = jsp.getList("data").size();
		
		// Assert the total count of objects inside the data array
		Assert.assertEquals(dataSize,6);
		
		// Validate each object in data array
		for (int i = 0 ; i < dataSize ; i++) {
			
			String id = jsp.getString("data[" + i + "].id");
			String email = jsp.getString("data[" + i + "].email");
			String fname = jsp.getString("data[" + i + "].first_name");
			String lname = jsp.getString("data[" + i + "].last_name");
			String avatar = jsp.getString("data[" + i + "].avatar");
			
			Assert.assertNotNull(id);
			Assert.assertNotNull(email);
			Assert.assertNotNull(fname);
			Assert.assertNotNull(lname);
			Assert.assertNotNull(avatar);
			
			Assert.assertTrue(Integer.parseInt(id) >= 7 && Integer.parseInt(id) <= 12);
			Assert.assertTrue(email.contains("@reqres.in"));			
		}
		System.out.println("POST method successfully validate");	
	}

}
