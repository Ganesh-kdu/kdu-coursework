package question1;

public class InvalidDataException extends RuntimeException{
    InvalidDataException (String msge, Throwable cause){
        super(msge,cause);
    }
}
