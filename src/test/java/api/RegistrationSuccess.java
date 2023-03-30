package api;

public class RegistrationSuccess {
    private Integer id;
    private String token;



    public RegistrationSuccess(Integer id, String token) {
        this.id = id;
        this.token = token;
    }
    public RegistrationSuccess(){};

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
