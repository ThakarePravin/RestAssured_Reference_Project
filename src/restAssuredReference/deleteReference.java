package restAssuredReference;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class deleteReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Step 1 : Declare The Base URL
		RestAssured.baseURI="https://reqres.in";
		
		// Step 2 : Configure Request Body
		int statusCode = given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().statusCode();
		Assert.assertEquals(statusCode, 204);
		System.out.println(statusCode);
		System.out.println("Delete method validation is successful");
	}

}
