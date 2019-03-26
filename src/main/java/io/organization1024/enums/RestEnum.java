package io.organization1024.enums;

import lombok.Getter;

/**
 * @author imyzt
 * @date 2019/1/23 10:40
 * @description RestEnum
 */
@Getter
public enum RestEnum {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 数据库不存在
     */
    DB_NOT_EXIST(500100, "数据库不存在"),
    DB_DRIVER_ERROR(500101, "数据库驱动错误"),
    SQL_ERROR(500102, "SQL错误"),



    /**
     * 系统异常
     */
    ERROR(999, "系统异常"),


        ;

    private Integer code;

    private String msg;

    RestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
