package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void avatarAndIdEquality(){
        List<UserData> users = given()
                .when().
                contentType(ContentType.JSON).
                get(URL + "api/users?page=2").
                then().log().all().
                extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(user ->Assert.assertTrue(user.getAvatar().contains(user.getId().toString())));
    }

    @Test
    public void successRegistration(){
        //Specifications.installSpecification(Specifications.requestSpec(URL, Specifications.responseSpecOK200()));
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Registration user = new Registration("eve.holt@reqres.in","pistol");
        RegistrationSuccess registrationSuccess = given().body(user).
                when().post(URL + "api/register").
                then().log().all().
                extract().as(RegistrationSuccess.class);
        //Assert.assertEquals(id, registrationSuccess.getId());
        //Assert.assertEquals(token, registrationSuccess.getToken());

    }

    @Test
    public void succRegistration(){
        Registration user1 = new Registration("eve.holt@reqres.in","pistol" );
        //user1.setEmail();
        //user1.setPassword("pistol");
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        RegistrationSuccess registrationSuccess =given().contentType("application/json").body(user1).
                when().post(URL+"api/register").
                then().statusCode(200).log().all().extract().as(RegistrationSuccess.class);
        Assert.assertEquals(id, registrationSuccess.getId());
        Assert.assertEquals(token, registrationSuccess.getToken());
    }

    @Test
    public void unsuccRegistration(){
        Registration user = new Registration("sydney@fife", "");
        UnsuccessRegistration unsuccessRegistration = given().contentType("application/json")
                .body(user).post("https://reqres.in/api/register")
                .then().log().all()
                .extract().as(UnsuccessRegistration.class);
        Assert.assertEquals("Missing password", unsuccessRegistration.getError());
    }

    @Test
    public void sortUsersByYears(){
        List<ColorsList> colors = given().when()
                .get(URL + "api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsList.class);
        List<Integer> years = colors.stream().map(ColorsList::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
    }

    @Test
    public void deleteUser(){
       /* String result = given().when()
                .delete(URL + "api/users/2")
                .then().log().all()
                .extract().response();
        System.out.println(result + 111);
        
        */
        Response response = given().when()
                .delete(URL + "api/users/2")
                .then().log().all()
                .extract().response();
        Assert.assertEquals(204, response.statusCode());
    }







}
