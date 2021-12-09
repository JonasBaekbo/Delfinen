//@ Mikkel Sandell
package files;

import java.io.FileNotFoundException;

public class FileReadException extends RuntimeException {
    public FileReadException(String message, FileNotFoundException cause) {
        super(message, cause);
    }
}