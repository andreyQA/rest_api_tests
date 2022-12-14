package specs;

import io.restassured.specification.RequestSpecification;

import static helper.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class ListOfUsersRequestSpec {
    public static RequestSpecification usersRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().all()
            .baseUri("https://reqres.in")
            .basePath("/api/users");
}


