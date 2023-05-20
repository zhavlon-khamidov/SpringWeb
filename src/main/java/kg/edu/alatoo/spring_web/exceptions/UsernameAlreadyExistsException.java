package kg.edu.alatoo.spring_web.exceptions;

public class UsernameAlreadyExistsException extends Exception {

    private final String username;
    public UsernameAlreadyExistsException(String username) {
        super("Username '" + username +"' already exists");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
