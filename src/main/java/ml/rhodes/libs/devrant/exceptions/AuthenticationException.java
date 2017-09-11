package ml.rhodes.libs.devrant.exceptions;

public class AuthenticationException extends Exception {
    public AuthenticationException() {
        super("Invalid login data specified.");
    }
}
