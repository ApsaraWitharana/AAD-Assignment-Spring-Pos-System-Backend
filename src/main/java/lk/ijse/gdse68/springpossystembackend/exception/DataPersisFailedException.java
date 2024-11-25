package lk.ijse.gdse68.springpossystembackend.exception;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
public class DataPersisFailedException extends RuntimeException{
    public DataPersisFailedException() {
        super();
    }

    public DataPersisFailedException(String message) {
        super(message);
    }

    public DataPersisFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
