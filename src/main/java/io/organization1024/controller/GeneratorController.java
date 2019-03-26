package io.organization1024.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.organization1024.service.GeneratorService;
import io.organization1024.vo.DataSourceVo;
import io.organization1024.vo.GeneratorVo;
import io.organization1024.vo.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author imyzt
 * @date 2019/3/25
 * @description 代码生成器Web控制器
 */
@RestController
public class GeneratorController {

    private final GeneratorService generatorService;

    @Autowired
    public GeneratorController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping("tableNames")
    public Rest listTableName(@Validated DataSourceVo dataSource) {
        Page<Map<String, Object>> listTableName = generatorService.tableNames(dataSource);
        return Rest.success(listTableName);
    }

    @PostMapping("gen")
    public Rest generator(GeneratorVo generatorVo) {
        generatorService.generator(generatorVo);
        return Rest.success();
    }

}
