package restAssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;


public class postReference {

	public static void main(String[] args) {
		//Step 1 : Declare Base URL
		RestAssured.baseURI="https://reqres.in/";
		//Step 2 : Configure Request Body
		int statusCode = given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").when().post("/api/users").then().extract().statusCode();
		
		// using log all
		String responseBody=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").log().all().when().post("/api/users").then().log().all().extract().response().asString();
		
		// without log all
	/*	String responseBody=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").when().post("/api/users").then().extract().response().asString(); */
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		
		// Step 3 : Parse the Response Body
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");
		res_createdAt = res_createdAt.substring(0,11);
		
		// Generate date in format as received in response
		LocalDateTime Date = LocalDateTime.now();
		String expectedDate = Date.toString().substring(0,11);
						
		// Step 4 : Validate The Response Body Parameters
		Assert.assertEquals(statusCode, 201);
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt,expectedDate);
		
		System.out.println("ResponseDate" + res_createdAt);
		System.out.println("Exp_date" + expectedDate);
		
	}

}
