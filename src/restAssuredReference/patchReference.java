package restAssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

public class patchReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Step 1 : Declare Base URL
				RestAssured.baseURI="https://reqres.in/";
				//Step 2 : Configure Request Body
				int statusCode = given().header("Content-Type","application/json").body("{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}").when().patch("/api/users/2").then().extract().statusCode();
				
				// using log all
				String responseBody=given().header("Content-Type","application/json").body("{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}").log().all().when().patch("/api/users/2").then().log().all().extract().response().asString();
				
				System.out.println(statusCode);
				System.out.println(responseBody); 
				
				// Step 3 : Parse the Response Body
						JsonPath jsp = new JsonPath(responseBody);
						String res_name = jsp.getString("name");
						String res_job = jsp.getString("job");
					    //String res_id = jsp.getString("id");
						String res_updatedAt = jsp.getString("updatedAt");
						res_updatedAt = res_updatedAt.substring(0,11);
						
						// Generate date in format as received in response
						LocalDateTime Date = LocalDateTime.now();
						String expectedDate = Date.toString().substring(0,11);
						
						
				// Step 4 : Validate The Response Body Parameters
						Assert.assertEquals(statusCode, 200);
						Assert.assertEquals(res_name, "morpheus");
						Assert.assertEquals(res_job, "zion resident");
						Assert.assertEquals(res_updatedAt,expectedDate);
						
						System.out.println("Updated date" + res_updatedAt);
						System.out.println("Expected date" + expectedDate);
						System.out.println("Patch Method Validation is Successful");
	}

}
