package io.organization1024.vo;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author imyzt
 * @date 2019/3/25
 * @description 代码生成属性, 详见 https://mybatis.plus/config/generator-config.html
 */
@Data
public class GeneratorVo {

    /**
     * 全局配置
     */
    private String outputDir;
    private String author;
    private Boolean open;
    private Boolean swagger;

    /**
     * 数据源配置
     */
    private DataSourceVo dataSource;

    /**
     * 包配置
     */
    private String modelName;
    private String parent;

    /**
     * 策略配置
     */
    private String superEntityClass;
    private Boolean entityLombokModel;
    private Boolean restControllerStyle;
    private String superControllerClass;
    private String include;

}
