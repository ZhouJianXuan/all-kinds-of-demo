package work.koreyoshi.base.exception;

/**
 * @author zhoujx
 */
public class ResponseException extends BaseCustomException{

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException() {
        super();
    }

    public ResponseException(String message, Exception e) {
        super(message, e);
    }

}
