package work.koreyoshi.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoujx
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseCustomException extends RuntimeException {

    protected String message;

    protected Integer code;

    public BaseCustomException(String message) {
        super(message);
    }

    public BaseCustomException() {
    }

}
