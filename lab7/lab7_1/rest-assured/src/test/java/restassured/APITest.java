package restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class APITest {

    private final String API = "https://jsonplaceholder.typicode.com/todos";


    @Test
    public void whenRequestedTodos_thenStatusCode200() {
        when()
            .get(API)
        .then()
            .statusCode(200);
    }

    @Test
    public void whenRequestedSpecificTodo_thenCheckTitle() {
        when()
            .get(API+"/4")
        .then()
            .statusCode(200)
            .log().body()
            .and().body("id", equalTo(4))
            .and().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void whenRequestedTodos_thenReturnSpecifiedIds() {
        when()
            .get(API)
        .then()
            .statusCode(200)
            .log().body()
            .and().body("id", hasItems(198, 199));
    }

    
}
