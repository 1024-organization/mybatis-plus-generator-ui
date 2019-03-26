package io.organization1024.exception;


import io.organization1024.enums.RestEnum;
import lombok.Getter;

/**
 * @author imyzt
 * @date 2019/3/25
 * @description 异常类
 */
@Getter
public class GeneratorUiException extends RuntimeException{

    private final Integer status;

    private final String msg;

    public GeneratorUiException(RestEnum restEnum, Exception e) {
        super(restEnum.getMsg(), e);
        this.status = restEnum.getCode();
        this.msg = restEnum.getMsg();
    }
}
