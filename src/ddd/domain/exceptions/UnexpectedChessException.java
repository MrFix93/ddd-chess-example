package ddd.domain.exceptions;

public class UnexpectedChessException extends RuntimeException {
    public UnexpectedChessException() {
    }

    public UnexpectedChessException(String message, Throwable cause) {
        super(message, cause);
    }
}
