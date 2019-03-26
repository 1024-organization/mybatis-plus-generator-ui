package io.organization1024.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author imyzt
 * @date 2019/3/25
 * @description 数据源相关配置文件
 */
@Data
public class DataSourceVo {

    /**
     * 驱动连接的URL
     */
    @NotBlank(message = "请填写数据库驱动连接的URL")
    private String url;
    /**
     * 驱动名称
     */
    @NotBlank(message = "请选择数据库驱动")
    private String driverName;
    /**
     * 数据库连接用户名
     */
    @NotBlank(message = "请填写数据库连接用户名")
    private String username;
    /**
     * 数据库连接密码
     */
    @NotBlank(message = "请填写数据库连接密码")
    private String password;

    /**
     * 数据库名称
     */
    @NotBlank(message = "请填写数据库名称")
    private String dbName;
}
