package ddd.domain.exceptions;

public class IllegalBoardSquareException extends RuntimeException {
    public IllegalBoardSquareException(String message) {
        super(message);
    }
}
