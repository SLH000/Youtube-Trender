/**
 * Exception class to indicate any parsing error
 */
public class YouTubeDataParserException extends Exception {

    public YouTubeDataParserException(String message) {
        super(message);
    }
    public YouTubeDataParserException(String message, Throwable cause) {
        super(message, cause);
    }
}