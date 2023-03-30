package api;

public class UnsuccessRegistration {
    private String error;

    UnsuccessRegistration(){}

    public UnsuccessRegistration(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
