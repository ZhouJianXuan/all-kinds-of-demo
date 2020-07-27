package work.koreyoshi.base.exception;

/**
 * @author zhoujx
 */
public class FileException extends BaseCustomException{

    public FileException(String message, int code){
        this.message = message;
        this.code = code;
    }

    public static FileException readError(){
        return new FileException("文件读失败", 20010);
    }

    public static FileException writeError(){
        return new FileException("文件写失败", 20020);
    }
}
