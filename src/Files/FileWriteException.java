package Files;

import java.io.FileNotFoundException;

public class FileWriteException extends RuntimeException {
    public FileWriteException(String message, FileNotFoundException cause) {
        super(message, cause);
    }
}