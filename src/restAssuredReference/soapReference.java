package restAssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.xml.*;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
public class soapReference {

	public static void main(String[] args) {
		//Step 1 : Declare Base URI and Request Body Variables
		String BaseURI = "https://www.dataaccess.com";
		String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>240</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";		
		
		//Step 2 : Set Expected Results
		XmlPath xmlRequest = new XmlPath(RequestBody);
		String req_parameter = xmlRequest.getString("ubiNum");
				
		//Step 3 : Configure the API and Fetch Response Body
		RestAssured.baseURI = BaseURI ;
		String responseBody = given().header("Content-Type","text/xml; charset=utf-8").when().body(RequestBody).
				when().post("/webservicesserver/NumberConversion.wso").then().extract().response().getBody().asString();
		
		//Step 4 : Extract Response Body Parameters
		XmlPath xmlResponse = new XmlPath(responseBody);
		String Result = xmlResponse.getString("NumberToWordsResult");
				
		//Step 5 : Validate The Response Body Parameters
		Assert.assertEquals(Result, "two hundred and forty ");
		System.out.println("Result:"+ Result);
		System.out.println("Soap Request Validation is Successfull");
			
		

	}

}
