package work.koreyoshi.base.exception;

public class SendException extends BaseCustomException{

    public SendException(String message, int code){
        this.message = message;
        this.code = code;
    }

    public static SendException sendMailError() {
        return new SendException("发送邮件错误", -1);
    }

    public static SendException sendMailError(String message) {
        return new SendException(message, -1);
    }
}
