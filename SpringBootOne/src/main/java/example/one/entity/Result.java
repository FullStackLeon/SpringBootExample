package example.one.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result<T> {
    private Integer code;
    private String message;
    private  T data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
