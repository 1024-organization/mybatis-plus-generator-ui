package io.organization1024.service;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import io.organization1024.util.DbKit;
import io.organization1024.vo.DataSourceVo;
import io.organization1024.vo.GeneratorVo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * @author imyzt
 * @date 2019/1/23 10:56
 * @description 代码生成器核心服务类
 */
@Service
public class GeneratorService {

    private static final String TEMPLATES_MAPPER_XML_FTL = "/templates/mapper.xml.ftl";

    public Page<Map<String, Object>> tableNames(DataSourceVo dataSourceVo) {
        String dbName = dataSourceVo.getDbName();
        List<String> listTableName = DbKit.tableNames(dataSourceVo, dbName);
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        Page <Map <String, Object>> mapPage = new Page <>();
        for (int i = 0; i < listTableName.size(); i++) {
            HashMap <String, Object> hashMap = new HashMap <>(10);
            hashMap.put("id", i + 1);
            hashMap.put("tableName", listTableName.get(i));
            maps.add(hashMap);
        }
        mapPage.setRecords(maps);
        mapPage.setTotal((long) listTableName.size());
        return mapPage;
    }

    public void generator(GeneratorVo generatorVO) {

        // 代码生成器
        AutoGenerator autoGenerator = parameterWrapper(generatorVO);
        autoGenerator.execute();
    }


    private AutoGenerator parameterWrapper(GeneratorVo generatorVo) {

        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generatorVo.getOutputDir());
        gc.setAuthor(generatorVo.getAuthor());
        gc.setOpen(generatorVo.getOpen());
         gc.setSwagger2(generatorVo.getSwagger());
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        DataSourceVo dataSource = generatorVo.getDataSource();
        dsc.setUrl(dataSource.getUrl());
        dsc.setDriverName(dataSource.getDriverName());
        dsc.setUsername(dataSource.getUsername());
        dsc.setPassword(dataSource.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(generatorVo.getModelName());
        pc.setParent(generatorVo.getParent());
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // do something
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        outputConfig(focList, generatorVo,pc.getMapper());
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(generatorVo.getSuperEntityClass());
        strategy.setEntityLombokModel(generatorVo.getEntityLombokModel());
        strategy.setRestControllerStyle(generatorVo.getRestControllerStyle());
        strategy.setSuperControllerClass(generatorVo.getSuperControllerClass());
        //表名，多个英文逗号分割
        strategy.setInclude(generatorVo.getInclude().split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        return mpg;
    }

    /**
     * 将Mapper.xml转到Mapper.java目录
     */
    private void outputConfig(List<FileOutConfig> focList, GeneratorVo generatorVo, String mapperName) {

        UnaryOperator<String> function = (String entityName) -> {
            StringBuilder builder = new StringBuilder();
            String[] split = generatorVo.getParent().split("\\.");
            for (String aSplit : split) {
                builder.append(aSplit).append(File.separator);
            }
            return String.join(File.separator,
                    generatorVo.getOutputDir(), builder.toString(), generatorVo.getModelName(), mapperName, entityName);
        };
        focList.add(new FileOutConfig(TEMPLATES_MAPPER_XML_FTL) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return function.apply(tableInfo.getEntityName()) + "Mapper" + StringPool.DOT_XML;
            }
        });
    }

}
