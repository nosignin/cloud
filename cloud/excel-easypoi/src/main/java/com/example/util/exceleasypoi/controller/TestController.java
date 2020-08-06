package com.example.util.exceleasypoi.controller;

import com.alibaba.fastjson.JSON;
import com.example.util.exceleasypoi.entity.CustomerList;
import com.example.util.exceleasypoi.util.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
     * 地址在：http://localhost:8080/swagger-ui.html
     */
    @PostMapping("/excel/import")
    @ApiOperation(value = "导入excel", notes = "具体描述")
    public List<CustomerList> importExcel(@RequestParam("file") MultipartFile file) {
        List<CustomerList> personList = ExcelUtil.importExcel(file, 1, CustomerList.class);
        log.info(">>> 解析到的用户数据是 {} <<<", JSON.toJSONString(personList));
        return personList;
    }


    /**
     * 导出excel
     * 地址在：http://localhost:8080/excel/output
     */
    @GetMapping("/excel/output")
    @ApiOperation(value = "导出excel", notes = "具体描述")
    public void outputExcel(HttpServletResponse response){
        try {
            List<CustomerList> personList = new ArrayList<>();
            for(int i =0;i<10;i++){
                CustomerList customerList = new CustomerList();
                customerList.setName("asdas"+i);
                personList.add(customerList);
            }
            ExcelUtil.exportExcel(personList,CustomerList.class,"客户表.xls",response);
        } catch (Exception e) {
            log.info("getCustomerPage", e);
        }
    }
}