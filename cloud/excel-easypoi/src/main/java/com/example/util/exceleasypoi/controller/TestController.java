package com.example.util.exceleasypoi.controller;

import com.alibaba.fastjson.JSON;
import com.example.util.exceleasypoi.entity.CustomerList;
import com.example.util.exceleasypoi.util.FileWithExcelUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 石佳
 * @since 2020/08/06
 */
@RestController("/test")
@Slf4j
public class TestController {

    /**
     * 导入excel
     */
    @PostMapping("/import/excel")
    @ApiOperation(value = "导入excel", notes = "具体描述")
    public List<CustomerList> importExcel(@RequestParam("file") MultipartFile file) {
        List<CustomerList> personList = FileWithExcelUtil.importExcel(file, 1, CustomerList.class);
        log.info(">>> 解析到的用户数据是 {} <<<", JSON.toJSONString(personList));
        return personList;
    }
}