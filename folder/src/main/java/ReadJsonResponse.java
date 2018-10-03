
public class ReadJsonResponse{
public static void main(String[] str) throws Exception {

	RestAssured.baseURI ="http://198.58.98.34:4350/api/user";
	RequestSpecification request = RestAssured.given();
	
	
	JSONObject requestParams = new JSONObject();
	requestParams.put("email", "flydemo1@mailinator.com"); 
	requestParams.put("pass", "demo123#");
	
	// Add a header stating the Request body is a JSON
	request.header("Content-Type", "application/json");
	 
	// Add the Json to the body of the request
	request.body(requestParams.toJSONString());
	 
	// Post the request and check the response
	Response response = request.post("/login");
	
	int statusCode = response.getStatusCode();
	System.out.println("status: "+statusCode);
}
}