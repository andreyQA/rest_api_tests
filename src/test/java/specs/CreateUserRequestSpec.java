package specs;

import io.restassured.specification.RequestSpecification;

import static helper.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class CreateUserRequestSpec {
    public static RequestSpecification createRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().uri()
            .log().all()
            .baseUri("https://reqres.in")
            .basePath("/api/users");
}


