package lk.ijse.gdse68.springpossystembackend.exception;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
public class ItemNoteFound extends RuntimeException{
    public ItemNoteFound() {
        super();
    }

    public ItemNoteFound(String message) {
        super(message);
    }

    public ItemNoteFound(String message, Throwable cause) {
        super(message, cause);
    }
}
