package restassuretests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Demo1_Request {

	@Test (priority = 1)
	public void addBook() {
		given()
		.contentType("application/json")
		.body("{\n"
				+ "   \"name\":\"API Automation with Java\",\n"
				+ "   \"isbn\":\"ABC\",\n"
				+ "   \"aisle\":\"2275\",\n"
				+ "   \"author\":\"Mary Ann\"\n"
				+ "}")
		.when()
			.post("http://216.10.245.166/Library/Addbook.php")
		.then().statusCode(200)
				.body("Msg", equalTo("successfully added"))
				.header("Content-Type", "application/json;charset=UTF-8");
	}
	

	@Test (priority = 2)
	public void getBookDetails() {
		given()
		.when()
			.get("http://216.10.245.166/Library/GetBook.php?ID=ABC2275")
		.then().statusCode(200)
				.statusLine("HTTP/1.1 200 OK").assertThat().body("book_name", contains("API Automation with Java"))
				.header("Content-Type", "application/json;charset=UTF-8");
	}
	

	@Test (priority = 3)
	public void deleteBook() {
		given()
		.contentType("application/json")
		.body("{\n"
				+ "   \"ID\":\"ABC2275\"\n"
				+ "}")
		.when()
			.post("http://216.10.245.166/Library/DeleteBook.php")
		.then().statusCode(200)
				.body("msg", equalTo("book is successfully deleted"))
				.header("Content-Type", "application/json;charset=UTF-8");
	}
	
}
