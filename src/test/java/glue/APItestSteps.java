package glue;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

public class APItestSteps {
	private Response response;

    @Given("I perform a GET call to {string}")
    public void i_perform_a_get_call(String url) {
        response = given()
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .response();
    }

    @Then("the response status code should be {int}")
    public void validate_status_code(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("the response body should contain")
    public void validate_body_values(io.cucumber.datatable.DataTable table) {

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String field = row.get("field");
            String expectedValue = row.get("value"); 
            if(field.equals("personal_name")) {
                String actual = response.jsonPath().getString("personal_name");
                assertEquals("Mismatch for personal_name", expectedValue, actual);
            }

            if(field.equals("alternate_names")) {
                List<String> list = response.jsonPath().getList("alternate_names");
                assertTrue(
                    "alternate_names does NOT contain expected value: " + expectedValue,
                    list.contains(expectedValue)
                );
            }
        }
    }
}
