package io.organization1024.vo;

import io.organization1024.enums.RestEnum;
import lombok.Data;

/**
 * @author imyzt
 * @date 2019/3/25
 * @description HTTP统一返回对象
 */
@Data
public class Rest<T> {

    private Integer code;

    private String msg;

    private T data;

    private Rest(RestEnum rest) {
        this.code = rest.getCode();
        this.msg = rest.getMsg();
    }

    public static Rest success() {
        return new Rest<>(RestEnum.SUCCESS);
    }

    public static <T> Rest success(T data) {
        Rest<T> rest = new Rest<>(RestEnum.SUCCESS);
        rest.setData(data);
        return rest;
    }

    public static  Rest fail(RestEnum restEnum) {
        return new Rest<>(restEnum);
    }

    public static  Rest error(RestEnum restEnum) {
        return new Rest<>(restEnum);
    }
}
