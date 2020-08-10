import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import Files.reusableMethods;

public class IGTest extends reusableMethods {

	public static void assertion_StatusCodeCheck() {
		int statuscode = given().when().get("/apitest").then().assertThat().statusCode(200).extract().statusCode();

		if (statuscode == 200) {
			System.out.println("pass");
		}
	}

	public static void assertion_Header_Check() {
		String header = given().when().get("/apitest").then().extract().header("Content-Type");
		if (header.contains("application/json")) {
			System.out.println("pass");
		}
	}

	public static void assertion_Body_Check() {
		String ExpAge = "25";
		String ExpId = "101209986";
		String ExpRole = "QA Automation Developer";
		String ExpDob = "25-02-1994";
		String ExpMessage = "data retrieved successful";

		String response3 = given().when().get("/apitest").then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).extract().response().asString();

		String id = reusableMethods.getJsonPath(response3, "employeeData[0].id");
		assertEquals(id, ExpId);
		// System.out.println(id);

		String age = reusableMethods.getJsonPath(response3, "employeeData[0].age");
		assertEquals(age, ExpAge);
		// System.out.println(age);

		String role = reusableMethods.getJsonPath(response3, "employeeData[0].role");
		assertEquals(role, ExpRole);
		// System.out.println(role);

		String dob = reusableMethods.getJsonPath(response3, "employeeData[0].dob");
		assertEquals(dob, ExpDob);
		// System.out.println(dob);

		String message = reusableMethods.getJsonPath(response3, "message");
		assertEquals(message, ExpMessage);
		// System.out.println(message);

		if (id.equalsIgnoreCase(ExpId) && age.equalsIgnoreCase(ExpAge) && role.equalsIgnoreCase(ExpRole)
				&& dob.equalsIgnoreCase(ExpDob) && message.equalsIgnoreCase(ExpMessage))
			System.out.println("pass");

	}

	public static void assertion_Company_Check() {
		String Expcompany = "ABC Infotech";

		String response4 = given().when().get("/apitest").then().extract().response().asString();

		String Actcompany = reusableMethods.getJsonPath(response4, "employeeData[0].company");
		try {
			assertEquals(Actcompany, Expcompany);
		}

		catch (java.lang.AssertionError AS) {
			System.out.println(AS);
		}
		if (Actcompany == Expcompany) {
			System.out.println("pass");

		} else {
			System.out.println("fail");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://demo4032024.mockable.io";
		assertion_StatusCodeCheck();
		assertion_Header_Check();
		assertion_Body_Check();
		assertion_Company_Check();

	}

}
