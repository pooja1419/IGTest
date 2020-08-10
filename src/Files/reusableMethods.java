package Files;
import io.restassured.path.json.JsonPath;

public class reusableMethods {

	public static String getJsonPath(String response, String key)
	{
		String FinalResp=response.toString();
		JsonPath js = new JsonPath(FinalResp);
		return js.get(key).toString();
		
	}

	
}
