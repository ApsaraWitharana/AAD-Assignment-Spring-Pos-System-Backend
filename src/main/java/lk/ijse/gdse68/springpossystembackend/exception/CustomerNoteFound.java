package lk.ijse.gdse68.springpossystembackend.exception;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
public class CustomerNoteFound extends RuntimeException{
    public CustomerNoteFound() {
        super();
    }

    public CustomerNoteFound(String message) {
        super(message);
    }

    public CustomerNoteFound(String message, Throwable cause) {
        super(message, cause);
    }
}
