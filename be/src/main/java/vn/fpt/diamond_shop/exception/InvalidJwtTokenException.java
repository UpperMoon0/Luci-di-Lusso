package vn.fpt.diamond_shop.exception;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException() {
        super("Invalid JWT token");
    }
}