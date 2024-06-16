package GETAPIs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetAPIWithPathParam {

	@Test
	public void getCircuitDataAPIWithYearPathParamTest() {

		baseURI = "http://ergast.com";

		given().log().all().pathParam("year", "2016").when().log().all().get("/api/f1/{year}/circuits.json").then()
				.log().all().assertThat().statusCode(200).and().body("MRData.CircuitTable.season", equalTo("2016"))
				.and().body("MRData.CircuitTable.Circuits.circuitId", hasSize(21));

	}

	@DataProvider
	public Object[][] getCircuitYearData() {
		
		Object[][] data= {
				         {"2016",21},
				         {"2017",20},
				         {"1966",9},
				         {"2023",22}
				         };
		return data;

	}

	@Test(dataProvider = "getCircuitYearData")
	public void getCircuitDataAPIWithYearPathParamTestWithDataProvider(String seasonYear, int circuitsCount) {

		baseURI = "http://ergast.com";

		given().log().all().pathParam("year", seasonYear).when().log().all().get("/api/f1/{year}/circuits.json").then()
				.log().all().assertThat().statusCode(200).and().body("MRData.CircuitTable.season", equalTo(seasonYear))
				.and().body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitsCount));

	}

}
