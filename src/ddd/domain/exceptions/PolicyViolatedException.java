package ddd.domain.exceptions;

public class PolicyViolatedException extends Exception {
    public PolicyViolatedException(String message) {
        super(message);
    }

    public PolicyViolatedException(String message, Throwable e) {
    }
}
