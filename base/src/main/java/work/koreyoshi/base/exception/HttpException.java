package work.koreyoshi.base.exception;

/**
 * @author zhoujx
 */
public class HttpException extends BaseCustomException {

    public HttpException(String message, int code){
        this.message = message;
        this.code = code;
    }

    public static HttpException connectError() {
        return new HttpException("连接错误", -1);
    }
}
