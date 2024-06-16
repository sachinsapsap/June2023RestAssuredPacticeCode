package XMLAPIs;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class GetCircuitXMlAPITest {

	@Test
	public void xmlTest() {

		baseURI = "http://ergast.com";

		Response response = given().log().all().when().log().all().get("/api/f1/2017/circuits.xml").then().extract()
				.response();

		String responseBody = response.body().asString();
		System.out.println(responseBody);

//		XmlPath xmlPath= response.xmlPath();

		XmlPath xmlPath = new XmlPath(responseBody);

		List<String> circuitNameList = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");

		for (String e : circuitNameList) {
			System.out.println(e);
		}

		System.out.println("-----------------------------");

		List<String> circuitIdList = xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");

		for (String e : circuitIdList) {
			System.out.println(e);
		}

		System.out.println("-----------------------------");

//		Fetch locality where CircuitId="Americas"

		String locality = xmlPath.get("**.findAll{it.@circuitId=='americas'}.Location.Locality").toString();
		System.out.println(locality);

		System.out.println("-----------------------------");

//		Fetch latitude and longitude where CircuitId="Americas"

		String latitude = xmlPath.get("**.findAll{it.@circuitId=='americas'}.Location.@lat").toString();
		System.out.println(latitude);

		String longitude = xmlPath.get("**.findAll{it.@circuitId=='americas'}.Location.@long").toString();
		System.out.println(longitude);

//		Fetch locality where CircuitId="Americas" or "bahrain"

		String locality1 = xmlPath
				.get("**.findAll{it.@circuitId=='americas'||it.@circuitId=='bahrain'}.Location.Locality").toString();
		System.out.println(locality1);
		
//		Fetch circuitName where CircuitId="Americas"

		String circuitName = xmlPath.get("**.findAll{it.@circuitId=='americas'}.CircuitName").toString();
		System.out.println(circuitName);
		
//		Fetch url where CircuitId="Americas"

		String url = xmlPath.get("**.findAll{it.@circuitId=='americas'}.@url").toString();
		System.out.println(url);

	}

}
