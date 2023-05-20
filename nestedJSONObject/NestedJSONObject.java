package nestedJSONObject;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NestedJSONObject {
	@Test
	public void CreateUser() throws JsonProcessingException {
		EmployeesPojoClass emp1 = new EmployeesPojoClass();
		emp1.setFirstname("ARIF");
		emp1.setLastname("HUSSAIN");
		emp1.setGender("MALE");
		emp1.setAge(30);
		emp1.setSalary(42000.99);
		
		EmployeeAddress empAdd = new EmployeeAddress();
		empAdd.setStreet("PARK AVENUE");
		empAdd.setCity("PATNA");
		empAdd.setState("BIHAR");
		empAdd.setPinCode(802312);
		
		emp1.setAddress(empAdd);
		
		//convert class object to json object as string
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonpayload =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		RequestSpecification reqspec = RestAssured.given();
		reqspec.baseUri("https://httpbin.org/post");
		reqspec.contentType(ContentType.JSON);
		reqspec.body(jsonpayload);
		
		Response response = reqspec.post();
		response.prettyPrint();
		
		System.out.println("jsonpayload: "+jsonpayload);
	}

}
